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

import java.util.Arrays;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.makeabyte.appstore.server.entity.AppstoreApp;
import com.makeabyte.appstore.server.entity.AppstorePlatform;
import com.makeabyte.appstore.server.entity.AppstoreAppPlatformMap;
import com.makeabyte.appstore.server.entity.AppstoreAppPlatformMapId;

@Name("appstoreAppPlatformMapList")
public class AppstoreAppPlatformMapList extends EntityQuery<AppstoreAppPlatformMap> {

	   private static final long serialVersionUID = -8714919199026419237L;

	   private static final String EJBQL = "select appstoreAppPlatformMap from AppstoreAppPlatformMap appstoreAppPlatformMap";
	   private static final String[] RESTRICTIONS = {};

	   private AppstoreApp appstoreApp = new AppstoreApp();
	   private AppstorePlatform appstorePlatform = new AppstorePlatform();
	   private AppstoreAppPlatformMap appstoreAppPlatformMap = new AppstoreAppPlatformMap();

	   public AppstoreAppPlatformMapList() {

		      appstoreAppPlatformMap.setId( new AppstoreAppPlatformMapId() );
		      setEjbql(EJBQL);
		      setRestrictionExpressionStrings( Arrays.asList( RESTRICTIONS ) );
		      setMaxResults(25);

		      appstoreApp.setId( appstoreAppPlatformMap.getId().getAppId() );
		      appstorePlatform.setId( appstoreAppPlatformMap.getId().getPlatformId() );
	   }

	   public AppstoreAppPlatformMap getAppstoreAppPlatformMap() {

		      return appstoreAppPlatformMap;
	   }

	   public AppstorePlatform getAppstorePlatform() {

		      return appstorePlatform; 
	   }

	   public AppstoreApp getAppstoreApp() {

		      return appstoreApp;
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

		      if( componentId.equals( "apmAppId" ) )
		    	  appstoreAppPlatformMap.getId().setAppId( id );

		      else if( componentId.equals( "apmPlatformId" ) ) 
		    	  appstoreAppPlatformMap.getId().setPlatformId( id );

		      setRestrictionExpressionStrings(Arrays.asList( new String[] { "appstoreAppPlatformMap.id.appId like concat(#{appstoreAppPlatformMapList.appstoreApp.id},'%')",
		    		  "appstoreAppPlatformMap.id.platformId like concat(#{appstoreAppPlatformMapList.appstorePlatform.id},'%')",} ));
	   }

	   public List<AppstoreAppPlatformMap> getAppsByPlatformId( long id ) {

		      getAppstorePlatform().setId( id );

		      setEjbql( EJBQL );
		      setRestrictionExpressionStrings(Arrays.asList( new String[] { "appstoreAppPlatformMap.id.platformId like concat(#{appstoreAppPlatformMapList.appstorePlatform.id},'%')" } ) );
		      return this.getResultList();
	   }
}