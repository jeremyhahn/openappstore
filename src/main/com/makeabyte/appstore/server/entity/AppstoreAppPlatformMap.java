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
@Table(name = "appstore_app_platform_map")
public class AppstoreAppPlatformMap implements java.io.Serializable {

	private static final long serialVersionUID = -5665139452592757598L;
	private AppstoreAppPlatformMapId id;
	private AppstoreApp appstoreApp;
	private AppstorePlatform appstorePlatform;

	public AppstoreAppPlatformMap() {
	}

	public AppstoreAppPlatformMap(AppstoreAppPlatformMapId id,
			AppstoreApp appstoreApp, AppstorePlatform appstorePlatform) {
		this.id = id;
		this.appstoreApp = appstoreApp;
		this.appstorePlatform = appstorePlatform;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "appId", column = @Column(name = "appId", nullable = false)),
			@AttributeOverride(name = "platformId", column = @Column(name = "platformId", nullable = false))})
	@NotNull
	public AppstoreAppPlatformMapId getId() {
		return this.id;
	}

	public void setId(AppstoreAppPlatformMapId id) {
		this.id = id;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "platformId", nullable = false, insertable = false, updatable = false)
	@NotNull
	public AppstorePlatform getAppstorePlatform() {
		return this.appstorePlatform;
	}

	public void setAppstorePlatform(AppstorePlatform appstorePlatform) {
		this.appstorePlatform = appstorePlatform;
	}

}
