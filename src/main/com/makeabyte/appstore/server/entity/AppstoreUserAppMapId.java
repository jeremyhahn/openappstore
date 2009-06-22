package com.makeabyte.appstore.server.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AppstoreUserAppMapId implements java.io.Serializable {

	private static final long serialVersionUID = -7429968198597573705L;
	private long appId;
	private long userId;

	public AppstoreUserAppMapId() {
	}

	public AppstoreUserAppMapId(long appId, long userId) {
		this.appId = appId;
		this.userId = userId;
	}

	@Column(name = "appId", nullable = false)
	public long getAppId() {
		return this.appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
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
		if (!(other instanceof AppstoreUserAppMapId))
			return false;
		AppstoreUserAppMapId castOther = (AppstoreUserAppMapId) other;

		return (this.getAppId() == castOther.getAppId())
				&& (this.getUserId() == castOther.getUserId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getAppId();
		result = 37 * result + (int) this.getUserId();
		return result;
	}

}
