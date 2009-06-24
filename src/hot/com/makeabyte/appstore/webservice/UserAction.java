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

import static com.makeabyte.appstore.common.Crypto.encrypt;

import java.util.Date;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Conversational;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.security.Restrict;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Identity;

import com.makeabyte.appstore.server.session.AppstoreUserHome;
import com.makeabyte.appstore.server.session.AppstoreUserList;

@Conversational
@Name("UserAction")
public class UserAction implements java.io.Serializable {

	   private static final long serialVersionUID = 5957130457667159039L;

	   @Logger Log log;

	   private AppstoreUserHome userHome;

	   @Begin(join = true)
	   public void createAppstoreUser() {

		      if( userHome == null )
		    	  userHome = getAppstoreUserHome();
	   }

	   public boolean login( String username, String password, String apiKey ) {

		      log.info( "Login request from user {0} using key {1}", username, apiKey );

		      // Get user id to verify api access is allowed
		      getAppstoreUserList().getAppstoreUser().setUsername( username );
		      long id = getAppstoreUserList().getIdByUsername();

		      userHome.setId( id );

		      if( !userHome.getInstance().isApiEnabled() ) {

		    	  log.info( "API access disallowed for user {0}", username );
		    	  throw new RuntimeException( "API access disabled" );
		      }

		      if( !userHome.getInstance().getApiKey().equals( apiKey ) ) {

		    	  log.info( "Invalid API key for user {0} using key {1}", username, apiKey );
		    	  throw new RuntimeException( "Invalid API key" );
		      }

		      Identity.instance().getCredentials().setUsername( username );
		   	  Identity.instance().getCredentials().setPassword( password );

		   	  String result = Identity.instance().login();

		   	  if( result == null )
		   		  return false;

		   	  return (result.equals( "loggedIn" ) == true) ? true : false;
	   }

	   @End
	   @Restrict("#{s:hasRole('admin')}")
	   public void createUser( UserWS user ) {

		      log.info( "Creating user {0}", user.toString() );

		      if( userExists( user.getUsername() ) )
		    	  throw new RuntimeException( "User already exists!" );

		      userHome.getInstance().setUsername( user.getUsername() );
		      userHome.getInstance().setPassword( encrypt( user.getPassword() ) );
		      userHome.getInstance().setCreated( (user.getCreated() == null) ? new Date() : user.getCreated() );
		      userHome.getInstance().setApiKey( user.getApiKey() );
		      userHome.getInstance().setApiEnabled( user.getApiEnabled() );

		      userHome.persist();
	   }

	   @End
	   @Restrict("#{s:hasRole('admin')}")
	   public void deleteUser( Long userId ) {

			  log.info( "Deleting userid: {0}", userId );

			  userHome.setId( userId );
		      userHome.remove();
	   }

	   @End
	   public boolean logout() {

	          Identity.instance().logout();
	          return !Identity.instance().isLoggedIn();
	   }

	   private boolean userExists( String username ) {

		       try {
		    	     getAppstoreUserList().getAppstoreUser().setUsername( username );
		    	     getAppstoreUserList().getIdByUsername();
		       }
		       catch( javax.persistence.NoResultException e ) {

		    	      return false;
		       }

		       return true;
	   }

	   private AppstoreUserHome getAppstoreUserHome() {

		       return (AppstoreUserHome)Component.getInstance( "appstoreUserHome" );
	   }

	   private AppstoreUserList getAppstoreUserList() {

		       return (AppstoreUserList)Component.getInstance( "appstoreUserList" );
	   }
}