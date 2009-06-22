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
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import com.makeabyte.appstore.server.entity.AppstoreApp;
import com.makeabyte.appstore.server.entity.AppstoreAppType;
import com.makeabyte.appstore.server.entity.AppstorePlatform;

@Name("appstoreAppTypeHome")
public class AppstoreAppTypeHome extends EntityHome<AppstoreAppType> {

	private static final long serialVersionUID = 7744618471300050190L;

	@In(create = true)
	AppstorePlatformHome appstorePlatformHome;

	public void setAppstoreAppTypeId(Long id) {
		setId(id);
	}

	public Long getAppstoreAppTypeId() {
		return (Long) getId();
	}

	@Override
	protected AppstoreAppType createInstance() {
		AppstoreAppType appstoreAppType = new AppstoreAppType();
		return appstoreAppType;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		AppstorePlatform appstorePlatform = appstorePlatformHome.getDefinedInstance();
		if (appstorePlatform != null) {
			getInstance().setAppstorePlatform(appstorePlatform);
		}
	}

	public boolean isWired() {
		if (getInstance().getAppstorePlatform() == null)
			return false;
		return true;
	}

	public AppstoreAppType getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<AppstoreApp> getAppstoreApps() {
		return getInstance() == null ? null : new ArrayList<AppstoreApp>(getInstance().getAppstoreApps());
	}

}
