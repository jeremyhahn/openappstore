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

class UserWS {

	  public $id;
	  public $username;
	  public $password;
	  public $lastLogin;
	  public $created;
	  public $apiKey;
	  public $apiEnabled;

	  public function UserWS() { }
	 
	  public function setId( $id ) {

	  	     $this->id = $id;
	  }

	  public function getId() {

	  	     return $this->id;
	  }

	  public function setUsername( $username ) {

	  	     $this->username = $username;
	  }

	  public function getUsername() {

	  	     return $this->username;
	  }

	  public function setPassword( $password ) {

	  	     $this->password = $password;
	  }

	  public function setCreated( $created ) {

	  	     $this->created = $created;
	  }

	  public function getCreated() {

	  	     return $this->created;
	  }

	  public function setLastLogin( $lastLogin ) {

	  	     $this->lastLogin = $lastLogin;
	  }

	  public function getLastLogin() {

	  	     return $this->lastLogin;
	  }

	  public function setApiKey( $key ) {

	  	     $this->apiKey = $key;
	  }

	  public function getApiKey() {

	  	     return $this->apiKey;
	  }

	  public function setApiEnabled( $bool ) {

	  	     $this->apiEnabled = $bool;
	  }

	  public function getApiEnabled() {

	  	     return $this->enabled;
	  }

	  public function isApiEnabled() {

	  	     return $this->apiEnabled == true ? true : false;
	  }
}