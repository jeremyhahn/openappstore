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
public class AppstoreUserRoleMapId implements java.io.Serializable {

	private static final long serialVersionUID = -1314841298324398598L;
	private long userId;
	private long roleId;

	public AppstoreUserRoleMapId() {
	}

	public AppstoreUserRoleMapId(long userId, long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	@Column(name = "userId", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "roleId", nullable = false)
	public long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AppstoreUserRoleMapId))
			return false;
		AppstoreUserRoleMapId castOther = (AppstoreUserRoleMapId) other;

		return (this.getUserId() == castOther.getUserId())
				&& (this.getRoleId() == castOther.getRoleId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getUserId();
		result = 37 * result + (int) this.getRoleId();
		return result;
	}

}
