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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import com.makeabyte.appstore.server.entity.AppstoreApp;
import com.makeabyte.appstore.server.entity.AppstoreUser;
import com.makeabyte.appstore.server.entity.AppstoreUserAppMap;
import com.makeabyte.appstore.server.entity.AppstoreUserAppMapId;

@Name("appstoreUserAppMapList")
public class AppstoreUserAppMapList extends EntityQuery<AppstoreUserAppMap> {

	   private static final long serialVersionUID = 7478419539242133718L;
	   private static final String EJBQL = "select appstoreUserAppMap from AppstoreUserAppMap appstoreUserAppMap";
	   private static final String[] RESTRICTIONS = {};

	   private AppstoreUserAppMap appstoreUserAppMap = new AppstoreUserAppMap();
	   private AppstoreApp appstoreApp = new AppstoreApp();
	   private AppstoreUser appstoreUser = new AppstoreUser();

	   public AppstoreUserAppMapList() {

		   	  appstoreUserAppMap.setId(new AppstoreUserAppMapId());
		   	  setEjbql(EJBQL);
		   	  setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		   	  setMaxResults(25);

		   	  appstoreApp.setId( appstoreUserAppMap.getId().getAppId() );
		   	  appstoreUser.setId( appstoreUserAppMap.getId().getUserId() );
 	   }

  	   public AppstoreUserAppMap getAppstoreUserAppMap() {

  		      return appstoreUserAppMap;
  	   }

	   public AppstoreApp getAppstoreApp() {

		      return appstoreApp;
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

		      if( componentId.equals( "uamAppId" ) )
		    	  appstoreUserAppMap.getId().setAppId( id );

		      else if( componentId.equals( "uamUserId" ) ) 
		    	  appstoreUserAppMap.getId().setUserId( id );

		      setRestrictionExpressionStrings(Arrays.asList( new String[] { "appstoreUserAppMap.id.appId like concat(#{appstoreUserAppMapList.appstoreApp.id},'%')", 
		    		  "appstoreUserAppMap.id.userId like concat(#{appstoreUserAppMapList.appstoreUser.id},'%')",} ));
	   }

	   public List<AppstoreUserAppMap> getAppsByUserId() {

	       	  setEjbql( EJBQL );
	       	  setRestrictionExpressionStrings(Arrays.asList( new String[] { "appstoreUserAppMap.id.userId like concat(#{appstoreUserAppMapList.appstoreUser.id},'%')" } ) );
	       	  return this.getResultList();
	   }

	   public List<SelectItem> getDevelopersApps() {

	   	   	  List<SelectItem> items = new ArrayList<SelectItem>(0);

	   	   	  SelectItem si = new SelectItem();
	   	   	  si.setLabel( "Choose..." );
	   	   	  si.setValue( "0" );

	   	   	  items.add( si );

	   	   	  for( AppstoreUserAppMap uam : getAppsByUserId() ) {

	   	     	   SelectItem item = new SelectItem();
	   	   		   item.setValue( uam.getAppstoreApp().getId() );
	   	   		   item.setLabel( uam.getAppstoreApp().getName() );

	   	   		   items.add( item );
	   	   	  }

	   	   	  return items;
	   }
}