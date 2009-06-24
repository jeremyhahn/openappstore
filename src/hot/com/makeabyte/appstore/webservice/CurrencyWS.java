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

public class CurrencyWS {

	   private long id;
	   private String code;
	   private String symbol;

	   public CurrencyWS() { }

	   public CurrencyWS( String code, String symbol ) {

		      this.code = code;
		      this.symbol = symbol;
	   }

	   public CurrencyWS( long id, String code, String symbol ) {

		      this.id = id;
		      this.code = code;
		      this.symbol = symbol;
	   }

	   public void setId( long id ) {

		      this.id = id;
	   }

	   public long getId() {

		      return id;
	   }

	   public void setCode( String code ) {

		      this.code = code;
	   }

	   public String getCode() {

		      return code;
	   }

	   public void setSymbol( String symbol ) {

		      this.symbol = symbol;
	   }

	   public String getSymbol() {

		      return symbol;
	   }

	   public String toString() {

		      final String TAB = "	";

		      return new StringBuilder( "CurrencyWS ( " )
		     		 .append( super.toString() ).append( TAB )
		     		 .append( "id = " ).append( this.id ).append( TAB )
		     		 .append( "code = " ).append( this.code ).append( TAB )
		     		 .append( "symbol = " ).append( this.symbol ).append( TAB )
		             .append( " )" ).toString();
	   }
}