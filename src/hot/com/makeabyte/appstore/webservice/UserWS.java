package com.makeabyte.appstore.webservice;

import static com.makeabyte.appstore.common.Crypto.encrypt;

import java.util.Date;

public class UserWS {

	   private Long id;
	   private String username;
	   private String password;
	   private Date lastLogin;
	   private Date created;
	   private String apiKey;
	   private boolean apiEnabled;
	
	   public Long getId() {

		      return id;
	   }

	   public void setId(Long id) {

		   	  this.id = id;
	   }

	   public String getUsername() {

		   	  return username;
	   }

	   public void setUsername( String username ) {

		   	  this.username = username;
	   }

	   public String getPassword() {

		   	  return password;
	   }

	   public void setPassword( String password ) {

		   	  this.password = encrypt( password );
	   }

	   public Date getLastLogin() {
		
		   	  return lastLogin;
	   }

	   public void setLastLogin( Date lastLogin ) {

		      this.lastLogin = lastLogin;
	   }

	   public Date getCreated() {

		   	  return created;
	   }

	   public void setCreated( Date created ) {

		   	  this.created = created;
	   }

	   public String getApiKey() {

		      return apiKey;
	   }

	   public void setApiKey( String apiKey ) {

		      this.apiKey = apiKey;
	   }
	   
	   public void setApiEnabled( boolean bool ) {

		      this.apiEnabled = bool;
	   }

	   public boolean getApiEnabled() {

		   	  return apiEnabled;
	   }

 	   public String toString() {
 
		      final String TAB = "	";

		      return new StringBuilder( "UserWS ( " )
		     		 .append( super.toString() ).append( TAB )
		     		 .append( "id = " ).append( this.id ).append( TAB )
		     		 .append( "username = " ).append( this.username ).append( TAB )
		     		 .append( "password = " ).append( this.password ).append( TAB )
		     		 .append( "lastLogin = " ).append( this.lastLogin ).append( TAB )
		     		 .append( "created = " ).append( this.created ).append( TAB )
		     		 .append( "apiKey = " ).append( this.apiKey ).append( TAB )
		     		 .append( "apiEnabled = " ).append( this.apiEnabled ).append( TAB )
		             .append( " )" ).toString();
 	   }
}