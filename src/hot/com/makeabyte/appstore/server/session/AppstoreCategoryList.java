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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.makeabyte.appstore.server.entity.AppstoreCategory;

@Name("appstoreCategoryList")
public class AppstoreCategoryList extends EntityQuery<AppstoreCategory> {

	private static final long serialVersionUID = 4223955469195084376L;

	private static final String EJBQL = "select appstoreCategory from AppstoreCategory appstoreCategory";

	private static final String[] RESTRICTIONS = {
			"lower(appstoreCategory.description) like lower(concat(#{appstoreCategoryList.appstoreCategory.description},'%'))",
			"lower(appstoreCategory.name) like lower(concat(#{appstoreCategoryList.appstoreCategory.name},'%'))",};

	private AppstoreCategory appstoreCategory = new AppstoreCategory();

	public AppstoreCategoryList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public AppstoreCategory getAppstoreCategory() {
		return appstoreCategory;
	}
	
	public List<SelectItem> getCategories() {

		   setEjbql( EJBQL );

		   List<SelectItem> items = new ArrayList<SelectItem>();

		   SelectItem si = new SelectItem();
	       si.setLabel( "Choose..." );
	       si.setValue( "0" );

	       items.add( si );
	    	
		   for( AppstoreCategory c : getResultList() ) {

			    SelectItem item = new SelectItem();
			    item.setValue( c.getId() );
			    item.setLabel( c.getName() );

			    items.add( item );
		   }

		   return items;
	}
}