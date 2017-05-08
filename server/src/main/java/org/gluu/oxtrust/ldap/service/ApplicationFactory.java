/*
 * oxAuth is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxtrust.ldap.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.xdi.service.cache.CacheConfiguration;
import org.xdi.service.cache.InMemoryConfiguration;

/**
 * Holds factory methods to create services
 *
 * @author Yuriy Movchan Date: 02/14/2017
 */
@ApplicationScoped
@Named("applicationFactory")
public class ApplicationFactory {
    
    @Inject
    private ApplianceService applianceService;

    @Inject
    private Logger log;

    @Produces @ApplicationScoped @Named("cacheConfiguration")
   	public CacheConfiguration createCacheConfiguration() {
   		CacheConfiguration cacheConfiguration = applianceService.getAppliance().getCacheConfiguration();
   		if (cacheConfiguration == null || cacheConfiguration.getCacheProviderType() == null) {
   			log.error("Failed to read cache configuration from LDAP. Please check appliance oxCacheConfiguration attribute " +
   					"that must contain cache configuration JSON represented by CacheConfiguration.class. Applieance DN: " + applianceService.getAppliance().getDn());
   			log.info("Creating fallback IN-MEMORY cache configuration ... ");

   			cacheConfiguration = new CacheConfiguration();
   			cacheConfiguration.setInMemoryConfiguration(new InMemoryConfiguration());

   			log.info("IN-MEMORY cache configuration is created.");
   		}
   		log.info("Cache configuration: " + cacheConfiguration);
   		return cacheConfiguration;
   	}

}