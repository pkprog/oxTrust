/*
 * oxTrust is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2014, Gluu
 */

package org.gluu.oxtrust.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.gluu.oxauth.model.common.GrantType;
import org.gluu.oxauth.model.common.ResponseType;
import org.gluu.persist.model.base.Entry;
import org.gluu.persist.model.base.GluuBoolean;
import org.gluu.persist.annotation.AttributeName;
import org.gluu.persist.annotation.DataEntry;
import org.gluu.persist.annotation.JsonObject;
import org.gluu.persist.annotation.ObjectClass;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * oxAuthClient
 *
 * @author Reda Zerrad Date: 06.08.2012
 * @author Yuriy Movchan Date: 05/22/2013
 * @author Javier Rojas Blum
 * @version December 4, 2018
 */
@DataEntry(sortBy = { "displayName" })
@ObjectClass(values = { "top", "oxAuthClient" })
@JsonInclude(Include.NON_NULL)
public class OxAuthClient extends Entry implements Serializable {

	private static final long serialVersionUID = -2310140703735705346L;

	private transient boolean selected;

	@AttributeName(ignoreDuringUpdate = true)
	private String inum;

	@AttributeName(ignoreDuringUpdate = true)
	private String iname;

	@NotNull
	@Size(min = 0, max = 60, message = "Length of the Display Name should not exceed 60")
	@AttributeName
	private String displayName;

	@NotNull
	@Size(min = 0, max = 250, message = "Length of the Description should not exceed 250")
	@AttributeName
	private String description;

	@NotNull
	@AttributeName(name = "oxAuthAppType")
	private OxAuthApplicationType oxAuthAppType;

	@AttributeName(name = "oxAuthContact")
	private List<String> contacts;

	@AttributeName(name = "oxAuthRedirectURI")
	private List<String> oxAuthRedirectURIs;

	@AttributeName(name = "oxAuthPostLogoutRedirectURI")
	private List<String> oxAuthPostLogoutRedirectURIs;

	@AttributeName(name = "oxAuthScope")
	private List<String> oxAuthScopes;

	@AttributeName(name = "oxAuthClaim")
	private List<String> oxAuthClaims;

	@NotNull
	@AttributeName(name = "oxAuthClientSecret")
	private String encodedClientSecret;

	@AttributeName(ignoreDuringUpdate = true)
	private String userPassword;

	@AttributeName(name = "associatedPerson")
	private List<String> associatedPersons;

	@AttributeName(name = "oxAuthTrustedClient")
	private GluuBoolean oxAuthTrustedClient = GluuBoolean.FALSE;

	@AttributeName(name = "oxAuthResponseType")
	private ResponseType[] responseTypes;

	@AttributeName(name = "oxAuthGrantType")
	private GrantType[] grantTypes;

	@AttributeName(name = "oxAuthLogoURI")
	private String logoUri;

	@AttributeName(name = "oxAuthClientURI")
	private String clientUri;

	@AttributeName(name = "oxAuthPolicyURI")
	private String policyUri;

	@AttributeName(name = "oxAuthTosURI")
	private String tosUri;

	@AttributeName(name = "oxAuthJwksURI")
	private String jwksUri;

	@AttributeName(name = "oxAuthJwks")
	private String jwks;

	@AttributeName(name = "oxAuthSectorIdentifierURI")
	private String sectorIdentifierUri;

	@AttributeName(name = "oxAuthSubjectType")
	private OxAuthSubjectType subjectType;

	@AttributeName(name = "oxIdTokenTokenBindingCnf")
	private String idTokenTokenBindingCnf;

	@AttributeName(name = "oxRptAsJwt")
	private GluuBoolean rptAsJwt = GluuBoolean.FALSE;

	@AttributeName(name = "oxAccessTokenAsJwt")
	private GluuBoolean accessTokenAsJwt = GluuBoolean.FALSE;

	@AttributeName(name = "oxAccessTokenSigningAlg")
	private SignatureAlgorithm accessTokenSigningAlg;

	@AttributeName(name = "oxAuthIdTokenSignedResponseAlg")
	private SignatureAlgorithm idTokenSignedResponseAlg;

	@AttributeName(name = "oxAuthIdTokenEncryptedResponseAlg")
	private KeyEncryptionAlgorithm idTokenEncryptedResponseAlg;

