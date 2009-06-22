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

import com.makeabyte.appstore.server.entity.AppstoreApp;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

@Name("appstoreAppList")
public class AppstoreAppList extends EntityQuery<AppstoreApp> {

	private static final long serialVersionUID = 764003500115655190L;

	private static final String EJBQL = "select appstoreApp from AppstoreApp appstoreApp";

	private static final String[] RESTRICTIONS = {
			"lower(appstoreApp.appId) like lower(concat(#{appstoreAppList.appstoreApp.appId},'%'))",
			"lower(appstoreApp.description) like lower(concat(#{appstoreAppList.appstoreApp.description},'%'))",
			"lower(appstoreApp.name) like lower(concat(#{appstoreAppList.appstoreApp.name},'%'))",};

	private AppstoreApp appstoreApp = new AppstoreApp();

	public AppstoreAppList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public AppstoreApp getAppstoreApp() {
		return appstoreApp;
	}
	
	public List<SelectItem> getApplications() {

	   	   setEjbql( EJBQL );
	   	   setRestrictionExpressionStrings(Arrays.asList( new String[] { } ) );

	   	   List<SelectItem> items = new ArrayList<SelectItem>();

	   	   SelectItem si = new SelectItem();
	   	   si.setLabel( "Choose..." );
	   	   si.setValue( "0" );

	   	   items.add( si );

	   	   for( AppstoreApp a : getResultList() ) {

	   		    SelectItem item = new SelectItem();
	   		    item.setValue( a.getId() );
	   		    item.setLabel( a.getName() );

	   		    items.add( item );
	   	   }

	   	   return items;
	}
}