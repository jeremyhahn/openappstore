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

require_once 'PHPUnit/Framework.php';
require_once '../src/AppstoreAPI.php';

abstract class BaseTest extends PHPUnit_Framework_TestCase {

	     protected $api;

         public function setUp() {

	  	        $this->api = new AppstoreAPI( "http://localhost:8080/appstore/api?wsdl", "", "" );
	     }

	     public function testApiNotNull() {

	     	    PHPUnit_Framework_Assert::assertNotNull( $this->api, "Error creating api." );
	     }
}

function __autoload( $class ) {

         $mock = "mock/$class.php";
         $src  = "../src/$class.php";

         if( file_exists( $mock ) ) {

         	 require $mock;
         	 return;
         }

         else if( file_exists( $src ) ) {

         	 require $src;
         	 return;
         }

         else if( file_exists( "$class.php" ) )
         	 require "$class.php";
}
?>