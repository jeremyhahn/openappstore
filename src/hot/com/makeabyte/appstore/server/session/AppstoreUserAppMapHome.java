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
import com.makeabyte.appstore.server.entity.AppstoreUser;
import com.makeabyte.appstore.server.entity.AppstoreUserAppMap;
import com.makeabyte.appstore.server.entity.AppstoreUserAppMapId;

@Name("appstoreUserAppMapHome")
public class AppstoreUserAppMapHome extends EntityHome<AppstoreUserAppMap> {

	private static final long serialVersionUID = 4203756223916600208L;

	@In(create = true)
	AppstoreAppHome appstoreAppHome;

	@In(create = true)
	AppstoreUserHome appstoreUserHome;
	
	private static boolean appSelected = false;
    private static boolean userSelected = false;

	public void setAppstoreUserAppMapId(AppstoreUserAppMapId id) {
		setId(id);
	}

	public AppstoreUserAppMapId getAppstoreUserAppMapId() {
		return (AppstoreUserAppMapId) getId();
	}

	public AppstoreUserAppMapHome() {
		setAppstoreUserAppMapId(new AppstoreUserAppMapId());
	}

	@Override
	public boolean isIdDefined() {
		if (getAppstoreUserAppMapId().getAppId() == 0)
			return false;
		if (getAppstoreUserAppMapId().getUserId() == 0)
			return false;
		return true;
	}

	@Override
	protected AppstoreUserAppMap createInstance() {
		AppstoreUserAppMap appstoreUserAppMap = new AppstoreUserAppMap();
		appstoreUserAppMap.setId(new AppstoreUserAppMapId());
		return appstoreUserAppMap;
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
		AppstoreUser appstoreUser = appstoreUserHome.getDefinedInstance();
		if (appstoreUser != null) {
			getInstance().setAppstoreUser(appstoreUser);
		}
	}

	public boolean isWired() {
		if (getInstance().getAppstoreApp() == null)
			return false;
		if (getInstance().getAppstoreUser() == null)
			return false;
		
		if( !appSelected || !userSelected )
			  return false;
		
		return true;
	}

	public AppstoreUserAppMap getDefinedInstance() {
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

			   if( componentId.equals( "uamAppId" ) )
			  	   appSelected = false;
			   else if( componentId.equals( "uamUserId" ) )
				  userSelected = false;

				  return;
		   }

		   if( componentId.equals( "uamAppId" ) ) {

			   instance.getId().setAppId( id );
			   appstoreAppHome.setId( Long.parseLong( e.getNewValue().toString() ) );
			   appSelected = true;
		   }

		   else if( componentId.equals( "uamUserId" ) ) {

			   instance.getId().setUserId( id );
			   appstoreUserHome.setId( id );
			   userSelected = true;
		   }
	}
}