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


import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

import javax.faces.event.ValueChangeEvent;

import com.makeabyte.appstore.server.entity.AppstoreUser;
import com.makeabyte.appstore.server.entity.AppstorePlatform;
import com.makeabyte.appstore.server.entity.AppstoreUserPlatformMapId;
import com.makeabyte.appstore.server.entity.AppstoreUserPlatformMap;

@Name("appstoreUserPlatformMapList")
public class AppstoreUserPlatformMapList extends EntityQuery<AppstoreUserPlatformMap> {

	   private static final long serialVersionUID = 821109887569332591L;
	   private static final String EJBQL = "select appstoreUserPlatformMap from AppstoreUserPlatformMap appstoreUserPlatformMap";
	   private static final String[] RESTRICTIONS = {};

	   private AppstoreUserPlatformMap appstoreUserPlatformMap = new AppstoreUserPlatformMap();
	   private AppstorePlatform appstorePlatform = new AppstorePlatform();
	   private AppstoreUser appstoreUser = new AppstoreUser();

	   public AppstoreUserPlatformMapList() {

		      appstoreUserPlatformMap = new AppstoreUserPlatformMap();
		      appstoreUserPlatformMap.setId(new AppstoreUserPlatformMapId());

		      setEjbql( EJBQL );
		      setRestrictionExpressionStrings( Arrays.asList( RESTRICTIONS ) );
		      setMaxResults( 25 );

		      appstorePlatform.setId( appstoreUserPlatformMap.getId().getPlatformId() );
		      appstoreUser.setId( appstoreUserPlatformMap.getId().getUserId() );
	   }

 	   public AppstoreUserPlatformMap getAppstoreUserPlatformMap() {

		      return appstoreUserPlatformMap;
	   }

	   public AppstorePlatform getAppstorePlatform() {

		      return appstorePlatform;
	   }
	   
	   public AppstoreUser getAppstoreUser() {

		      return appstoreUser;
	   }
	
	   /**
	    * Handles action events initiated by the search
	    * 
	    * @param e ValueChangeEvent
	    */
	   public void ValueChangeListener( ValueChangeEvent e ) {

		      String componentId = e.getComponent().getId();
		      Long id = Long.parseLong( e.getNewValue().toString() );

		      if( id.intValue() == 0 ) {

			      refresh();
			      setRestrictionExpressionStrings(Arrays.asList( new String[] {} ));
			      return;
		      }

		      if( componentId.equals( "platformId" ) )
		    	  appstoreUserPlatformMap.getId().setPlatformId( id );
		   
		      else if( componentId.equals( "upmUserId" ) ) 
		    	  appstoreUserPlatformMap.getId().setUserId( id );

		      setRestrictionExpressionStrings(Arrays.asList( new String[] { "appstoreUserPlatformMap.id.platformId like concat(#{appstoreUserPlatformMapList.appstorePlatform.id},'%')", 
		    		  "appstoreUserPlatformMap.id.userId like concat(#{appstoreUserPlatformMapList.appstoreUser.id},'%')",} ));
	}
}