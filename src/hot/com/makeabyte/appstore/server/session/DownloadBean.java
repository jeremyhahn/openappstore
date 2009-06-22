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

import com.makeabyte.appstore.common.Mime;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.log.Log;

import com.makeabyte.appstore.server.entity.AppstoreApp;

@Stateless
@Name("DownloadBean")
public class DownloadBean implements Download {

	   @Logger
	   private Log log;

	   @In
	   private EntityManager entityManager;

	   @In(value="#{facesContext.externalContext}")
	   private ExternalContext extCtx;

	   @In(value="#{facesContext}")
	   FacesContext facesContext;

	   @RequestParameter
	   private Long applicationId;

	   public void download() {

		      AppstoreApp app = entityManager.find( AppstoreApp.class, applicationId );
		      HttpServletResponse response = (HttpServletResponse)extCtx.getResponse();
		      response.setContentType( Mime.getTypeByExtension( app.getExtension() ) );
	          response.addHeader( "Content-disposition", "attachment; filename=\"" + app.getAppId() + app.getExtension() + "\"");

	          try {
	        	     ServletOutputStream os = response.getOutputStream();
	        	     os.write( app.getSource() );
	        	     os.flush();
	        	     os.close();
	        	     facesContext.responseComplete();
	          }
	          catch( Exception e ) {

	        	     log.error("\nDownlaod failed: " + e.toString() + "\n");
	          }
	   }
}