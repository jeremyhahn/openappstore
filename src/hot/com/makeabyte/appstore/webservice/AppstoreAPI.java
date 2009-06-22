package com.makeabyte.appstore.webservice;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Name;

@Stateless
@Name("AppstoreAPI")
@WebService(name = "AppstoreAPI", serviceName = "AppstoreAPI",
		targetNamespace = "http://webservice.appstore.makeabyte.com")
@SOAPBinding( style = SOAPBinding.Style.RPC )
public class AppstoreAPI implements AppstoreAPIRemote {

	   public AppstoreAPI() {

		      log( "AppstoreAPI created" );
	   }

	   @WebMethod
	   public String test()  {

		      return "AppstoreAPI works!";
	   }

	   @WebMethod
	   public boolean login( String username, String password, String apiKey )  {

		      log( "login WebMethod Requested" );

		   	  /* USE THIS IF YOUR SOAP CLIENT DOES NOT SUPPORT AUTOMATIC SESSION PROPAGATION
		   	   *
		   	   * import org.jboss.seam.web.ServletContexts;
		   	   * 
		   	   * String sessionId = ServletContexts.instance().getRequest().getSession().getId();
		   	   * if( result.equals( "loggedIn" ) )
		   	   *     return sessionId;
		   	   */

		   	  UserAction action = getUserAction();
		   	  action.createAppstoreUser();
		   	  return action.login( username, password, apiKey );
	   }

	   @WebMethod
	   public boolean logout() {

	          UserAction action = getUserAction();
	          action.createAppstoreUser();
	          return action.logout();
	   }

	   @WebMethod
	   public void createUser( UserWS user ) {

		   	  log( "createUser WebMethod executed" );

		      UserAction action = getUserAction();
		      action.createAppstoreUser();
		      action.createUser( user );
	   }

	   @WebMethod
	   public void deleteUser( Long userId ) {

		      log( "deleteUser WebMethod executed" );

		      UserAction action = getUserAction();
		      action.createAppstoreUser();
		      action.deleteUser( userId );
	   }

	   @WebMethod
	   public byte[] download( long id ) {

		      log( "download WebMethod executed" );

		      AppAction action = getAppAction();
		      return action.download( id );
	   }
	   
	   @WebMethod
	   public AppWS[] getAppsByUserId( long id ) {

		      log( "getAppsByUserId WebMethod Executed" );

		      AppAction action = getAppAction();
		      return action.getAppsByUserId( id );
	   }

	   @WebMethod
	   public AppWS[] getAppsByPlatformId( long id ) {

		      log( "getAppsByPlatformId WebMethod Executed" );

		      AppAction action = getAppAction();
		      return action.getAppsByPlatformId( id );
	   }

	   @WebMethod
	   public void addAppToCategory( long appId, long categoryId ) {

		   	  log( "addAppToCategory WebMethod Executed" );

		      AppAction action = getAppAction();
		      action.addAppToCategory( appId, categoryId );
	   }

	   @WebMethod
	   public void addAppToPlatform( long appId, long platformId ) {

		   	  log( "addAppToPlatform WebMethod Executed" );

		      AppAction action = getAppAction();
		      action.addAppToPlatform( appId, platformId );
	   }

	   private UserAction getUserAction() {

		       return (UserAction) Component.getInstance( UserAction.class, true );
	   }

	   private AppAction getAppAction() {

		       return (AppAction) Component.getInstance( AppAction.class, true );
	   }

	   private void log( String message ) {

		       System.out.println( "com.makeabyte.appstore.server.webservice.AppstoreAPI: " + message );
	   }
}