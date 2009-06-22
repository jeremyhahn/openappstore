/***********************************************************************
# OpenAppstore: Enterprise Open Source Application Store
# Copyright (C) 2007-2009  Make A Byte, inc
# www.makeabyte.com
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
*************************************************************************/
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
