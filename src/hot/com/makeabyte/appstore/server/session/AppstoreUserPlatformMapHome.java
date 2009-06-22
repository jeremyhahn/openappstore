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
import com.makeabyte.appstore.server.entity.AppstorePlatform;
import com.makeabyte.appstore.server.entity.AppstoreUserPlatformMap;
import com.makeabyte.appstore.server.entity.AppstoreUserPlatformMapId;

@Name("appstoreUserPlatformMapHome")
public class AppstoreUserPlatformMapHome extends EntityHome<AppstoreUserPlatformMap> {

	   private static final long serialVersionUID = 6056190687877781276L;
	
	   @In(create = true)
	   AppstorePlatformHome appstorePlatformHome;
	
	   @In(create = true)
	   AppstoreUserHome appstoreUserHome;
	
	   private static boolean platformSelected = false;
	   private static boolean userSelected = false;
	
	   public void setAppstoreUserPlatformMapId(AppstoreUserPlatformMapId id) {
	
		      setId(id);
	   }
	
	   public AppstoreUserPlatformMapId getAppstoreUserPlatformMapId() {
	
		      return (AppstoreUserPlatformMapId) getId();
	   }
	
	   public AppstoreUserPlatformMapHome() {
	
		      setAppstoreUserPlatformMapId(new AppstoreUserPlatformMapId());
	   }
	
	   @Override
	   public boolean isIdDefined() {
	
	   	      if( getAppstoreUserPlatformMapId().getPlatformId() == 0 )
				  return false;
	
			  if( getAppstoreUserPlatformMapId().getUserId() == 0 )
				  return false;
	
			  return true;
	   }
	
	   @Override
	   protected AppstoreUserPlatformMap createInstance() {
	
		         AppstoreUserPlatformMap appstoreUserPlatformMap = new AppstoreUserPlatformMap();
			     appstoreUserPlatformMap.setId(new AppstoreUserPlatformMapId());
	
	   	         return appstoreUserPlatformMap;
	   }
	
	   public void load() {
	
		      if( isIdDefined() )
			      wire();
	   }
	
	   public void wire() {
	
		      getInstance();
			  AppstorePlatform appstorePlatform = appstorePlatformHome.getDefinedInstance();
	
			  if( appstorePlatform != null )			
				  getInstance().setAppstorePlatform(appstorePlatform);
	
			  AppstoreUser appstoreUser = appstoreUserHome.getDefinedInstance();
			  if( appstoreUser != null )
				  getInstance().setAppstoreUser(appstoreUser);
	   }
	
	   public boolean isWired() {
	
		      if( getInstance().getAppstorePlatform() == null )
				  return false;
	
			  if( getInstance().getAppstoreUser() == null )
				  return false;

			  if( !platformSelected || !userSelected )
				  return false;

			  return true;
	   }

	   public AppstoreUserPlatformMap getDefinedInstance() {
	
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
	
				  if( componentId.equals( "upmPlatformId" ) )
					  platformSelected = false;
				  else if( componentId.equals( "upmUserId" ) )
					  userSelected = false;
	
				  return;
			  }
	
			  if( componentId.equals( "upmPlatformId" ) ) {
	
				  instance.getId().setPlatformId( id );
				  appstorePlatformHome.setId( Long.parseLong( e.getNewValue().toString() ) );
				  platformSelected = true;
			  }
	
			  else if( componentId.equals( "upmUserId" ) ) {
	
				  instance.getId().setUserId( id );
				  appstoreUserHome.setId( id );
				  userSelected = true;
			  }
	   }
}