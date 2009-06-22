<?php

# appstore-php-api
# Copyright (C) 2007-2009  Make A Byte, inc
# http://www.makeabyte.com

# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.

# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.

# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.

require_once 'AppstoreAPIRemote.php';

class AppstoreAPI implements AppstoreAPIRemote {

	  private $client;

	  public function AppstoreAPI( $endpoint, $username, $password ) {

	  	     $this->client = new SoapClient( $endpoint, array( 'trace' => true, 'cache_wsdl' => 0 ) );
	  }

	  /**
	   * Gets the SOAP client handle
	   * 
	   * @return SoapClient handle
	   */
	  public function getClient() {

	  	     return $this->client;
	  }

	  /**
	   * Writes a debug message to javascript debug console (such as firebug plugin for firefox)
	   * 
	   * @return void
	   */
	  public function debug( $message ) {

	  	     $pieces = explode( "\n", $message );
	  	     
	  	     echo '<script type="text/javascript" language="javascript">';
	  	     
	  	     foreach( $pieces as $piece )
		  	     echo 'console.log( "' . $piece . '" );';

		  	 echo '</script>"';
	  }

	  /**
	   * Debugs the last XML request sent to the AppStore API using javascript console.
	   *  
	   * @return void
	   */
	  public function debugRequest() {

	  	     $this->debug( $this->client->__getLastRequest() );
	  }

	  /**
	   * Debugs the last XML request by sending it to the browser as text/xml
	   * 
	   * @return void
	   */
	  public function debugRequestAsXML() {

	  	     header( 'Content-Type: text/xml;' );
             print( $this->client->__getLastRequest() );
	  }

	  /* API methods */

	  /**
	   * Call the API test method
	   *
	   * @return string AppStoreWS works!
	   * @see src/AppstoreAPIRemote#test()
	   */
	  public function test() {

	  	     return $this->client->test();
	  }

	  /**
	   * Authenticate the specified username/password/key combination and
	   * establish a session with the API endpoint.
	   * 
	   * @return bool True if successful, false on fail
	   * @throws AppstoreAPIException If remote exceptions occur
	   * @see src/AppstoreAPIRemote#login($username,$password,$apiKey)
	   */
	  public function login( $username, $password, $apiKey ) {

	  	     try {
	  	     	   return $this->client->login( $username, $password, $apiKey );	
	  	     }
	  	     catch( SoapFault $e ) {

	  	     	    throw new AppstoreAPIException( $e );
	  	     }
	  }

	  /**
	   * Log out the user and kill the http session
	   * 
	   * @return bool True if successful, false on fail
	   * @see src/AppstoreAPIRemote#logout()
	   */
	  public function logout() {

	  	     return $this->client->logout();
	  }

	  /**
	   * Create a new appstore user account
	   * 
	   * @param UserWS The user object to create
	   * @throws AppstoreAPIException
	   * @see src/AppstoreAPIRemote#createUser($UserWS)
	   */
	  public function createUser( UserWS $user ) {

	  	     try {
	  	     	   return $this->client->createUser( $user );
	  	     }
	  	     catch( SoapFault $e ) {

	  	     	    throw new AppstoreAPIException( $e );
	  	     }
	  }

	  /**
	   * Deletes an appstore user account
	   * 
	   * @param userId The id of the account to delete
	   * @throws AppstoreAPIException
	   * @see src/AppstoreAPIRemoteRemote#deleteUser($UserWS) 
	   */
	  public function deleteUser( $userId ) {

	  		 try {
	  	     	   return $this->client->deleteUser( $userId );
	  	     }
	  	     catch( SoapFault $e ) {

	  	     	    throw new AppstoreAPIException( $e );
	  	     }
	  }
	  
	  /**
	   * Returns an array of AppWS objects which correspond to the specified platform id
	   * 
	   * @param long The platform id
	   * @throws AppstoreAPIException
	   * @see src/AppstoreAPIRemote#getAppsByPlatformId($platformId) 
	   */
	  public function getAppsByPlatformId( $platformId ) {

	  	     try {
	  	     		return $this->client->getAppsByPlatformId( $platformId );
	  	     }
	  	     catch( SoapFault $e ) {

	  	     	    throw new AppstoreAPIException( $e );
	  	     }
	  }

	  /**
	   * Returns an array of AppWS objects which correspond to the specified user id
	   * 
	   * @param long The user id
	   * @throws AppstoreAPIException
	   * @see src/AppstoreAPIRemote
	   */
	  public function getAppsByUserId( $userId ) {
	  	
	  	     try {
	  	     	   return $this->client->getAppsByUserId( $userId );
	  	     }
	  	     catch( SoapFault $e ) {

	  	     	    throw new AppstoreAPIException( $e );
	  	     }
	  }

	  /**
       * Adds an application to the specified category
       * 
       * @param long The unique id of the application
       * @param long The unique id of the category
       * @throws AppstoreAPIException
       * @see src/AppstoreAPIRemote#addAppToCategory($appId,$categoryId)
	   */
	  public function addAppToCategory( $appId, $categoryId ) {

	  	     try {
	  	     	   $this->client->addAppToCategory( $appId, $categoryId );
	  	     }
	  	     catch( SoapFault $e ) {

	  	     	    throw new AppstoreAPIException( $e );
	  	     }
	  }

	  /**
	   * Gets an application as a byte array (suitable for writing to the file system)
	   * 
	   * @param long The application id
	   * @throws AppstoreAPIException If any remote exceptions occur
	   * @see src/AppstoreAPIRemote#download($id)
	   */
	  public function download( $id ) {
	  	
	  	     try {
	  	     	   return $this->client->download( $id );
	  	     }
	  	     catch( SoapFault $e ) {

	  	     	    throw new AppstoreAPIException( $e );
	  	     }
	  }
}