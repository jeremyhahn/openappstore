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

import static com.makeabyte.appstore.common.Crypto.encrypt;

import java.io.Serializable;

import javax.ejb.Stateless;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;
import org.jboss.seam.security.Identity;

import com.makeabyte.appstore.server.entity.AppstoreUser;

@Stateless
@Name("authenticator")
public class AuthenticatorBean implements Authenticator, Serializable {

	   private static final long serialVersionUID = -1175581029329825380L;

	   @Logger
	   private Log log;

	   @In
	   Identity identity;

	   @In
	   Credentials credentials;

	   @In(create=true)
	   AppstoreUserRoleMapList appstoreUserRoleMapList;

	   public boolean authenticate() {

		   	  String username = credentials.getUsername();
		   	  String password = credentials.getPassword();

		   	  AppstoreUser user = appstoreUserRoleMapList.getUserByUsername( username );
		   	  
		   	  if( user == null )
		   		  return false;

		   	  if( user.getUsername().equalsIgnoreCase( username )
		   			  && user.getPassword().equals( encrypt( password ) ) ) {

		   		  for( String role : appstoreUserRoleMapList.getRolesForUser( user.getId() ) ) {

		   			   identity.addRole( role );
		   			   log.info( "added role: {0}", role );
		   		  }

		   		  return true;
		   	  }

		   	  return false;
	   }

	   public void logout() {

		   	  log.info( "Logging out" );
		   	  identity.logout();
	   }
}