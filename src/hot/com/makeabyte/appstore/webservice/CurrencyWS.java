package com.makeabyte.appstore.webservice;

public class CurrencyWS {

	   private long id;
	   private String code;
	   private String symbol;

	   public CurrencyWS() { }

	   public CurrencyWS( String code, String symbol ) {

		      this.code = code;
		      this.symbol = symbol;
	   }

	   public CurrencyWS( long id, String code, String symbol ) {

		      this.id = id;
		      this.code = code;
		      this.symbol = symbol;
	   }

	   public void setId( long id ) {

		      this.id = id;
	   }

	   public long getId() {

		      return id;
	   }

	   public void setCode( String code ) {

		      this.code = code;
	   }

	   public String getCode() {

		      return code;
	   }

	   public void setSymbol( String symbol ) {

		      this.symbol = symbol;
	   }

	   public String getSymbol() {

		      return symbol;
	   }

	   public String toString() {

		      final String TAB = "	";

		      return new StringBuilder( "CurrencyWS ( " )
		     		 .append( super.toString() ).append( TAB )
		     		 .append( "id = " ).append( this.id ).append( TAB )
		     		 .append( "code = " ).append( this.code ).append( TAB )
		     		 .append( "symbol = " ).append( this.symbol ).append( TAB )
		             .append( " )" ).toString();
	   }
}