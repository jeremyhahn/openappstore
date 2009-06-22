package com.makeabyte.appstore.webservice;

public class PlatformWS {

	   private long id;
	   private String name;
	   private String description;

	   public PlatformWS() { }

	   public PlatformWS( String name, String description ) {

		      this.name = name;
		      this.description = description;
	   }

	   public PlatformWS( long id, String name, String description ) {

		      this.id = id;
		      this.name = name;
		      this.description = description;
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

	   public String toString() {

		      final String TAB = "	";

		      return new StringBuilder( "PlatformWS ( " )
		     		 .append( super.toString() ).append( TAB )
		     		 .append( "id = " ).append( this.id ).append( TAB )
		     		 .append( "name = " ).append( this.name ).append( TAB )
		     		 .append( "description = " ).append( this.description ).append( TAB )
		             .append( " )" ).toString();
	   }
}