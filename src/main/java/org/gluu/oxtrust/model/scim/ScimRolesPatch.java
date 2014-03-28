package org.gluu.oxtrust.model.scim;

/**
 * SCIM person Patch roles
 * 
 * @author Reda Zerrad Date: 04.25.2012
 */

public class ScimRolesPatch extends ScimRoles {
	private String operation;

	public String getOperation() {
		return this.operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
}
