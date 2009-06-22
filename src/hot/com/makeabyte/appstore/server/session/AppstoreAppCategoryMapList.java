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

import com.makeabyte.appstore.server.entity.AppstoreApp;
import com.makeabyte.appstore.server.entity.AppstoreCategory;
import com.makeabyte.appstore.server.entity.AppstoreAppCategoryMap;
import com.makeabyte.appstore.server.entity.AppstoreAppCategoryMapId;

@Name("appstoreAppCategoryMapList")
public class AppstoreAppCategoryMapList extends EntityQuery<AppstoreAppCategoryMap> {

	   private static final long serialVersionUID = -355460080022583627L;

	   private static final String EJBQL = "select appstoreAppCategoryMap from AppstoreAppCategoryMap appstoreAppCategoryMap";
	   private static final String[] RESTRICTIONS = {};

	   private AppstoreApp appstoreApp = new AppstoreApp();
	   private AppstoreCategory appstoreCategory = new AppstoreCategory();
	   private AppstoreAppCategoryMap appstoreAppCategoryMap = new AppstoreAppCategoryMap();

	   public AppstoreAppCategoryMapList() {

		      appstoreAppCategoryMap.setId(new AppstoreAppCategoryMapId());

		      setEjbql( EJBQL );
		      setRestrictionExpressionStrings( Arrays.asList( RESTRICTIONS ) );
		      setMaxResults( 25 );

		      appstoreCategory.setId( appstoreAppCategoryMap.getId().getCategoryId() );
		      appstoreApp.setId( appstoreAppCategoryMap.getId().getAppId() );
	   }

	   public AppstoreAppCategoryMap getAppstoreAppCategoryMap() {

		      return appstoreAppCategoryMap;
	   }

	   public AppstoreCategory getAppstoreCategory() {

		      return appstoreCategory;
	   }

	   public AppstoreApp getAppstoreApp() {

		      return appstoreApp;
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

		      if( componentId.equals( "acmAppId" ) )
		    	  appstoreAppCategoryMap.getId().setAppId( id );

		      else if( componentId.equals( "acmCategoryId" ) ) 
		    	  appstoreAppCategoryMap.getId().setCategoryId( id );

		      setRestrictionExpressionStrings(Arrays.asList( new String[] { "appstoreAppCategoryMap.id.appId like concat(#{appstoreAppCategoryMapList.appstoreApp.id},'%')", 
		    		  "appstoreAppCategoryMap.id.categoryId like concat(#{appstoreAppCategoryMapList.appstoreCategory.id},'%')",} ));
	}
}