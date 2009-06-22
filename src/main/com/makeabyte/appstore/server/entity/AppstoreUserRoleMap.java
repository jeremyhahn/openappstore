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
@Table(name = "appstore_user_role_map")
public class AppstoreUserRoleMap implements java.io.Serializable {

	private static final long serialVersionUID = -3571913599591576742L;
	private AppstoreUserRoleMapId id;
	private AppstoreUserRole appstoreUserRole;
	private AppstoreUser appstoreUser;

	public AppstoreUserRoleMap() {
	}

	public AppstoreUserRoleMap(AppstoreUserRoleMapId id,
			AppstoreUserRole appstoreUserRole, AppstoreUser appstoreUser) {
		this.id = id;
		this.appstoreUserRole = appstoreUserRole;
		this.appstoreUser = appstoreUser;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "userId", column = @Column(name = "userId", nullable = false)),
			@AttributeOverride(name = "roleId", column = @Column(name = "roleId", nullable = false))})
	@NotNull
	public AppstoreUserRoleMapId getId() {
		return this.id;
	}

	public void setId(AppstoreUserRoleMapId id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "roleId", nullable = false, insertable = false, updatable = false)
	@NotNull
	public AppstoreUserRole getAppstoreUserRole() {
		return this.appstoreUserRole;
	}

	public void setAppstoreUserRole(AppstoreUserRole appstoreUserRole) {
		this.appstoreUserRole = appstoreUserRole;
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

}