	@AttributeName(name = "oxAuthIdTokenEncryptedResponseEnc")
	private BlockEncryptionAlgorithm idTokenEncryptedResponseEnc;

	@AttributeName(name = "oxAuthSignedResponseAlg")
	private SignatureAlgorithm userInfoSignedResponseAlg;

	@AttributeName(name = "oxAuthUserInfoEncryptedResponseAlg")
	private KeyEncryptionAlgorithm userInfoEncryptedResponseAlg;

	@AttributeName(name = "oxAuthUserInfoEncryptedResponseEnc")
	private BlockEncryptionAlgorithm userInfoEncryptedResponseEnc;

	@AttributeName(name = "oxAuthRequestObjectSigningAlg")
	private SignatureAlgorithm requestObjectSigningAlg;

	@AttributeName(name = "oxAuthRequestObjectEncryptionAlg")
	private KeyEncryptionAlgorithm requestObjectEncryptionAlg;

	@AttributeName(name = "oxAuthRequestObjectEncryptionEnc")
	private BlockEncryptionAlgorithm requestObjectEncryptionEnc;

	@AttributeName(name = "oxAuthTokenEndpointAuthMethod")
	private AuthenticationMethod tokenEndpointAuthMethod;

	@AttributeName(name = "oxAuthTokenEndpointAuthSigningAlg")
	private SignatureAlgorithm tokenEndpointAuthSigningAlg;

	@AttributeName(name = "oxAuthDefaultMaxAge")
	private Integer defaultMaxAge;

	@AttributeName(name = "oxAuthRequireAuthTime")
	private GluuBoolean requireAuthTime;

	@AttributeName(name = "oxAuthPostLogoutRedirectURI")
	private String[] postLogoutRedirectUris;

	@AttributeName(name = "oxClaimRedirectURI")
	private String[] claimRedirectURI;

	@AttributeName(name = "oxAuthLogoutURI")
	private List<String> logoutUri;

	@AttributeName(name = "oxAuthLogoutSessionRequired")
	private GluuBoolean logoutSessionRequired = GluuBoolean.FALSE;

	@AttributeName(name = "oxPersistClientAuthorizations")
	private GluuBoolean oxAuthPersistClientAuthorizations = GluuBoolean.TRUE;

	@AttributeName(name = "oxIncludeClaimsInIdToken")
	private GluuBoolean oxIncludeClaimsInIdToken = GluuBoolean.FALSE;

	@AttributeName(name = "oxRefreshTokenLifetime")
	private Integer oxRefreshTokenLifetime;

	@AttributeName(name = "oxAccessTokenLifetime")
	private Integer accessTokenLifetime;

	@AttributeName(name = "oxAuthDefaultAcrValues")
	private String[] defaultAcrValues;

	@AttributeName(name = "oxAuthInitiateLoginURI")
	private String initiateLoginUri;

	@AttributeName(name = "oxAuthClientSecretExpiresAt")
	private Date clientSecretExpiresAt;

	@AttributeName(name = "oxAuthRequestURI")
	private String[] requestUris;

	@AttributeName(name = "oxAuthAuthorizedOrigins")
	private String[] authorizedOrigins;

	@AttributeName(name = "oxSoftwareId")
	private String softwareId;

	@AttributeName(name = "oxSoftwareVersion")
	private String softwareVersion;

	@AttributeName(name = "oxSoftwareStatement")
	private String softwareStatement;

	@AttributeName(name = "oxDisabled")
	private boolean disabled;

	@AttributeName(name = "oxdId")
	private String oxdId;

	@Transient
	private String oxAuthClientSecret;

	@AttributeName(name = "oxAttributes")
	@JsonObject
	private ClientAttributes attributes;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String[] getClaimRedirectURI() {
		return claimRedirectURI;
	}

	public void setClaimRedirectURI(String[] claimRedirectURI) {
		this.claimRedirectURI = claimRedirectURI;
	}

	public String getInum() {
		return inum;
	}

