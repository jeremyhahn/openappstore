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

public class AppTypeWS {

	   private Long id;
	   private String name;
	   private String description;
	   private PlatformWS platform;

	   public AppTypeWS() { }

	   public AppTypeWS( String name, String description, PlatformWS platform ) {

		      this.name = name;
		      this.description = description;
		      this.platform = platform;
	   }

	   public AppTypeWS( long id, String name, String description, PlatformWS platform ) {

		      this.id = id;
		      this.name = name;
		      this.description = description;
		      this.platform = platform;
	   }

	   public void setId( long id ) {

		      this.id = id;
	   }

	   public long getId() {

		      return id;
	   }

	   public void setName( String name ) {

		      this.name = name;
	   }

	   public String getName() {

		      return name;
	   }

	   public void setDescription( String description ) {

		      this.description = description;
	   }

	   public String getDescription() {

		      return description;
	   }

	   public void setPlatformId( PlatformWS platform ) {

		      this.platform = platform;
	   }

	   public PlatformWS getPlatform() {

		      return platform;
	   }

	   public String toString() {

		      final String TAB = "	";

		      return new StringBuilder( "AppTypeWS ( " )
		     		 .append( super.toString() ).append( TAB )
		     		 .append( "id = " ).append( this.id ).append( TAB )
		     		 .append( "name = " ).append( this.name ).append( TAB )
		     		 .append( "description = " ).append( this.description ).append( TAB )
		     		 .append( "platform = " ).append( this.platform.toString() ).append( TAB )
		             .append( " )" ).toString();
	   }
}