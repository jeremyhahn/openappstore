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

import com.makeabyte.appstore.server.entity.AppstorePlatform;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

@Name("appstorePlatformList")
public class AppstorePlatformList extends EntityQuery<AppstorePlatform> {

	private static final long serialVersionUID = 8629964847508111338L;

	private static final String EJBQL = "select appstorePlatform from AppstorePlatform appstorePlatform";
	private static final String[] RESTRICTIONS = {
			"lower(appstorePlatform.description) like lower(concat(#{appstorePlatformList.appstorePlatform.description},'%'))",
			"lower(appstorePlatform.name) like lower(concat(#{appstorePlatformList.appstorePlatform.name},'%'))",};

	private AppstorePlatform appstorePlatform = new AppstorePlatform();

	public AppstorePlatformList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public AppstorePlatform getAppstorePlatform() {
		return appstorePlatform;
	}
	
	public List<SelectItem> getPlatforms() {

	   	   setEjbql( EJBQL );
	   	   setRestrictionExpressionStrings(Arrays.asList( new String[] { } ) );

	   	   List<SelectItem> items = new ArrayList<SelectItem>();

	   	   SelectItem si = new SelectItem();
	   	   si.setLabel( "Choose..." );
	   	   si.setValue( "0" );

	   	   items.add( si );

	   	   for( AppstorePlatform p : getResultList() ) {

	   		    SelectItem item = new SelectItem();
	   		    item.setValue( p.getId() );
	   		    item.setLabel( p.getName() );

	   		    items.add( item );
	   	   }

	   	   return items;
	}
}
