package com.makeabyte.appstore.webservice;

import javax.jws.WebService;

@WebService
public interface AppstoreAPIRemote {

 	   public String test();

	   public boolean login( String username, String password, String apiKey );
	   public boolean logout();

	   public void createUser( UserWS user );
	   public void deleteUser( Long userId );

	   public AppWS[] getAppsByPlatformId( long id );
	   public byte[] download( long id );
}