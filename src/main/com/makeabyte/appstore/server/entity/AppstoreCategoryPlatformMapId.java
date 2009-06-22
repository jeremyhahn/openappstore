package com.makeabyte.appstore.server.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AppstoreCategoryPlatformMapId implements java.io.Serializable {

	private static final long serialVersionUID = 2199667391495130905L;
	private long categoryId;
	private long platformId;

	public AppstoreCategoryPlatformMapId() {
	}

	public AppstoreCategoryPlatformMapId(long categoryId, long platformId) {
		this.categoryId = categoryId;
		this.platformId = platformId;
	}

	@Column(name = "categoryId", nullable = false)
	public long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "platformId", nullable = false)
	public long getPlatformId() {
		return this.platformId;
	}

	public void setPlatformId(long platformId) {
		this.platformId = platformId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AppstoreCategoryPlatformMapId))
			return false;
		AppstoreCategoryPlatformMapId castOther = (AppstoreCategoryPlatformMapId) other;

		return (this.getCategoryId() == castOther.getCategoryId())
				&& (this.getPlatformId() == castOther.getPlatformId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getCategoryId();
		result = 37 * result + (int) this.getPlatformId();
		return result;
	}

}
