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
package com.makeabyte.appstore.webservice;

import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import com.makeabyte.appstore.server.entity.AppstoreApp;
import com.makeabyte.appstore.server.entity.AppstoreAppCategoryMapId;
import com.makeabyte.appstore.server.entity.AppstoreAppPlatformMap;
import com.makeabyte.appstore.server.entity.AppstoreAppPlatformMapId;
import com.makeabyte.appstore.server.entity.AppstoreCategory;
import com.makeabyte.appstore.server.entity.AppstorePlatform;
import com.makeabyte.appstore.server.entity.AppstoreUserAppMap;
import com.makeabyte.appstore.server.session.AppstoreAppCategoryMapHome;
import com.makeabyte.appstore.server.session.AppstoreAppPlatformMapHome;
import com.makeabyte.appstore.server.session.AppstoreAppPlatformMapList;
import com.makeabyte.appstore.server.session.AppstoreUserAppMapList;
import com.makeabyte.appstore.server.session.AppstoreUserList;

@Name("AppAction")
public class AppAction implements java.io.Serializable {

	   private static final long serialVersionUID = 1152459821350735755L;

	   public AppAction() { }

	   @Logger
	   private Log log;

	   @In
	   private EntityManager entityManager;

	   @Transactional
	   @Restrict("#{identity.loggedIn}")
	   public byte[] download( long id ) {

		      log.info( "Download requested for application id {0}", id );

		      AppstoreApp app = entityManager.find( AppstoreApp.class, id );
              return app.getSource();
	   }

	   /**
	    * Returns a list of applications corresponding to the specified platformId
	    * 
	    * @param id Unique identifier of a platform to get a list of applications for
	    * @return List of AppWS objects
	    */
	   @Restrict("#{identity.loggedIn}")
	   public AppWS[] getAppsByPlatformId( long id ) {

		   	  log.info( "Getting apps for platform id {0}", id );

		   	  List<AppstoreAppPlatformMap> maps = getAppstoreAppPlatformMapList().getAppsByPlatformId( id );
		   	  AppWS[] apps = new AppWS[maps.size()];

		   	  int count = 0;
		   	  for( AppstoreAppPlatformMap map : maps ) {

		   		   AppTypeWS appType = new AppTypeWS();
		   		   appType.setId( map.getAppstoreApp().getAppstoreAppType().getId() );
		   		   appType.setName( map.getAppstoreApp().getAppstoreAppType().getName() );
		   		   appType.setDescription( map.getAppstoreApp().getAppstoreAppType().getDescription() );

		   		   CurrencyWS currency = new CurrencyWS();
		   		   currency.setId( map.getAppstoreApp().getAppstoreCurrencyCode().getId() );
		   		   currency.setCode( map.getAppstoreApp().getAppstoreCurrencyCode().getCode() );
		   		   currency.setSymbol( map.getAppstoreApp().getAppstoreCurrencyCode().getSymbol() );
		   		   
		   		   AppWS app = new AppWS();
		   		   app.setId( map.getAppstoreApp().getId() );
		   		   app.setAppId( map.getAppstoreApp().getAppId() );
		   		   app.setName( map.getAppstoreApp().getName() );
		   		   app.setDescription( map.getAppstoreApp().getDescription() );
		   		   app.setAppType( appType );
		   		   app.setCost( ((map.getAppstoreApp().getCost() == null) ? (float) 0.00 : map.getAppstoreApp().getCost()) );
		   		   app.setCurrency( currency );
		   		   app.setSize( map.getAppstoreApp().getSize() );
		   		   // app.setSource( map.getAppstoreApp().getSource() );   DONT INCLUDE SOURCE WHILE GETTING ALL APPS!
		   		   app.setExtension( map.getAppstoreApp().getExtension() );

		   		   apps[count] = app;
		   		   count++;
		   	  }

		   	  return apps;
	   }

	   /**
	    * Returns a list of applications corresponding to the specified user/developer
	    * 
	    * @param id Unique identifier of a user to get a list of applications for
	    * @return List of AppWS objects
	    */
	   @Restrict("#{identity.loggedIn}")
	   public AppWS[] getAppsByUserId( long id ) {

		   	  log.info( "Getting apps for user id {0}", id );

		   	  getAppstoreUserAppMapList().getAppstoreUser().setId( id );

		   	  List<AppstoreUserAppMap> maps = getAppstoreUserAppMapList().getAppsByUserId();
		   	  AppWS[] apps = new AppWS[maps.size()];

		   	  int count = 0;
		   	  for( AppstoreUserAppMap map : maps ) {

		   		   AppTypeWS appType = new AppTypeWS();
		   		   appType.setId( map.getAppstoreApp().getAppstoreAppType().getId() );
		   		   appType.setName( map.getAppstoreApp().getAppstoreAppType().getName() );
		   		   appType.setDescription( map.getAppstoreApp().getAppstoreAppType().getDescription() );

		   		   CurrencyWS currency = new CurrencyWS();
		   		   currency.setId( map.getAppstoreApp().getAppstoreCurrencyCode().getId() );
		   		   currency.setCode( map.getAppstoreApp().getAppstoreCurrencyCode().getCode() );
		   		   currency.setSymbol( map.getAppstoreApp().getAppstoreCurrencyCode().getSymbol() );

		   		   AppWS app = new AppWS();
		   		   app.setId( map.getAppstoreApp().getId() );
		   		   app.setAppId( map.getAppstoreApp().getAppId() );
		   		   app.setName( map.getAppstoreApp().getName() );
		   		   app.setDescription( map.getAppstoreApp().getDescription() );
		   		   app.setAppType( appType );
		   		   app.setCost( ((map.getAppstoreApp().getCost() == null) ? (float) 0.00 : map.getAppstoreApp().getCost()) );
		   		   app.setCurrency( currency );
		   		   app.setSize( map.getAppstoreApp().getSize() );
		   		   // app.setSource( map.getAppstoreApp().getSource() );   DONT INCLUDE SOURCE WHILE GETTING ALL APPS!
		   		   app.setExtension( map.getAppstoreApp().getExtension() );

		   		   apps[count] = app;
		   		   count++;
		   	  }

		   	  return apps;
	   }

