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

import javax.faces.event.ValueChangeEvent;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import com.makeabyte.appstore.server.entity.AppstoreApp;
import com.makeabyte.appstore.server.entity.AppstoreAppType;
import com.makeabyte.appstore.server.entity.AppstoreUserAppMap;
import com.makeabyte.appstore.server.entity.AppstoreCurrencyCode;
import com.makeabyte.appstore.server.entity.AppstoreAppCategoryMap;
import com.makeabyte.appstore.server.entity.AppstoreAppPlatformMap;

@Name("appstoreAppHome")
public class AppstoreAppHome extends EntityHome<AppstoreApp> {

	private static final long serialVersionUID = 7098590068519134315L;

	@In(create = true)
	AppstoreAppTypeHome appstoreAppTypeHome;

	@In(create = true)
	AppstoreCurrencyCodeHome appstoreCurrencyCodeHome;

	public void setAppstoreAppId(Long id) {
		setId(id);
	}

	public Long getAppstoreAppId() {
		return (Long) getId();
	}

	@Override
	protected AppstoreApp createInstance() {
		AppstoreApp appstoreApp = new AppstoreApp();
		return appstoreApp;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		AppstoreAppType appstoreAppType = appstoreAppTypeHome.getDefinedInstance();
		if (appstoreAppType != null) {
			getInstance().setAppstoreAppType(appstoreAppType);
		}
		AppstoreCurrencyCode appstoreCurrencyCode = appstoreCurrencyCodeHome.getDefinedInstance();
		if (appstoreCurrencyCode != null) {
			getInstance().setAppstoreCurrencyCode(appstoreCurrencyCode);
		}
	}

	public boolean isWired() {
		if (getInstance().getAppstoreAppType() == null)
			return false;
		if (getInstance().getAppstoreCurrencyCode() == null)
			return false;
		return true;
	}

	public AppstoreApp getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	public List<AppstoreAppCategoryMap> getAppstoreAppCategoryMaps() {
		return getInstance() == null
				? null
				: new ArrayList<AppstoreAppCategoryMap>(getInstance().getAppstoreAppCategoryMaps());
	}
	public List<AppstoreAppPlatformMap> getAppstoreAppPlatformMaps() {
		return getInstance() == null
				? null
				: new ArrayList<AppstoreAppPlatformMap>(getInstance().getAppstoreAppPlatformMaps());
	}
	public List<AppstoreUserAppMap> getAppstoreUserAppMaps() {
		return getInstance() == null
				? null
				: new ArrayList<AppstoreUserAppMap>(getInstance().getAppstoreUserAppMaps());
	}
	
	/**
	 * Handles JSF event associated with add or edit content type operation
	 * 
	 * @param e
	 */
	public void ValueChangeListener( ValueChangeEvent e ) {
		   
		   getInstance().setExtension( e.getNewValue().toString() );
	}
}