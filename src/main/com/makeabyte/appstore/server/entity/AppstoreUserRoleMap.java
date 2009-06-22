package com.makeabyte.appstore.server.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.NotNull;

@Entity
@Table(name = "appstore_user_role_map")
public class AppstoreUserRoleMap implements java.io.Serializable {

	private static final long serialVersionUID = -3571913599591576742L;
	private AppstoreUserRoleMapId id;
	private AppstoreUserRole appstoreUserRole;
	private AppstoreUser appstoreUser;

	public AppstoreUserRoleMap() {
	}

	public AppstoreUserRoleMap(AppstoreUserRoleMapId id,
			AppstoreUserRole appstoreUserRole, AppstoreUser appstoreUser) {
		this.id = id;
		this.appstoreUserRole = appstoreUserRole;
		this.appstoreUser = appstoreUser;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "userId", nullable = false)),
			@AttributeOverride(name = "roleId", column = @Column(name = "roleId", nullable = false))})
	@NotNull
	public AppstoreUserRoleMapId getId() {
		return this.id;
	}

	public void setId(AppstoreUserRoleMapId id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", nullable = false, insertable = false, updatable = false)
	@NotNull
	public AppstoreUserRole getAppstoreUserRole() {
		return this.appstoreUserRole;
	}

	public void setAppstoreUserRole(AppstoreUserRole appstoreUserRole) {
		this.appstoreUserRole = appstoreUserRole;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
	@NotNull
	public AppstoreUser getAppstoreUser() {
		return this.appstoreUser;
	}

	public void setAppstoreUser(AppstoreUser appstoreUser) {
		this.appstoreUser = appstoreUser;
	}

}
