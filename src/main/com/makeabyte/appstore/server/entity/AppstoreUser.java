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

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "appstore_user")
public class AppstoreUser implements java.io.Serializable {

	private static final long serialVersionUID = -8876023432963929940L;
	private Long id;
	private String username;
	private String password;
	private Date lastLogin;
	private Date created;
	private String apiKey;
	private boolean apiEnabled;
	private Set<AppstoreUserRoleMap> appstoreUserRoleMaps = new HashSet<AppstoreUserRoleMap>(0);
	private Set<AppstoreUserPlatformMap> appstoreUserPlatformMaps = new HashSet<AppstoreUserPlatformMap>(0);
	private Set<AppstoreUserAppMap> appstoreUserAppMaps = new HashSet<AppstoreUserAppMap>(0);

	public AppstoreUser() {
	}

	public AppstoreUser(String username, String password, Date lastLogin,
			Date created, boolean apiEnabled) {
		this.username = username;
		this.password = password;
		this.lastLogin = lastLogin;
		this.created = created;
		this.apiEnabled = apiEnabled;
	}
	public AppstoreUser(String username, String password, Date lastLogin,
			Date created, String apiKey, boolean apiEnabled,
			Set<AppstoreUserRoleMap> appstoreUserRoleMaps,
			Set<AppstoreUserPlatformMap> appstoreUserPlatformMaps,
			Set<AppstoreUserAppMap> appstoreUserAppMaps) {
		this.username = username;
		this.password = password;
		this.lastLogin = lastLogin;
		this.created = created;
		this.apiKey = apiKey;
		this.apiEnabled = apiEnabled;
		this.appstoreUserRoleMaps = appstoreUserRoleMaps;
		this.appstoreUserPlatformMaps = appstoreUserPlatformMaps;
		this.appstoreUserAppMaps = appstoreUserAppMaps;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "username", nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 128)
	@NotNull
	@Length(max = 128)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastLogin", length = 19)
	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false, length = 19)
	@NotNull
	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "apiKey", length = 64)
	@Length(max = 64)
	public String getApiKey() {
		return this.apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	@Column(name = "apiEnabled", nullable = false)
	public boolean isApiEnabled() {
		return this.apiEnabled;
	}

	public void setApiEnabled(boolean apiEnabled) {
		this.apiEnabled = apiEnabled;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstoreUser")
	public Set<AppstoreUserRoleMap> getAppstoreUserRoleMaps() {
		return this.appstoreUserRoleMaps;
	}

	public void setAppstoreUserRoleMaps(
			Set<AppstoreUserRoleMap> appstoreUserRoleMaps) {
		this.appstoreUserRoleMaps = appstoreUserRoleMaps;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstoreUser")
	public Set<AppstoreUserPlatformMap> getAppstoreUserPlatformMaps() {
		return this.appstoreUserPlatformMaps;
	}

	public void setAppstoreUserPlatformMaps(
			Set<AppstoreUserPlatformMap> appstoreUserPlatformMaps) {
		this.appstoreUserPlatformMaps = appstoreUserPlatformMaps;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstoreUser")
	public Set<AppstoreUserAppMap> getAppstoreUserAppMaps() {
		return this.appstoreUserAppMaps;
	}

	public void setAppstoreUserAppMaps(
			Set<AppstoreUserAppMap> appstoreUserAppMaps) {
		this.appstoreUserAppMaps = appstoreUserAppMaps;
	}

}
