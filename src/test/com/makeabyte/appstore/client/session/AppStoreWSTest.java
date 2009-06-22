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

import org.testng.annotations.Test;

import com.makeabyte.appstore.server.session.AppstoreUserList;
import com.makeabyte.appstore.test.BaseTest;
import com.makeabyte.appstore.webservice.AppstoreAPIRemote;
import com.makeabyte.appstore.webservice.UserWS;

import java.net.MalformedURLException;  
import java.net.URL;  
  
import javax.faces.model.SelectItem;
import javax.xml.namespace.QName;  
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;

public class AppStoreWSTest extends BaseTest {

	   private static String endpoint = "http://localhost:8080/appstore/api?wsdl";
	   private String apiUser = "admin";
	   private String apiPass = "test";
	   private String apiKey = "123qwe";

	   public AppStoreWSTest() { }
   
	   @Test
	   public void test() throws Exception {

		      Object ret1 = getAPI().test();
		      assert ret1.equals( "AppstoreAPI works!" );
	   }

	   @Test(dependsOnMethods = { "test" })
	   public void loginTest() throws Exception {

		      Object ret2 = getAPI().login( apiUser, apiPass, apiKey );

		      assert ret2.equals( true );
	   }

	   @Test(dependsOnMethods = { "loginTest" })
	   public void createUserTest() throws Exception {

		      Object ret2 = getAPI().login( apiUser, apiPass, apiKey );
		      LOG( "createUserTest: " + ret2 );

		      UserWS user = new UserWS();
		      user.setUsername( "java-api" );
		      user.setPassword( "test" );
		      user.setApiKey( "213qwe" );
		      user.setApiEnabled( true );
		      getAPI().createUser( user );

		      AppstoreUserList appstoreUserList = new AppstoreUserList();
	          boolean bFound = false;
		      for( SelectItem uzer : appstoreUserList.getUsers() )
		       	   if( uzer.getLabel().equals( user.getUsername() ) )
		        	   bFound = true;

	          assert bFound == true;
	   }

	   /* Helpers during testing */
	   private static AppstoreAPIRemote getAPI() throws MalformedURLException {

		       AppstoreAPIRemote api = getPort( endpoint );
		       BindingProvider bindingProvider = (BindingProvider)api;
		       bindingProvider.getRequestContext().put( BindingProvider.SESSION_MAINTAIN_PROPERTY, true );

		       return api;
	   }

       private static AppstoreAPIRemote getPort( String endpoint ) throws MalformedURLException {

    	       QName qname = new QName( "http://webservice.appstore.makeabyte.com", "AppstoreAPI" );
    	       URL url = new URL( endpoint );

    	       Service service = Service.create( url, qname );
    	       return service.getPort( AppstoreAPIRemote.class );  
       }       
       private void LOG( Object message ) {
    	   
    	   	   System.out.println( message );
       }
}