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

require_once 'AppstoreAPI.php';
$api = new AppstoreAPI( "http://localhost:8080/appstore/api?wsdl", "", "" );

?>
<html>
	<head>
		<title>Appstore PHP Integration Example</title>
		<script type="text/javascript">
		<!--
		    console.log( "--------------  BEGIN TEST ------------------" );
		    console.log( "Appstore PHP Integration Example..." );
	        console.log( "Executing remote request: $client->test()" );	
	        console.log( "Result: <?php echo $api->test();?>" );
	        console.log( "-------------- TEST FINISHED ----------------" );
	    //-->
		</script>
	</head>

	<body>
	   <div id="phpExample" style="font-weight: bold; font-size: 16px;">PHP Integration Example (SOAP)</div>
	   
	   <div id="phpExampleLogin">test:</div>
	   <div id="phpExampleOutput"><?php echo $api->test(); ?></div>
	   
	   <hr/>

	   <div id="phpExampleLogin">login: </div>
	   <div id="phpExampleLoginOutput"><?php echo $api->login( "admin", "test", "123qwe" ); ?></div>
	   
	   <hr/>

	   <div id="phpExampleCreateUser">createUser: </div>
	   <div id="phpExampleCreateUserOutput">
	   		<?php
	   		   $UserWS = new UserWS();
			   $UserWS->setUsername( "phpapi" );
			   $UserWS->setPassword( "123qwe" );
			   $UserWS->setApiKey( "123456" );
			   $UserWS->setApiEnabled( true );

			   echo $api->createUser( $UserWS );
	   		?>
	   	</div>

		<hr/>

	   	<div id="phpExampleDeleteUser">deleteUser: </div>
	   	<div id="phpExampleDeleteUserOutput"><?php echo $api->deleteUser( 4 ); ?></div>

		<hr/>

		<div id="phpExampleDeleteUser">download: </div>
	   	<div id="phpExampleDeleteUserOutput">
	   		<?php
				 $handle = fopen( "test.zip", 'w') or die("can't open file");
				 fwrite( $handle, $api->download( 3 ) );
				 fclose( $handle );

				 echo file_exists( "test.zip" );
	   		 ?>
	   	</div>

		<hr/>

	   	<div id="phpExampleLogout">logout: </div>
	   	<div id="phpExampleLogoutOutput"><?php $api->logout(); ?></div>
	</body>
</html>