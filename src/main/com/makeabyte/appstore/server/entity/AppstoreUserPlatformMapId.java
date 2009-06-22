package com.makeabyte.appstore.server.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AppstoreUserPlatformMapId implements java.io.Serializable {

	private static final long serialVersionUID = -2356918459648229571L;
	private long platformId;
	private long userId;

	public AppstoreUserPlatformMapId() {
	}

	public AppstoreUserPlatformMapId(long platformId, long userId) {
		this.platformId = platformId;
		this.userId = userId;
	}

	@Column(name = "platformId", nullable = false)
	public long getPlatformId() {
		return this.platformId;
	}

	public void setPlatformId(long platformId) {
		this.platformId = platformId;
	}

	@Column(name = "userId", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AppstoreUserPlatformMapId))
			return false;
		AppstoreUserPlatformMapId castOther = (AppstoreUserPlatformMapId) other;

		return (this.getPlatformId() == castOther.getPlatformId())
				&& (this.getUserId() == castOther.getUserId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getPlatformId();
		result = 37 * result + (int) this.getUserId();
		return result;
	}

}
