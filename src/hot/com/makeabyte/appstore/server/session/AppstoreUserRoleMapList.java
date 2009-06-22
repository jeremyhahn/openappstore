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
import java.util.Arrays;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.makeabyte.appstore.server.entity.AppstoreUser;
import com.makeabyte.appstore.server.entity.AppstoreUserRole;
import com.makeabyte.appstore.server.entity.AppstoreUserRoleMap;
import com.makeabyte.appstore.server.entity.AppstoreUserRoleMapId;

@Name("appstoreUserRoleMapList")
public class AppstoreUserRoleMapList extends EntityQuery<AppstoreUserRoleMap> {

  	   private static final long serialVersionUID = 8725653219473474921L;

  	   private static final String EJBQL = "select appstoreUserRoleMap from AppstoreUserRoleMap appstoreUserRoleMap";

  	   private static final String[] RESTRICTIONS = {};
    
  	   private AppstoreUserRoleMap appstoreUserRoleMap = new AppstoreUserRoleMap();
  	   private AppstoreUserRole appstoreUserRole = new AppstoreUserRole();
  	   private AppstoreUser appstoreUser = new AppstoreUser();

  	   public AppstoreUserRoleMapList() {

  		   	  appstoreUserRoleMap.setId( new AppstoreUserRoleMapId() );
  		   	  setEjbql(EJBQL);
  		   	  setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
  		   	  setMaxResults(25);

  		   	  appstoreUser.setId( appstoreUserRoleMap.getId().getUserId() );
  		   	  appstoreUserRole.setId( appstoreUserRoleMap.getId().getRoleId() );
  	   }

  	   public AppstoreUserRoleMap getAppstoreUserRoleMap() {

  		      return appstoreUserRoleMap;
  	   }

  	   public AppstoreUser getAppstoreUser() {

		      return appstoreUser;
  	   }

  	   public AppstoreUserRole getAppstoreUserRole() {

		      return appstoreUserRole;
  	   }

	   public AppstoreUser getUserByUsername( String username ) {

		      for( AppstoreUserRoleMap urm : getResultList() )
		    	   if( urm.getAppstoreUser().getUsername().equalsIgnoreCase( username ) )
		    		   return urm.getAppstoreUser();

		      throw new RuntimeException( "Invalid username" );
	   }

	   public List<String> getRolesForUser( Long userId ) {

		      List<String> roleNames = new ArrayList<String>(0);
		      for( AppstoreUserRoleMap urm : getResultList() ) {
		    	  
		    	   if( userId.equals( urm.getAppstoreUser().getId() )  )		    		   
		    		   roleNames.add( urm.getAppstoreUserRole().getName() );
		      }

		      return roleNames;
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

  		      if( componentId.equals( "urmRoleId" ) )
  		    	  appstoreUserRoleMap.getId().setRoleId( id );
		   
  		      else if( componentId.equals( "urmUserId" ) ) 
  		    	  appstoreUserRoleMap.getId().setUserId( id );

  		      setRestrictionExpressionStrings(Arrays.asList( new String[] { "appstoreUserRoleMap.id.roleId like concat(#{appstoreUserRoleMapList.appstoreUser.id},'%')", 
  		    		  "appstoreUserRoleMap.id.userId like concat(#{appstoreUserRoleMapList.appstoreUserRole.id},'%')",} ));
	}
}