package com.makeabyte.appstore.webservice;

public class AppTypeWS {

	   private Long id;
	   private String name;
	   private String description;
	   private PlatformWS platform;

	   public AppTypeWS() { }

	   public AppTypeWS( String name, String description, PlatformWS platform ) {

		      this.name = name;
		      this.description = description;
		      this.platform = platform;
	   }

	   public AppTypeWS( long id, String name, String description, PlatformWS platform ) {

		      this.id = id;
		      this.name = name;
		      this.description = description;
		      this.platform = platform;
	   }

	   public void setId( long id ) {

		      this.id = id;
	   }

	   public long getId() {

		      return id;
	   }

	   public void setName( String name ) {

		      this.name = name;
	   }

	   public String getName() {

		      return name;
	   }

	   public void setDescription( String description ) {

		      this.description = description;
	   }

	   public String getDescription() {

		      return description;
	   }

	   public void setPlatformId( PlatformWS platform ) {

		      this.platform = platform;
	   }

	   public PlatformWS getPlatform() {

		      return platform;
	   }

	   public String toString() {

		      final String TAB = "	";

		      return new StringBuilder( "AppTypeWS ( " )
		     		 .append( super.toString() ).append( TAB )
		     		 .append( "id = " ).append( this.id ).append( TAB )
		     		 .append( "name = " ).append( this.name ).append( TAB )
		     		 .append( "description = " ).append( this.description ).append( TAB )
		     		 .append( "platform = " ).append( this.platform.toString() ).append( TAB )
		             .append( " )" ).toString();
	   }
}