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
@Table(name = "appstore_app_category_map")
public class AppstoreAppCategoryMap implements java.io.Serializable {

	private static final long serialVersionUID = -229880033003584608L;
	private AppstoreAppCategoryMapId id;
	private AppstoreCategory appstoreCategory;
	private AppstoreApp appstoreApp;

	public AppstoreAppCategoryMap() {
	}

	public AppstoreAppCategoryMap(AppstoreAppCategoryMapId id,
			AppstoreCategory appstoreCategory, AppstoreApp appstoreApp) {
		this.id = id;
		this.appstoreCategory = appstoreCategory;
		this.appstoreApp = appstoreApp;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "appId", column = @Column(name = "appId", nullable = false)),
			@AttributeOverride(name = "categoryId", column = @Column(name = "categoryId", nullable = false))})
	@NotNull
	public AppstoreAppCategoryMapId getId() {
		return this.id;
	}

	public void setId(AppstoreAppCategoryMapId id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", nullable = false, insertable = false, updatable = false)
	@NotNull
	public AppstoreCategory getAppstoreCategory() {
		return this.appstoreCategory;
	}

	public void setAppstoreCategory(AppstoreCategory appstoreCategory) {
		this.appstoreCategory = appstoreCategory;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "appId", nullable = false, insertable = false, updatable = false)
	@NotNull
	public AppstoreApp getAppstoreApp() {
		return this.appstoreApp;
	}

	public void setAppstoreApp(AppstoreApp appstoreApp) {
		this.appstoreApp = appstoreApp;
	}

}
