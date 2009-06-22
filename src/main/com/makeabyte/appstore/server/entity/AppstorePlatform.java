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

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "appstore_platform")
public class AppstorePlatform implements java.io.Serializable {

	private static final long serialVersionUID = 1356707524814209775L;

	private Long id;
	private String name;
	private String description;
	private Set<AppstoreCategoryPlatformMap> appstoreCategoryPlatformMaps = new HashSet<AppstoreCategoryPlatformMap>(0);
	private Set<AppstoreAppType> appstoreAppTypes = new HashSet<AppstoreAppType>(0);
	private Set<AppstoreAppPlatformMap> appstoreAppPlatformMaps = new HashSet<AppstoreAppPlatformMap>(0);
	private Set<AppstoreUserPlatformMap> appstoreUserPlatformMaps = new HashSet<AppstoreUserPlatformMap>(0);

	public AppstorePlatform() {
	}

	public AppstorePlatform(String name, String description) {
		this.name = name;
		this.description = description;
	}
	public AppstorePlatform(String name, String description,
			Set<AppstoreCategoryPlatformMap> appstoreCategoryPlatformMaps,
			Set<AppstoreAppType> appstoreAppTypes,
			Set<AppstoreAppPlatformMap> appstoreAppPlatformMaps,
			Set<AppstoreUserPlatformMap> appstoreUserPlatformMaps) {
		this.name = name;
		this.description = description;
		this.appstoreCategoryPlatformMaps = appstoreCategoryPlatformMaps;
		this.appstoreAppTypes = appstoreAppTypes;
		this.appstoreAppPlatformMaps = appstoreAppPlatformMaps;
		this.appstoreUserPlatformMaps = appstoreUserPlatformMaps;
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

	@Column(name = "name", nullable = false)
	@NotNull
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", nullable = false, length = 65535)
	@NotNull
	@Length(max = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstorePlatform")
	public Set<AppstoreCategoryPlatformMap> getAppstoreCategoryPlatformMaps() {
		return this.appstoreCategoryPlatformMaps;
	}

	public void setAppstoreCategoryPlatformMaps(
			Set<AppstoreCategoryPlatformMap> appstoreCategoryPlatformMaps) {
		this.appstoreCategoryPlatformMaps = appstoreCategoryPlatformMaps;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstorePlatform")
	public Set<AppstoreAppType> getAppstoreAppTypes() {
		return this.appstoreAppTypes;
	}

	public void setAppstoreAppTypes(Set<AppstoreAppType> appstoreAppTypes) {
		this.appstoreAppTypes = appstoreAppTypes;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstorePlatform")
	public Set<AppstoreAppPlatformMap> getAppstoreAppPlatformMaps() {
		return this.appstoreAppPlatformMaps;
	}

	public void setAppstoreAppPlatformMaps(
			Set<AppstoreAppPlatformMap> appstoreAppPlatformMaps) {
		this.appstoreAppPlatformMaps = appstoreAppPlatformMaps;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstorePlatform")
	public Set<AppstoreUserPlatformMap> getAppstoreUserPlatformMaps() {
		return this.appstoreUserPlatformMaps;
	}

	public void setAppstoreUserPlatformMaps(
			Set<AppstoreUserPlatformMap> appstoreUserPlatformMaps) {
		this.appstoreUserPlatformMaps = appstoreUserPlatformMaps;
	}

}
