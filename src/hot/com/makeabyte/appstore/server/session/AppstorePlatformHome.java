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

import com.makeabyte.appstore.server.entity.AppstoreAppType;
import com.makeabyte.appstore.server.entity.AppstorePlatform;
import com.makeabyte.appstore.server.entity.AppstoreAppPlatformMap;
import com.makeabyte.appstore.server.entity.AppstoreUserPlatformMap;
import com.makeabyte.appstore.server.entity.AppstoreCategoryPlatformMap;

@Name("appstorePlatformHome")
public class AppstorePlatformHome extends EntityHome<AppstorePlatform> {

	private static final long serialVersionUID = -4400985032441421396L;
	public void setAppstorePlatformId(Long id) {
		setId(id);
	}

	public Long getAppstorePlatformId() {
		return (Long) getId();
	}

	@Override
	protected AppstorePlatform createInstance() {
		AppstorePlatform appstorePlatform = new AppstorePlatform();
		return appstorePlatform;
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

	public AppstorePlatform getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<AppstoreAppPlatformMap> getAppstoreAppPlatformMaps() {
		return getInstance() == null
				? null
				: new ArrayList<AppstoreAppPlatformMap>(getInstance()
						.getAppstoreAppPlatformMaps());
	}
	public List<AppstoreAppType> getAppstoreAppTypes() {
		return getInstance() == null ? null : new ArrayList<AppstoreAppType>(
				getInstance().getAppstoreAppTypes());
	}
	public List<AppstoreCategoryPlatformMap> getAppstoreCategoryPlatformMaps() {
		return getInstance() == null
				? null
				: new ArrayList<AppstoreCategoryPlatformMap>(getInstance()
						.getAppstoreCategoryPlatformMaps());
	}
	public List<AppstoreUserPlatformMap> getAppstoreUserPlatformMaps() {
		return getInstance() == null
				? null
				: new ArrayList<AppstoreUserPlatformMap>(getInstance()
						.getAppstoreUserPlatformMaps());
	}

}
