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
@Table(name = "appstore_user_app_map")
public class AppstoreUserAppMap implements java.io.Serializable {

	private static final long serialVersionUID = -4318052185743359219L;
	private AppstoreUserAppMapId id;
	private AppstoreUser appstoreUser;
	private AppstoreApp appstoreApp;

	public AppstoreUserAppMap() {
	}

	public AppstoreUserAppMap(AppstoreUserAppMapId id,
			AppstoreUser appstoreUser, AppstoreApp appstoreApp) {
		this.id = id;
		this.appstoreUser = appstoreUser;
		this.appstoreApp = appstoreApp;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "appId", column = @Column(name = "appId", nullable = false)),
			@AttributeOverride(name = "userId", column = @Column(name = "userId", nullable = false))})
	@NotNull
	public AppstoreUserAppMapId getId() {
		return this.id;
	}

	public void setId(AppstoreUserAppMapId id) {
		this.id = id;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appId", nullable = false, insertable = false, updatable = false)
	@NotNull
	public AppstoreApp getAppstoreApp() {
		return this.appstoreApp;
	}

	public void setAppstoreApp(AppstoreApp appstoreApp) {
		this.appstoreApp = appstoreApp;
	}

}
