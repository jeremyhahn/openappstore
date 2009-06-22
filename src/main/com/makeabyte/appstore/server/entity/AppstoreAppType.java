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

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "appstore_app_type")
public class AppstoreAppType implements java.io.Serializable {

	private static final long serialVersionUID = 3305901593025377009L;
	private Long id;
	private AppstorePlatform appstorePlatform;
	private String name;
	private String description;
	private Set<AppstoreApp> appstoreApps = new HashSet<AppstoreApp>(0);

	public AppstoreAppType() {
	}

	public AppstoreAppType(AppstorePlatform appstorePlatform, String name) {
		this.appstorePlatform = appstorePlatform;
		this.name = name;
	}
	public AppstoreAppType(AppstorePlatform appstorePlatform, String name,
			String description, Set<AppstoreApp> appstoreApps) {
		this.appstorePlatform = appstorePlatform;
		this.name = name;
		this.description = description;
		this.appstoreApps = appstoreApps;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "platformId", nullable = false)
	@NotNull
	public AppstorePlatform getAppstorePlatform() {
		return this.appstorePlatform;
	}

	public void setAppstorePlatform(AppstorePlatform appstorePlatform) {
		this.appstorePlatform = appstorePlatform;
	}

	@Column(name = "name", nullable = false)
	@NotNull
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 65535)
	@Length(max = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstoreAppType")
	public Set<AppstoreApp> getAppstoreApps() {
		return this.appstoreApps;
	}

	public void setAppstoreApps(Set<AppstoreApp> appstoreApps) {
		this.appstoreApps = appstoreApps;
	}

}
