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
package com.makeabyte.appstore.common;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Crypto {

	   public static String encrypt( String data ) {

		      try {
		    	     String algorithm = null;
		
				  	 // Read in encryption algorithm from appstore.properties
					 AppstoreConfiguration configs = AppstoreConfiguration.getInstance();
					 algorithm = configs.getProperties().getProperty( "security.message.digest" );
					 
					 // Encrypt the password
                     byte[] passwordBytes;
					 MessageDigest md = java.security.MessageDigest.getInstance( algorithm );  
				     md.reset();
				     md.update( data.getBytes() );
				     passwordBytes = md.digest();

				     // Convert to digest to hex
				     StringBuffer hexString = new StringBuffer();
				     for( int i=0; i < passwordBytes.length; i++ ) {
				
				          String hex = Integer.toHexString(0xFF & passwordBytes[i]);
				          if( hex.length() == 1 )
				              hexString.append("0" + hex);
				          else
				              hexString.append(hex);
				      }

				      // String base64 = org.jboss.seam.util.Base64.encodeBytes( passwordBytes );
				      return hexString.toString();				
		      }
		      catch( IOException ioe ) {

		    	     log( ioe, 43 );
		      }
		      catch( NoSuchAlgorithmException e ) {

		    	     log( e, 47 );
		      }

		      throw new RuntimeException( "Unexpected error trying to encrypt data: " + data );
	   }

	   private static void log( Exception e, int line ) {
		   
		   	   System.out.println( "[ERROR] com.makeabyte.appstore.common.util.Crypto(line:" + line + "): " + e.getMessage() );
	   }
}