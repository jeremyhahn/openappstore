package com.makeabyte.appstore.server.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AppstoreAppPlatformMapId implements java.io.Serializable {

	private static final long serialVersionUID = 1009212196807514759L;
	private long appId;
	private long platformId;

	public AppstoreAppPlatformMapId() {
	}

	public AppstoreAppPlatformMapId(long appId, long platformId) {
		this.appId = appId;
		this.platformId = platformId;
	}

	@Column(name = "appId", nullable = false)
	public long getAppId() {
		return this.appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
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
		if (!(other instanceof AppstoreAppPlatformMapId))
			return false;
		AppstoreAppPlatformMapId castOther = (AppstoreAppPlatformMapId) other;

		return (this.getAppId() == castOther.getAppId())
				&& (this.getPlatformId() == castOther.getPlatformId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getAppId();
		result = 37 * result + (int) this.getPlatformId();
		return result;
	}

}
