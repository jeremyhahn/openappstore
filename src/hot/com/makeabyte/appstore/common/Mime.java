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
package com.makeabyte.appstore.common;

import org.jboss.seam.annotations.Name;

//import javax.faces.model.SelectItem;

@Name("Mime")
public class Mime {

       private static String[] extensions = { ".bin", ".bsh", ".bz", ".bz2", ".c",
    	                                     ".c++", ".class", ".exe", ".gtar", ".gz",
    	                                     ".gzip", ".hlp", ".hqx", ".htc", ".htm",
    	                                     ".html", ".htmls", ".java", ".ksh", ".sh",
    	                                     ".sit", ".swf", ".tar", ".tgz", ".zip",
    	                                     ".tar.gz", ".iso", ".jhp" };

       private static String[] types = { "application/octet-stream", "application/x-bsh", "application/x-bzip", "application/x-bzip2", "text/plain",
    	                                 "text/plain", "application/x-java-class", "application/octet-stream", "application/x-gtar", "application/x-gzip",
    	                                 "application/x-gzip", "application/x-winhelp", "application/binhex", "application/hta", "text/html",
    	                                 "text/html", "text/html", "text/plain", "text/x-script.ksh", "text/x-script.sh",
    	                                 "application/x-stuffit", "application/x-shockwave-flash", "application/x-tar", "application/x-compressed", "application/zip",
    	                                 "application/x-tar-gz", "application/octet-stream", "application/zip" };

	   public static String getTypeByExtension( String extension ) {

		      for( int i=0; i<extensions.length; i++ ) {

		    	   if( extensions[i].equals( extension ) )
		    		   return types[i];
		      }

		      throw new RuntimeException( "Unsupported mime type: " + extension );
	   }

	   /*
	   public List<SelectItem> getExtensionsAsSelectItems() {

		      List<SelectItem> items = new ArrayList<SelectItem>(0);

		      for( int i=0; i<extensions.length; i++ ) {

		    		   SelectItem item = new SelectItem();
		    		   item.setLabel( extensions[i] );
		    		   item.setValue( extensions[i] );

		    		   items.add( item );
		      }
	          return items;
	   }
	   */
}