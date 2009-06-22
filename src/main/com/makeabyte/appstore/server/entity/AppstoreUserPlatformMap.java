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
@Table(name = "appstore_user_platform_map")
public class AppstoreUserPlatformMap implements java.io.Serializable {

	private static final long serialVersionUID = -409979330007870785L;
	private AppstoreUserPlatformMapId id;
	private AppstoreUser appstoreUser;
	private AppstorePlatform appstorePlatform;

	public AppstoreUserPlatformMap() {
	}

	public AppstoreUserPlatformMap(AppstoreUserPlatformMapId id,
			AppstoreUser appstoreUser, AppstorePlatform appstorePlatform) {
		this.id = id;
		this.appstoreUser = appstoreUser;
		this.appstorePlatform = appstorePlatform;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "platformId", column = @Column(name = "platformId", nullable = false)),
			@AttributeOverride(name = "userId", column = @Column(name = "userId", nullable = false))})
	@NotNull
	public AppstoreUserPlatformMapId getId() {
		return this.id;
	}

	public void setId(AppstoreUserPlatformMapId id) {
		this.id = id;
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
