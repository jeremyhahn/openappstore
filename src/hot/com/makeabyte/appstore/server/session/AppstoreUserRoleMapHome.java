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

import com.makeabyte.appstore.server.entity.AppstoreUser;
import com.makeabyte.appstore.server.entity.AppstoreUserRole;
import com.makeabyte.appstore.server.entity.AppstoreUserRoleMap;
import com.makeabyte.appstore.server.entity.AppstoreUserRoleMapId;

@Name("appstoreUserRoleMapHome")
public class AppstoreUserRoleMapHome extends EntityHome<AppstoreUserRoleMap> {

	private static final long serialVersionUID = 2498650140430778232L;
	private static boolean roleSelected = false;
	private static boolean userSelected = false;

	@In(create = true)
	AppstoreUserHome appstoreUserHome;

	@In(create = true)
	AppstoreUserRoleHome appstoreUserRoleHome;
	
	public void setAppstoreUserRoleMapId(AppstoreUserRoleMapId id) {
		setId(id);
	}

	public AppstoreUserRoleMapId getAppstoreUserRoleMapId() {
		return (AppstoreUserRoleMapId) getId();
	}

	public AppstoreUserRoleMapHome() {
		setAppstoreUserRoleMapId(new AppstoreUserRoleMapId());
	}

	@Override
	public boolean isIdDefined() {
		if (getAppstoreUserRoleMapId().getRoleId() == 0)
			return false;
		if (getAppstoreUserRoleMapId().getUserId() == 0)
			return false;
		return true;
	}

	@Override
	protected AppstoreUserRoleMap createInstance() {
		AppstoreUserRoleMap appstoreUserRoleMap = new AppstoreUserRoleMap();
		appstoreUserRoleMap.setId(new AppstoreUserRoleMapId());
		return appstoreUserRoleMap;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		AppstoreUser appstoreUser = appstoreUserHome.getDefinedInstance();
		if (appstoreUser != null) {
			getInstance().setAppstoreUser(appstoreUser);
		}
		AppstoreUserRole appstoreUserRole = appstoreUserRoleHome.getDefinedInstance();
		if (appstoreUserRole != null) {
			getInstance().setAppstoreUserRole(appstoreUserRole);
		}
	}

	public boolean isWired() {
		if (getInstance().getAppstoreUser() == null)
			return false;
		if (getInstance().getAppstoreUserRole() == null)
			return false;
		
		if( !roleSelected || !userSelected ) return false;

		return true;
	}

	public AppstoreUserRoleMap getDefinedInstance() {

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

			   if( componentId.equals( "urmRoleId" ) )
				   roleSelected = false;
			   else if( componentId.equals( "urmUserId" ) )
				   userSelected = false;

			   return;
		   }

		   if( componentId.equals( "urmRoleId" ) ) {

			   instance.getId().setRoleId( id );
			   appstoreUserRoleHome.setId( Long.parseLong( e.getNewValue().toString() ) );
			   roleSelected = true;
		   }

		   else if( componentId.equals( "urmUserId" ) ) {

 			    instance.getId().setUserId( id );
			    appstoreUserHome.setId( id );
			    userSelected = true;
		   }
	}
}