	public void setInum(String inum) {
		this.inum = inum;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public final String getDescription() {
		return description;
	}

	public final void setDescription(String description) {
		this.description = description;
	}

	public OxAuthApplicationType getOxAuthAppType() {
		return oxAuthAppType;
	}

	public void setOxAuthAppType(OxAuthApplicationType oxAuthAppType) {
		this.oxAuthAppType = oxAuthAppType;
	}

	public List<String> getContacts() {
		return contacts;
	}

	public void setContacts(List<String> contacts) {
		this.contacts = contacts;
	}

	public List<String> getOxAuthRedirectURIs() {
		return oxAuthRedirectURIs;
	}

	public void setOxAuthRedirectURIs(List<String> oxAuthRedirectURIs) {
		this.oxAuthRedirectURIs = oxAuthRedirectURIs;
	}

	public List<String> getOxAuthPostLogoutRedirectURIs() {
		return oxAuthPostLogoutRedirectURIs;
	}

	public void setOxAuthPostLogoutRedirectURIs(List<String> oxAuthPostLogoutRedirectURIs) {
		this.oxAuthPostLogoutRedirectURIs = oxAuthPostLogoutRedirectURIs;
	}

	public List<String> getOxAuthScopes() {
		return oxAuthScopes;
	}

	public void setOxAuthScopes(List<String> oxAuthScopes) {
		this.oxAuthScopes = oxAuthScopes;
	}

	public List<String> getOxAuthClaims() {
		return oxAuthClaims;
	}

	public void setOxAuthClaims(List<String> oxAuthClaims) {
		this.oxAuthClaims = oxAuthClaims;
	}

	public String getEncodedClientSecret() {
		return encodedClientSecret;
	}

	public void setEncodedClientSecret(String encodedClientSecret) {
		this.encodedClientSecret = encodedClientSecret;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<String> getAssociatedPersons() {
		return associatedPersons;
	}

	public void setAssociatedPersons(List<String> associatedPersons) {
		this.associatedPersons = associatedPersons;
	}

	public GluuBoolean getOxAuthTrustedClient() {
		return oxAuthTrustedClient;
	}

	public void setOxAuthTrustedClient(GluuBoolean oxAuthTrustedClient) {
		this.oxAuthTrustedClient = oxAuthTrustedClient;
	}

	public ResponseType[] getResponseTypes() {
		return responseTypes;
	}

	public void setResponseTypes(ResponseType[] responseTypes) {
		this.responseTypes = responseTypes;
	}

	public GrantType[] getGrantTypes() {
		return grantTypes;
	}

	public void setGrantTypes(GrantType[] grantTypes) {
		this.grantTypes = grantTypes;
	}

	public String getLogoUri() {
		return logoUri;
	}

	public void setLogoUri(String logoUri) {
		this.logoUri = logoUri;
	}

	public String getClientUri() {
		return clientUri;
	}

	public void setClientUri(String clientUri) {
		this.clientUri = clientUri;
	}

	public String getPolicyUri() {
		return policyUri;
	}

	public void setPolicyUri(String policyUri) {
		this.policyUri = policyUri;
	}

	public String getTosUri() {
		return tosUri;
	}

	public void setTosUri(String tosUri) {
		this.tosUri = tosUri;
	}

	public String getJwksUri() {
		return jwksUri;
	}

	public void setJwksUri(String jwksUri) {
		this.jwksUri = jwksUri;
	}

	public String getJwks() {
		return jwks;
	}

	public void setJwks(String jwks) {
		this.jwks = jwks;
	}

	public String getSectorIdentifierUri() {
		return sectorIdentifierUri;
	}

	public void setSectorIdentifierUri(String sectorIdentifierUri) {
		this.sectorIdentifierUri = sectorIdentifierUri;
	}

	public OxAuthSubjectType getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(OxAuthSubjectType subjectType) {
		this.subjectType = subjectType;
	}

	public String getIdTokenTokenBindingCnf() {
		return idTokenTokenBindingCnf;
	}

	public void setIdTokenTokenBindingCnf(String idTokenTokenBindingCnf) {
		this.idTokenTokenBindingCnf = idTokenTokenBindingCnf;
	}

	public GluuBoolean getRptAsJwt() {
		return rptAsJwt;
	}

	public void setRptAsJwt(GluuBoolean rptAsJwt) {
		this.rptAsJwt = rptAsJwt;
	}

	public GluuBoolean getAccessTokenAsJwt() {
		return accessTokenAsJwt;
	}

	public void setAccessTokenAsJwt(GluuBoolean accessTokenAsJwt) {
		this.accessTokenAsJwt = accessTokenAsJwt;
	}

	public SignatureAlgorithm getAccessTokenSigningAlg() {
		return accessTokenSigningAlg;
	}

	public void setAccessTokenSigningAlg(SignatureAlgorithm accessTokenSigningAlg) {
		this.accessTokenSigningAlg = accessTokenSigningAlg;
	}

	public SignatureAlgorithm getIdTokenSignedResponseAlg() {
		return idTokenSignedResponseAlg;
	}

	public void setIdTokenSignedResponseAlg(SignatureAlgorithm idTokenSignedResponseAlg) {
		this.idTokenSignedResponseAlg = idTokenSignedResponseAlg;
	}

	public KeyEncryptionAlgorithm getIdTokenEncryptedResponseAlg() {
		return idTokenEncryptedResponseAlg;
	}

	public void setIdTokenEncryptedResponseAlg(KeyEncryptionAlgorithm idTokenEncryptedResponseAlg) {
		this.idTokenEncryptedResponseAlg = idTokenEncryptedResponseAlg;
	}

	public BlockEncryptionAlgorithm getIdTokenEncryptedResponseEnc() {
		return idTokenEncryptedResponseEnc;
	}

	public void setIdTokenEncryptedResponseEnc(BlockEncryptionAlgorithm idTokenEncryptedResponseEnc) {
		this.idTokenEncryptedResponseEnc = idTokenEncryptedResponseEnc;
	}

	public SignatureAlgorithm getUserInfoSignedResponseAlg() {
		return userInfoSignedResponseAlg;
	}

	public void setUserInfoSignedResponseAlg(SignatureAlgorithm userInfoSignedResponseAlg) {
		this.userInfoSignedResponseAlg = userInfoSignedResponseAlg;
	}

	public KeyEncryptionAlgorithm getUserInfoEncryptedResponseAlg() {
		return userInfoEncryptedResponseAlg;
	}

	public void setUserInfoEncryptedResponseAlg(KeyEncryptionAlgorithm userInfoEncryptedResponseAlg) {
		this.userInfoEncryptedResponseAlg = userInfoEncryptedResponseAlg;
	}

	public BlockEncryptionAlgorithm getUserInfoEncryptedResponseEnc() {
		return userInfoEncryptedResponseEnc;
	}

	public void setUserInfoEncryptedResponseEnc(BlockEncryptionAlgorithm userInfoEncryptedResponseEnc) {
		this.userInfoEncryptedResponseEnc = userInfoEncryptedResponseEnc;
	}

	public SignatureAlgorithm getRequestObjectSigningAlg() {
		return requestObjectSigningAlg;
	}

	public void setRequestObjectSigningAlg(SignatureAlgorithm requestObjectSigningAlg) {
		this.requestObjectSigningAlg = requestObjectSigningAlg;
	}

	public KeyEncryptionAlgorithm getRequestObjectEncryptionAlg() {
		return requestObjectEncryptionAlg;
	}

	public void setRequestObjectEncryptionAlg(KeyEncryptionAlgorithm requestObjectEncryptionAlg) {
		this.requestObjectEncryptionAlg = requestObjectEncryptionAlg;
	}

	public BlockEncryptionAlgorithm getRequestObjectEncryptionEnc() {
		return requestObjectEncryptionEnc;
	}

	public void setRequestObjectEncryptionEnc(BlockEncryptionAlgorithm requestObjectEncryptionEnc) {
		this.requestObjectEncryptionEnc = requestObjectEncryptionEnc;
	}

	public SignatureAlgorithm getTokenEndpointAuthSigningAlg() {
		return tokenEndpointAuthSigningAlg;
	}

	public void setTokenEndpointAuthSigningAlg(SignatureAlgorithm tokenEndpointAuthSigningAlg) {
		this.tokenEndpointAuthSigningAlg = tokenEndpointAuthSigningAlg;
	}

	public Integer getDefaultMaxAge() {
		return defaultMaxAge;
	}

	public void setDefaultMaxAge(Integer defaultMaxAge) {
		this.defaultMaxAge = defaultMaxAge;
	}

	public GluuBoolean getRequireAuthTime() {
		return requireAuthTime;
	}

	public void setRequireAuthTime(GluuBoolean requireAuthTime) {
		this.requireAuthTime = requireAuthTime;
	}

	public AuthenticationMethod getTokenEndpointAuthMethod() {
		return tokenEndpointAuthMethod;
	}

	public void setTokenEndpointAuthMethod(AuthenticationMethod tokenEndpointAuthMethod) {
		this.tokenEndpointAuthMethod = tokenEndpointAuthMethod;
	}

	public String[] getPostLogoutRedirectUris() {
		return postLogoutRedirectUris;
	}

	public void setPostLogoutRedirectUris(String[] postLogoutRedirectUris) {
		this.postLogoutRedirectUris = postLogoutRedirectUris;
	}

	public List<String> getLogoutUri() {
		return logoutUri;
	}

	public void setLogoutUri(List<String> logoutUri) {
		this.logoutUri = logoutUri;
	}

	public GluuBoolean getLogoutSessionRequired() {
		return logoutSessionRequired;
	}

	public void setLogoutSessionRequired(GluuBoolean logoutSessionRequired) {
		this.logoutSessionRequired = logoutSessionRequired;
	}

	public GluuBoolean getOxAuthPersistClientAuthorizations() {
		return oxAuthPersistClientAuthorizations;
	}

	public void setOxAuthPersistClientAuthorizations(GluuBoolean oxAuthPersistClientAuthorizations) {
		this.oxAuthPersistClientAuthorizations = oxAuthPersistClientAuthorizations;
	}

	public GluuBoolean getOxIncludeClaimsInIdToken() {
		return oxIncludeClaimsInIdToken;
	}

	public void setOxIncludeClaimsInIdToken(GluuBoolean oxIncludeClaimsInIdToken) {
		this.oxIncludeClaimsInIdToken = oxIncludeClaimsInIdToken;
	}

	public Integer getOxRefreshTokenLifetime() {
		return oxRefreshTokenLifetime;
	}

	public void setOxRefreshTokenLifetime(Integer oxRefreshTokenLifetime) {
		this.oxRefreshTokenLifetime = oxRefreshTokenLifetime;
	}

	public Integer getAccessTokenLifetime() {
		return accessTokenLifetime;
	}

	public void setAccessTokenLifetime(Integer accessTokenLifetime) {
		this.accessTokenLifetime = accessTokenLifetime;
	}

	public String[] getDefaultAcrValues() {
		return defaultAcrValues;
	}

	public void setDefaultAcrValues(String[] defaultAcrValues) {
		this.defaultAcrValues = defaultAcrValues;
	}

	public String getInitiateLoginUri() {
		return initiateLoginUri;
	}

	public void setInitiateLoginUri(String initiateLoginUri) {
		this.initiateLoginUri = initiateLoginUri;
	}

	public String[] getRequestUris() {
		return requestUris;
	}

	public void setRequestUris(String[] requestUris) {
		this.requestUris = requestUris;
	}

	public String[] getAuthorizedOrigins() {
		return authorizedOrigins;
	}

	public void setAuthorizedOrigins(String[] authorizedOrigins) {
		this.authorizedOrigins = authorizedOrigins;
	}

	public String getSoftwareId() {
		return softwareId;
	}

	public void setSoftwareId(String softwareId) {
		this.softwareId = softwareId;
	}

	public String getSoftwareVersion() {
		return softwareVersion;
	}

	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}

	public String getSoftwareStatement() {
		return softwareStatement;
	}

	public void setSoftwareStatement(String softwareStatement) {
		this.softwareStatement = softwareStatement;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public void setOxAuthClientSecret(String oxAuthClientSecret) {
		this.oxAuthClientSecret = oxAuthClientSecret;
	}

	public String getOxAuthClientSecret() {
		return oxAuthClientSecret;
	}

	public Date getClientSecretExpiresAt() {
		if (clientSecretExpiresAt == null) {
			return inOneCentury();
		} else {
			return clientSecretExpiresAt;
		}
	}

	public void setClientSecretExpiresAt(Date clientSecretExpiresAt) {
		this.clientSecretExpiresAt = clientSecretExpiresAt;
	}

	public final String getOxdId() {
		return oxdId;
	}

	public final void setOxdId(String oxdId) {
		this.oxdId = oxdId;
	}

	private Date inOneCentury() {
		LocalDate nextCentury = LocalDate.now().plusYears(100);
		return Date.from(nextCentury.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public ClientAttributes getAttributes() {
		if (attributes == null) {
			attributes = new ClientAttributes();
		}
		return attributes;
	}

	public void setAttributes(ClientAttributes attributes) {
		this.attributes = attributes;
	}

}