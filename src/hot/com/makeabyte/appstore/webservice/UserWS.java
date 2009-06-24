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

public class UserWS {

	   private Long id;
	   private String username;
	   private String password;
	   private Date lastLogin;
	   private Date created;
	   private String apiKey;
	   private boolean apiEnabled;
	
	   public Long getId() {

		      return id;
	   }

	   public void setId(Long id) {

		   	  this.id = id;
	   }

	   public String getUsername() {

		   	  return username;
	   }

	   public void setUsername( String username ) {

		   	  this.username = username;
	   }

	   public String getPassword() {

		   	  return password;
	   }

	   public void setPassword( String password ) {

		   	  this.password = encrypt( password );
	   }

	   public Date getLastLogin() {
		
		   	  return lastLogin;
	   }

	   public void setLastLogin( Date lastLogin ) {

		      this.lastLogin = lastLogin;
	   }

	   public Date getCreated() {

		   	  return created;
	   }

	   public void setCreated( Date created ) {

		   	  this.created = created;
	   }

	   public String getApiKey() {

		      return apiKey;
	   }

	   public void setApiKey( String apiKey ) {

		      this.apiKey = apiKey;
	   }
	   
	   public void setApiEnabled( boolean bool ) {

		      this.apiEnabled = bool;
	   }

	   public boolean getApiEnabled() {

		   	  return apiEnabled;
	   }

 	   public String toString() {
 
		      final String TAB = "	";

		      return new StringBuilder( "UserWS ( " )
		     		 .append( super.toString() ).append( TAB )
		     		 .append( "id = " ).append( this.id ).append( TAB )
		     		 .append( "username = " ).append( this.username ).append( TAB )
		     		 .append( "password = " ).append( this.password ).append( TAB )
		     		 .append( "lastLogin = " ).append( this.lastLogin ).append( TAB )
		     		 .append( "created = " ).append( this.created ).append( TAB )
		     		 .append( "apiKey = " ).append( this.apiKey ).append( TAB )
		     		 .append( "apiEnabled = " ).append( this.apiEnabled ).append( TAB )
		             .append( " )" ).toString();
 	   }
}