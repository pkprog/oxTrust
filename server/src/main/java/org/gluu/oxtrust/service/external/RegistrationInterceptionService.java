/*
 * oxTrust is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxtrust.service.external;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import javax.faces.application.FacesMessage;
import org.apache.commons.io.IOUtils;
import org.gluu.oxtrust.ldap.service.OrganizationService;
import org.gluu.oxtrust.model.GluuCustomPerson;
import org.gluu.oxtrust.model.GluuOrganization;
import org.gluu.oxtrust.model.RegistrationConfiguration;
import org.gluu.oxtrust.model.RegistrationInterceptorScript;
import org.gluu.oxtrust.service.python.interfaces.RegistrationScript;
import org.gluu.oxtrust.util.OxTrustConstants;
import org.python.core.PyLong;
import org.python.core.PyObject;
import org.slf4j.Logger;
import org.xdi.exception.PythonException;
import org.xdi.oxauth.model.util.Util;
import org.xdi.service.PythonService;

/**
 * @author Oleksiy Tataryn
 *
 */
@Named("registrationInterceptionService")
@Stateless
@Deprecated
public class RegistrationInterceptionService {

	@Inject
	private Logger log;
	
	@Inject
	private OrganizationService organizationService;

	@Inject
	private PythonService pythonService;
	
	public boolean runPreRegistrationScripts(GluuCustomPerson person, Map<String, String[]> requestParameters) {
		GluuOrganization org = organizationService.getOrganization();
		RegistrationConfiguration config = org.getOxRegistrationConfiguration();
		if(config != null && config.isRegistrationInterceptorsConfigured()){
			List<RegistrationInterceptorScript> scripts = config.getRegistrationInterceptorScripts();
			List<RegistrationInterceptorScript> sortedEnabledPreRegistrationScripts = sort(getActive(getPreRegistrationScripts(scripts)));
			if(sortedEnabledPreRegistrationScripts != null){
				boolean result = true;
				for(RegistrationInterceptorScript script: sortedEnabledPreRegistrationScripts){
					RegistrationScript registrationScript = createRegistrationScriptFromStringWithPythonException(script);
					result &= registrationScript.execute(script.getCustomAttributes(), person, requestParameters);
				}
				return result;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}

	private RegistrationScript createRegistrationScriptFromStringWithPythonException(
			RegistrationInterceptorScript script) {
		String pythonScriptText = script.getCustomScript();
		if (pythonScriptText == null) {
			return null;
		}

		RegistrationScript pythonScript = null;

		InputStream bis = null;
		try {
            bis = new ByteArrayInputStream(pythonScriptText.getBytes(Util.UTF8_STRING_ENCODING));
            pythonScript = pythonService.loadPythonScript(bis, "RegistrationScriptClass",
            		RegistrationScript.class, new PyObject[] { new PyLong(System.currentTimeMillis()) });
		} catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        } catch (PythonException e) {
        	  log.error(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(bis);
		}

		return pythonScript;
	}

	private List<RegistrationInterceptorScript> sort(List<RegistrationInterceptorScript> scripts) {
		if(scripts == null || scripts.isEmpty()){
			return null;
		}else{
			Collections.sort(scripts);
			return scripts;
		}
	}

	private List<RegistrationInterceptorScript> getActive(List<RegistrationInterceptorScript> scripts) {
		if(scripts == null || scripts.isEmpty()){
			return null;
		}else{
			List<RegistrationInterceptorScript> activeScripts = new ArrayList<RegistrationInterceptorScript>();
			for(RegistrationInterceptorScript script: scripts){
				if(script.isEnabled()){
					activeScripts.add(script);
				}
			}
			return activeScripts;
		}
	}

	private List<RegistrationInterceptorScript> getPreRegistrationScripts(
			List<RegistrationInterceptorScript> scripts) {
		if(scripts == null || scripts.isEmpty()){
			return null;
		}else{
			List<RegistrationInterceptorScript> preRegistrationScripts = new ArrayList<RegistrationInterceptorScript>();
			for(RegistrationInterceptorScript script: scripts){
				if(script.getType().equals(OxTrustConstants.PRE_REGISTRATION_SCRIPT)){
					preRegistrationScripts.add(script);
				}
			}
			return preRegistrationScripts;
		}
		
	}

	public boolean runPostRegistrationScripts(GluuCustomPerson person, Map<String, String[]> requestParameters) {
		GluuOrganization org = organizationService.getOrganization();
		RegistrationConfiguration config = org.getOxRegistrationConfiguration();
		if(config != null && config.isRegistrationInterceptorsConfigured()){
			List<RegistrationInterceptorScript> scripts = config.getRegistrationInterceptorScripts();
			List<RegistrationInterceptorScript> sortedEnabledPostRegistrationScripts = sort(getActive(getPostRegistrationScripts(scripts)));
			if(sortedEnabledPostRegistrationScripts != null){
				boolean result = true;
				for(RegistrationInterceptorScript script: sortedEnabledPostRegistrationScripts){
					RegistrationScript registrationScript = createRegistrationScriptFromStringWithPythonException(script);
					result &= registrationScript.execute(script.getCustomAttributes(), person, requestParameters);
				}
				return result;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}

	private List<RegistrationInterceptorScript> getPostRegistrationScripts(
			List<RegistrationInterceptorScript> scripts) {
		if(scripts == null || scripts.isEmpty()){
			return null;
		}else{
			List<RegistrationInterceptorScript> postRegistrationScripts = new ArrayList<RegistrationInterceptorScript>();
			for(RegistrationInterceptorScript script: scripts){
				if(script.getType().equals(OxTrustConstants.POST_REGISTRATION_SCRIPT)){
					postRegistrationScripts.add(script);
				}
			}
			return postRegistrationScripts;
		}
		
	}

	public boolean runInitRegistrationScripts(GluuCustomPerson person,
			Map<String, String[]> requestParameters) {
		GluuOrganization org = organizationService.getOrganization();
		RegistrationConfiguration config = org.getOxRegistrationConfiguration();
		if(config != null && config.isRegistrationInterceptorsConfigured()){
			List<RegistrationInterceptorScript> scripts = config.getRegistrationInterceptorScripts();
			List<RegistrationInterceptorScript> sortedEnabledInitRegistrationScripts = sort(getActive(getInitRegistrationScripts(scripts)));
			if(sortedEnabledInitRegistrationScripts != null){
				boolean result = true;
				for(RegistrationInterceptorScript script: sortedEnabledInitRegistrationScripts){
					RegistrationScript registrationScript = createRegistrationScriptFromStringWithPythonException(script);
					result &= registrationScript.execute(script.getCustomAttributes(), person, requestParameters);
				}
				return result;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}

	private List<RegistrationInterceptorScript> getInitRegistrationScripts(
			List<RegistrationInterceptorScript> scripts) {
		if(scripts == null || scripts.isEmpty()){
			return null;
		}else{
			List<RegistrationInterceptorScript> initRegistrationScripts = new ArrayList<RegistrationInterceptorScript>();
			for(RegistrationInterceptorScript script: scripts){
				if(script.getType().equals(OxTrustConstants.INIT_REGISTRATION_SCRIPT)){
					initRegistrationScripts.add(script);
				}
			}
			return initRegistrationScripts;
		}
	}
}
