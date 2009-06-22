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
@Table(name = "appstore_category_platform_map")
public class AppstoreCategoryPlatformMap implements java.io.Serializable {

	private static final long serialVersionUID = 3001832992891978895L;
	private AppstoreCategoryPlatformMapId id;
	private AppstoreCategory appstoreCategory;
	private AppstorePlatform appstorePlatform;

	public AppstoreCategoryPlatformMap() {
	}

	public AppstoreCategoryPlatformMap(AppstoreCategoryPlatformMapId id,
			AppstoreCategory appstoreCategory, AppstorePlatform appstorePlatform) {
		this.id = id;
		this.appstoreCategory = appstoreCategory;
		this.appstorePlatform = appstorePlatform;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "categoryId", column = @Column(name = "categoryId", nullable = false)),
			@AttributeOverride(name = "platformId", column = @Column(name = "platformId", nullable = false))})
	@NotNull
	public AppstoreCategoryPlatformMapId getId() {
		return this.id;
	}

	public void setId(AppstoreCategoryPlatformMapId id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", nullable = false, insertable = false, updatable = false)
	@NotNull
	public AppstoreCategory getAppstoreCategory() {
		return this.appstoreCategory;
	}

	public void setAppstoreCategory(AppstoreCategory appstoreCategory) {
		this.appstoreCategory = appstoreCategory;
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
