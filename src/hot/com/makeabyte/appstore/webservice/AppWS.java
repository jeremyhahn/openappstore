package com.makeabyte.appstore.webservice;

public class AppWS {

	   private long id;
	   private String appId;
	   private String name;
	   private String description;
	   private float cost;
	   private byte[] source;
	   private long size;
	   private String extension;
	   private CurrencyWS currency;
	   private AppTypeWS appType;

	   public AppWS() { }

	   public AppWS( String appId, String name, String description, float cost, byte[] source,
			   					long size, String extension, CurrencyWS currency, AppTypeWS appType ) {

		   	  this.appId = appId;
		   	  this.name = name;
		   	  this.description = description;
		   	  this.cost = cost;
		   	  this.source = source;
		   	  this.size = size;
		   	  this.extension = extension;
		   	  this.currency = currency;
		   	  this.appType = appType;
	   }

	   public AppWS( long id, String appId, String name, String description, float cost,
			   			byte[] source, long size, String extension, CurrencyWS currency, AppTypeWS appType ) {

		      this.id = id;
			  this.appId = appId;
			  this.name = name;
			  this.description = description;
			  this.cost = cost;
			  this.source = source;
			  this.size = size;
			  this.extension = extension;
			  this.currency = currency;
			  this.appType = appType;
	   }

	   public void setId( long id ) {

		      this.id = id;
	   }

	   public long getId() {

		      return id;
	   }

	   public void setAppId( String appId ) {

		      this.appId = appId;
	   }

	   public String getAppId() {

		      return appId;
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

	   public void setCost( float cost ) {

		      this.cost = cost;
	   }

	   public float getCost() {

		      return cost;
	   }

	   public void setSource( byte[] source ) {

		      this.source = source;
	   }

	   public byte[] getSource() {

		      return source;
	   }

	   public void setSize( long size ) {

		      this.size = size;
	   }

	   public long getSize() {

		      return size;
	   }

	   public void setExtension( String extension ) {

		      this.extension = extension;
	   }

	   public String getExtension() {

		      return extension;
	   }

	   public void setCurrency( CurrencyWS currency ) {

		      this.currency = currency;
	   }

	   public CurrencyWS getCurrency() {

		      return currency;
	   }

	   public void setAppType( AppTypeWS appType ) {

		      this.appType = appType;
	   }

	   public AppTypeWS getAppType() {

		      return appType;
	   }
}