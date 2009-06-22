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

import javax.faces.event.ValueChangeEvent;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.makeabyte.appstore.server.entity.AppstoreCategory;
import com.makeabyte.appstore.server.entity.AppstorePlatform;
import com.makeabyte.appstore.server.entity.AppstoreCategoryPlatformMap;
import com.makeabyte.appstore.server.entity.AppstoreCategoryPlatformMapId;

@Name("appstoreCategoryPlatformMapList")
public class AppstoreCategoryPlatformMapList extends EntityQuery<AppstoreCategoryPlatformMap> {

	   private static final long serialVersionUID = -2713540709477689540L;

	   private static final String EJBQL = "select appstoreCategoryPlatformMap from AppstoreCategoryPlatformMap appstoreCategoryPlatformMap";
	   private static final String[] RESTRICTIONS = {};

	   private AppstoreCategory appstoreCategory = new AppstoreCategory();
	   private AppstorePlatform appstorePlatform = new AppstorePlatform();
	   private AppstoreCategoryPlatformMap appstoreCategoryPlatformMap = new AppstoreCategoryPlatformMap();

	   public AppstoreCategoryPlatformMapList() {

		      appstoreCategoryPlatformMap.setId( new AppstoreCategoryPlatformMapId() );
		      setEjbql( EJBQL );
		      setRestrictionExpressionStrings( Arrays.asList( RESTRICTIONS ) );
		      setMaxResults( 25 );

		      appstoreCategory.setId( appstoreCategoryPlatformMap.getId().getCategoryId() );
		      appstorePlatform.setId( appstoreCategoryPlatformMap.getId().getPlatformId() );
	   }

	   public AppstoreCategoryPlatformMap getAppstoreCategoryPlatformMap() {

		   return appstoreCategoryPlatformMap;
	   }
	
	   public AppstoreCategory getAppstoreCategory() {

		      return appstoreCategory;
	   }
	   
	   public AppstorePlatform getAppstorePlatform() {

		      return appstorePlatform;
	   }
	
	   /**
	    * Handles action events initiated by the AppstoreUserRoleMapList search
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

		      if( componentId.equals( "cpmCategoryId" ) )
		    	  appstoreCategoryPlatformMap.getId().setCategoryId( id );

		      else if( componentId.equals( "cpmPlatformId" ) ) 
		    	  appstoreCategoryPlatformMap.getId().setPlatformId( id );

		      setRestrictionExpressionStrings(Arrays.asList( new String[] { "appstoreCategoryPlatformMap.id.categoryId like concat(#{appstoreCategoryPlatformMapList.appstoreCategory.id},'%')", 
		    		  "appstoreCategoryPlatformMap.id.platformId like concat(#{appstoreCategoryPlatformMapList.appstorePlatform.id},'%')",} ));
	}
}
