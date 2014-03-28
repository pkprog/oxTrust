package org.gluu.oxtrust.util.jsf;

import java.io.Serializable;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.gluu.oxtrust.ldap.service.ProfileConfigurationService;
import org.gluu.oxtrust.model.ProfileConfiguration;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.faces.Converter;
import org.jboss.seam.annotations.intercept.BypassInterceptors;

@Converter()
@Name("profileConfigurationConverter")
@BypassInterceptors
public class ProfileConfigurationConverter implements javax.faces.convert.Converter, Serializable {

	private static final long serialVersionUID = 3376046924407678310L;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String profileConfigurationName) {
		return profileConfigurationName;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object profileConfiguration) {
		return (String) profileConfiguration;
	}

}
