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

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Singleton class responsible for reading appstore.properties configuration.
 */
public class AppstoreConfiguration {

	   private Log log = LogFactory.getLog( AppstoreConfiguration.class );
	   private static AppstoreConfiguration instance;
	   private Properties properties;

	   /**
	    * Private constructor
	    */
	   private AppstoreConfiguration() throws IOException {

		       properties = read();
	   }

	   /**
	    * Get an instance of this class which will contain
	    * a properties object with details of the current 
	    * appstore configuration.
	    * 
	    * @return An instance of this class
	    */
	   public static AppstoreConfiguration getInstance() throws IOException {

		      if( instance == null )
		    	  try {
		    		    instance = new AppstoreConfiguration();
		    	  }
		      	  catch( IOException e ) {			

			             throw e;
		      	  }
		      return instance;
	   }

	   /**
	    * Get the details of this configuration
	    * 
	    * @return A Properties object representing the current appstore configuration  
	    */
	   public Properties getProperties() {

		      return properties;
	   }

	   /**
	    * Reads and loads appstore.properties configuration
	    * 
	    * @return A Properties object created from the specified file
	    */
	   private Properties read() throws IOException {

		       Properties props = new Properties();

		       try {
		    	     props.load( AppstoreConfiguration.class.getResourceAsStream( "/appstore.properties" ) );
		    	     log.info( "Loaded appstore.properties" );
		       } 
		       catch( Exception e ) {

		    	   	  log.error( "Unable to load appstore.properties file" );
		    	   	  throw new IOException( e );
		       }

		       return props;
	   }

	   /**
	    * Write the properties configuration to disk
	    * 
	    * @throws IOException
	    */
	   public void write() throws IOException {

		      if( properties == null )
		    	  throw new RuntimeException( "Refuse to write null properties configuration" );

		      try {
		           properties.store( new FileOutputStream( "/appstore.properties" ), null );
		      }
		      catch( Exception e ) {

		    	     throw new IOException( e );
		      }
	   }
}