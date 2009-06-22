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

import com.makeabyte.appstore.server.entity.AppstoreUser;
import com.makeabyte.appstore.server.entity.AppstoreUserAppMap;
import com.makeabyte.appstore.server.entity.AppstoreUserRoleMap;
import com.makeabyte.appstore.server.entity.AppstoreUserPlatformMap;

@Name("appstoreUserHome")
public class AppstoreUserHome extends EntityHome<AppstoreUser> {

	private static final long serialVersionUID = -2870392746043176884L;

	public void setAppstoreUserId(Long id) {
		setId(id);
	}

	public Long getAppstoreUserId() {
		return (Long) getId();
	}

	@Override
	protected AppstoreUser createInstance() {
		AppstoreUser appstoreUser = new AppstoreUser();
		return appstoreUser;
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

	public AppstoreUser getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<AppstoreUserAppMap> getAppstoreUserAppMaps() {
		return getInstance() == null
				? null
				: new ArrayList<AppstoreUserAppMap>(getInstance()
						.getAppstoreUserAppMaps());
	}
	public List<AppstoreUserPlatformMap> getAppstoreUserPlatformMaps() {
		return getInstance() == null
				? null
				: new ArrayList<AppstoreUserPlatformMap>(getInstance()
						.getAppstoreUserPlatformMaps());
	}
	public List<AppstoreUserRoleMap> getAppstoreUserRoleMaps() {
		return getInstance() == null
				? null
				: new ArrayList<AppstoreUserRoleMap>(getInstance()
						.getAppstoreUserRoleMaps());
	}

}