	   /**
	    * Adds an application to a category
	    *  
	    * @param appId The id of the application
	    * @param categoryId The id of the category
	    */
	   @Restrict("#{identity.hasRole( 'admin' ) || identity.hasRole( 'developer' )}")
	   public void addAppToCategory( long appId, long categoryId ) {

		      AppstoreApp app = entityManager.find( AppstoreApp.class, appId );
		      AppstoreCategory category = entityManager.find( AppstoreCategory.class, categoryId );

		      if( app == null ) throw new RuntimeException( "Requested app does not exist." );
		      if( category == null ) throw new RuntimeException( "Requested category does not exit" );

		      // Update the relationship if the requester owns the app
		      getAppstoreUserList().getAppstoreUser().setUsername( Identity.instance().getPrincipal().getName() );
		      long userId = getAppstoreUserList().getIdByUsername();
		      getAppstoreUserAppMapList().getAppstoreUser().setId( userId );
		      for( AppstoreUserAppMap uam : getAppstoreUserAppMapList().getAppsByUserId() ) {

		    	   if( uam.getAppstoreApp().getId() == appId ) {

		    		   persistAppCategoryMap( app, category );
		 		       return;
		    	   }
		      }

		      // Admin gets away with murder
		      if( Identity.instance().hasRole( "admin" ) ) {

		    	  persistAppCategoryMap( app, category );
		    	  return;
		      }

		      throw new RuntimeException( "Permission denied. You must own an application if you wish to modify its properties." );
	   }

	   /**
	    * Adds an application to a platform
	    *  
	    * @param appId The id of the application
	    * @param platformId The id of the platform
	    */
	   @Restrict("#{identity.hasRole( 'admin' ) || identity.hasRole( 'developer' )}")
	   public void addAppToPlatform( long appId, long platformId ) {

		      AppstoreApp app = entityManager.find( AppstoreApp.class, appId );
		      AppstorePlatform platform = entityManager.find( AppstorePlatform.class, platformId );

		      if( app == null ) throw new RuntimeException( "Requested app does not exist." );
		      if( platform == null ) throw new RuntimeException( "Requested platform does not exist." );

		      // Update the relationship if the requester owns the app
		      getAppstoreUserList().getAppstoreUser().setUsername( Identity.instance().getPrincipal().getName() );
		      long userId = getAppstoreUserList().getIdByUsername();
		      getAppstoreUserAppMapList().getAppstoreUser().setId( userId );
		      for( AppstoreUserAppMap uam : getAppstoreUserAppMapList().getAppsByUserId() ) {

		    	   if( uam.getAppstoreApp().getId() == appId ) {

		    		   persistAppPlatformMap( app, platform );
		 		       return;
		    	   }
		      }

		      // Admin gets away with murder once again
		      if( Identity.instance().hasRole( "admin" ) ) {

		    	  persistAppPlatformMap( app, platform );
		    	  return;
		      }

		      throw new RuntimeException( "Permission denied. You must own an application if you wish to modify its properties." );
	   }

	   private void persistAppPlatformMap( AppstoreApp app, AppstorePlatform platform ) {

		       AppstoreAppPlatformMapId mid = new AppstoreAppPlatformMapId();
		       mid.setAppId( app.getId() );
		       mid.setPlatformId( platform.getId() );

		       getAppstoreAppPlatformMapHome().getInstance().setAppstoreApp( app );
		       getAppstoreAppPlatformMapHome().getInstance().setAppstorePlatform( platform );
		       getAppstoreAppPlatformMapHome().getInstance().setId( mid );
		       getAppstoreAppPlatformMapHome().persist();
	   }

	   private void persistAppCategoryMap( AppstoreApp app, AppstoreCategory category ) {

	    	   AppstoreAppCategoryMapId mid = new AppstoreAppCategoryMapId();
	   		   mid.setAppId( app.getId() );
	   		   mid.setCategoryId( category.getId() );

	    	   getAppstoreAppCategoryMapHome().getInstance().setAppstoreApp( app );
	    	   getAppstoreAppCategoryMapHome().getInstance().setAppstoreCategory( category );
	    	   getAppstoreAppCategoryMapHome().getInstance().setId( mid );
	    	   getAppstoreAppCategoryMapHome().persist();
	   }
	   
	   private AppstoreAppPlatformMapHome getAppstoreAppPlatformMapHome() {

		      return (AppstoreAppPlatformMapHome) Component.getInstance( AppstoreAppPlatformMapHome.class, true );
	   }

	   private AppstoreAppPlatformMapList getAppstoreAppPlatformMapList() {

		      return (AppstoreAppPlatformMapList) Component.getInstance( AppstoreAppPlatformMapList.class, true );
	   }

	   private AppstoreUserAppMapList getAppstoreUserAppMapList() {

		       return (AppstoreUserAppMapList) Component.getInstance( AppstoreUserAppMapList.class, true );
	   }

	   private AppstoreAppCategoryMapHome getAppstoreAppCategoryMapHome() {

		       return (AppstoreAppCategoryMapHome) Component.getInstance( AppstoreAppCategoryMapHome.class, true );
	   }

	   private AppstoreUserList getAppstoreUserList() {

		       return (AppstoreUserList) Component.getInstance( AppstoreUserList.class, true );
	   }
}