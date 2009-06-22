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
package com.makeabyte.appstore.server.session;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import com.makeabyte.appstore.server.entity.AppstoreCategory;
import com.makeabyte.appstore.server.entity.AppstoreAppCategoryMap;
import com.makeabyte.appstore.server.entity.AppstoreCategoryPlatformMap;

@Name("appstoreCategoryHome")
public class AppstoreCategoryHome extends EntityHome<AppstoreCategory> {

	private static final long serialVersionUID = 7625980172261108129L;

	public void setAppstoreCategoryId(Long id) {
		setId(id);
	}

	public Long getAppstoreCategoryId() {
		return (Long) getId();
	}

	@Override
	protected AppstoreCategory createInstance() {
		AppstoreCategory appstoreCategory = new AppstoreCategory();
		return appstoreCategory;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
	}

	public boolean isWired() {
		return true;
	}

	public AppstoreCategory getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<AppstoreAppCategoryMap> getAppstoreAppCategoryMaps() {
		return getInstance() == null
				? null
				: new ArrayList<AppstoreAppCategoryMap>(getInstance()
						.getAppstoreAppCategoryMaps());
	}
	public List<AppstoreCategoryPlatformMap> getAppstoreCategoryPlatformMaps() {
		return getInstance() == null
				? null
				: new ArrayList<AppstoreCategoryPlatformMap>(getInstance()
						.getAppstoreCategoryPlatformMaps());
	}

}
