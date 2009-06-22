package com.makeabyte.appstore.server.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AppstoreAppCategoryMapId implements java.io.Serializable {

	private static final long serialVersionUID = 2309041003062083841L;
	private long appId;
	private long categoryId;

	public AppstoreAppCategoryMapId() {
	}

	public AppstoreAppCategoryMapId(long appId, long categoryId) {
		this.appId = appId;
		this.categoryId = categoryId;
	}

	@Column(name = "appId", nullable = false)
	public long getAppId() {
		return this.appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	@Column(name = "categoryId", nullable = false)
	public long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AppstoreAppCategoryMapId))
			return false;
		AppstoreAppCategoryMapId castOther = (AppstoreAppCategoryMapId) other;

		return (this.getAppId() == castOther.getAppId())
				&& (this.getCategoryId() == castOther.getCategoryId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getAppId();
		result = 37 * result + (int) this.getCategoryId();
		return result;
	}

}
