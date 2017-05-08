/*
 * oxTrust is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxtrust.action;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import javax.faces.application.FacesMessage;
import org.gluu.oxtrust.ldap.service.ApplianceService;
import org.gluu.oxtrust.model.GluuAppliance;
import org.gluu.oxtrust.util.OxTrustConstants;
import org.slf4j.Logger;

/**
 * Action class for health check display
 * 
 * @author Oleksiy Tataryn Date: 11.14.2013
 */
@ConversationScoped
@Named("applianceStatusAction")
public class ApplianceStatusAction implements Serializable {

	private static final long serialVersionUID = -7470520478553992898L;

	@Inject
    private Logger log;

	@Inject
	private ApplianceService applianceService;

	private String health;

	public String checkHealth() {
		GluuAppliance appliance = applianceService.getAppliance();
		Date lastUpdateDateTime = appliance.getLastUpdate();
		long lastUpdate = 0;
		if (lastUpdateDateTime != null) {
			lastUpdate = lastUpdateDateTime.getTime();
		}

		long currentTime = System.currentTimeMillis();
		
		log.debug("lastUpdate: '{0}', currentTime: '{1}'", lastUpdate, currentTime);
		long timeSinceLastUpdate = (currentTime - lastUpdate) / 1000;
		if (timeSinceLastUpdate >= 0 && timeSinceLastUpdate < 100) {
			this.setHealth("OK");
		} else {
			this.setHealth("FAIL");
		}
		log.debug("Set status '{0}'", this.getHealth());

		return OxTrustConstants.RESULT_SUCCESS;
	}

	public String getHealth() {
		return health;
	}

	public void setHealth(String health) {
		this.health = health;
	}

}
