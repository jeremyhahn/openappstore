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

import javax.faces.event.ValueChangeEvent;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import com.makeabyte.appstore.server.entity.AppstoreApp;
import com.makeabyte.appstore.server.entity.AppstorePlatform;
import com.makeabyte.appstore.server.entity.AppstoreAppPlatformMap;
import com.makeabyte.appstore.server.entity.AppstoreAppPlatformMapId;

@Name("appstoreAppPlatformMapHome")
public class AppstoreAppPlatformMapHome extends EntityHome<AppstoreAppPlatformMap> {

	private static final long serialVersionUID = -2524385700772703297L;

	@In(create = true)
	AppstoreAppHome appstoreAppHome;

	@In(create = true)
	AppstorePlatformHome appstorePlatformHome;

	private static boolean appSelected = false;
    private static boolean platformSelected = false;

	public void setAppstoreAppPlatformMapId(AppstoreAppPlatformMapId id) {
		setId(id);
	}

	public AppstoreAppPlatformMapId getAppstoreAppPlatformMapId() {
		return (AppstoreAppPlatformMapId) getId();
	}

	public AppstoreAppPlatformMapHome() {
		setAppstoreAppPlatformMapId(new AppstoreAppPlatformMapId());
	}

	@Override
	public boolean isIdDefined() {
		if (getAppstoreAppPlatformMapId().getAppId() == 0)
			return false;
		if (getAppstoreAppPlatformMapId().getPlatformId() == 0)
			return false;
		return true;
	}

	@Override
	protected AppstoreAppPlatformMap createInstance() {
		AppstoreAppPlatformMap appstoreAppPlatformMap = new AppstoreAppPlatformMap();
		appstoreAppPlatformMap.setId(new AppstoreAppPlatformMapId());
		return appstoreAppPlatformMap;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		AppstoreApp appstoreApp = appstoreAppHome.getDefinedInstance();
		if (appstoreApp != null) {
			getInstance().setAppstoreApp(appstoreApp);
		}
		AppstorePlatform appstorePlatform = appstorePlatformHome
				.getDefinedInstance();
		if (appstorePlatform != null) {
			getInstance().setAppstorePlatform(appstorePlatform);
		}
	}

	public boolean isWired() {

		if( getInstance().getAppstoreApp() == null )
			return false;

		if( getInstance().getAppstorePlatform() == null )
			return false;

		if( !platformSelected || !appSelected )
			return false;

		return true;
	}

	public AppstoreAppPlatformMap getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/**
	 * Handles JSF event associated with add or edit operation
	 * 
	 * @param e
	 */
	public void ValueChangeListener( ValueChangeEvent e ) {
		
	       String componentId = e.getComponent().getId();
		   Long id = Long.parseLong( e.getNewValue().toString() );
	   
		   if( id.intValue() == 0 ) {

			   if( componentId.equals( "apmAppId" ) )
			  	   appSelected = false;
			   else if( componentId.equals( "apmPlatformId" ) )
				   platformSelected = false;

				  return;
		   }

		   if( componentId.equals( "apmAppId" ) ) {

			   instance.getId().setAppId( id );
			   appstoreAppHome.setId( Long.parseLong( e.getNewValue().toString() ) );
			   appSelected = true;
		   }

		   else if( componentId.equals( "apmPlatformId" ) ) {

			   instance.getId().setPlatformId( id );
			   appstorePlatformHome.setId( id );
			   platformSelected = true;
		   }
	}
}