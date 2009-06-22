package com.makeabyte.appstore.webservice;

class AppstoreAPIException extends Exception {

	  private static final long serialVersionUID = -7392157993058496759L;

	  public AppstoreAPIException( Exception e ) {

		     super( e );
	  }

	  public AppstoreAPIException( String message ) {

		     super( message );
	  }
}