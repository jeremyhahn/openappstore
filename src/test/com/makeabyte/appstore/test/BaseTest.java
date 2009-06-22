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
package com.makeabyte.appstore.test;

import org.jboss.seam.mock.SeamTest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest extends SeamTest {

	   @BeforeMethod
	   public void begin() {
		      super.begin();
	   }

	   @AfterMethod
	   public void end() {
	          // super.end();
	   }

	   @BeforeClass
	   public void init() throws Exception {
	          //super.init();
	   }

	   @AfterClass
	   public void cleanup() throws Exception {
	          //super.cleanup();
	   }

	   /**
	    * Helper method for testing components which require authentication
	    * 
	    * @throws Exception
	    */
	   public void login() throws Exception {

			  new FacesRequest( "/login.xhtml" ) {

			          @Override
			          protected void updateModelValues() throws Exception {

			        	        assert !isSessionInvalid();
			                    setValue( "#{identity.username}", "admin" );
			                    setValue( "#{identity.password}", "test" );
			          }
			          @Override
			          protected void invokeApplication() {

			        	        invokeAction( "#{identity.login}" );
			          }

			          @Override
			          protected void renderResponse() {

			                    assert getValue( "#{identity.loggedIn}" ).equals( true );
			                    Assert.assertEquals( "ldapadmin", getValue( "#{identity.username}" ) );
			                    assert getValue( "#{identity.hasRole('admin')}" ).equals( true );
			          }
			  }.run();
	   }

	   protected void log( String message ) {

	       		 System.out.println( message );
       }
} 