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
package com.makeabyte.appstore.client.session;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.jboss.seam.Component;

import com.makeabyte.appstore.server.session.Authenticator;
import com.makeabyte.appstore.test.BaseTest;

public class AuthenticatorTest extends BaseTest {

	   public AuthenticatorTest() { }

	   @Test
	   public void authenticate() throws Exception {

		      new ComponentTest() {

				@Override
				protected void testComponents() throws Exception {

					      Authenticator auth = (Authenticator)Component.getInstance( "authenticator" );

					      assert !isSessionInvalid();
					      assert getValue( "#{identity.loggedIn}" ).equals( false );

		                  setValue( "#{identity.username}", "admin" );
		                  setValue( "#{identity.password}", "test" );

                          Assert.assertEquals( "admin", getValue( "#{identity.username}" ) );
		                  Assert.assertEquals( "test", getValue( "#{identity.password}" ) );

		                  assert auth.authenticate() == true;

		                  assert getValue( "#{identity.hasRole('admin')}" ).equals( true );
		                  assert getValue( "#{identity.hasRole('developer')}" ).equals( true );
 				} 
		      
		      }.run();
	   }
}