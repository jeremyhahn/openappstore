/***********************************************************************
# OpenAppstore: Enterprise Open Source Application Store
# Copyright (C) 2007-2009  Make A Byte, inc
# www.makeabyte.com
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/>.
*************************************************************************/
package com.makeabyte.appstore.server.session;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.EventObject;

import javax.faces.event.ActionEvent;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

import com.icesoft.faces.component.inputfile.FileInfo;
import com.icesoft.faces.component.inputfile.InputFile;

@Name("UploadBean")
public class UploadBean implements Upload, Serializable {

	   private static final long serialVersionUID = 6682391259202405201L;

	   @Logger
	   private Log log;

	   private int percentage = -1;

	   public UploadBean() { }

	   /**
	    * Accessor for file upload percentage
	    */
	   public int getPercentage() {

    	      return percentage;
	   }

	   /**
	    * Mutator for file upload percentage
	    */
	   public void setPercentage( int percentage ) {

		      this.percentage = percentage;
	   }

       /**
        * ICEfaces progress bar event listener. Increments the upload
        * percentage with every call.
        */
	   public void progressListener( EventObject event ) {

    	      InputFile ifile = (InputFile) event.getSource();
              percentage = ifile.getFileInfo().getPercent();
       }

       /**
        * ICEfaces file upload event listener
        * 
        * @param event ICEfaces file upload component ActionEvent
        */
       public void actionListener( ActionEvent event ) {

   	          InputFile inputFile = (InputFile) event.getSource();
   	          FileInfo fileInfo = inputFile.getFileInfo();

   	          log.info( "Uploading file {0}", fileInfo.getFileName()  );

   	          if( fileInfo.isSaved() ) {

   	        	  log.info( "Upload successful" );
   	        	  FacesMessages.instance().add( "Successfully uploaded " + fileInfo.getFileName() );
   	        	  
   	        	  handleUpload( fileInfo );
   	          }

   	          else if( fileInfo.isFailed() ) {

   	        	   log.error( "Upload failed with status {0}", fileInfo.getStatus() );
   	        	   FacesMessages.instance().add( fileInfo.getException().getMessage() );
   	          }
       }

       public void handleUpload( FileInfo info ) {

    	      try {
    	    	    String[] pieces = info.getFileName().split( "\\." );

    	    	    // Tame .tar.gz problem child
    	    	    String extension = (info.getFileName().indexOf( ".tar.gz" ) != -1) ? ".tar.gz" : "." + pieces[pieces.length-1];

	    	        AppstoreAppHome appHome = (AppstoreAppHome)Component.getInstance( "appstoreAppHome" );
		   		  	appHome.getInstance().setSource( toByteArray( info.getFile() ) );
		   		  	appHome.getInstance().setSize( info.getSize() );
		   		  	appHome.getInstance().setExtension( extension );

		   		  	info.getFile().delete();
    	      }
    	      catch( IOException ioe ) {

    	    	     log.error( ioe );
    	    	     FacesMessages.instance().add( ioe.getMessage() );
    	      }
       }

       public byte[] toByteArray( File file ) throws IOException {  

    	      int length = (int)file.length();  
    	      byte[] array = new byte[length];  
    	      InputStream in = new FileInputStream( file );  
    	      int offset = 0;  
    	      while( offset < length) {  

    	    	     in.read(array, offset, (length - offset));  
    	             offset += length;  
    	      }  
    	      in.close();  
    	      return array;  
      } 
}