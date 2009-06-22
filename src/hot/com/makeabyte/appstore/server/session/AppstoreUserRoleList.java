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

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

import com.makeabyte.appstore.server.entity.AppstoreUserRole;

@Name("appstoreUserRoleList")
public class AppstoreUserRoleList extends EntityQuery<AppstoreUserRole> {

	private static final long serialVersionUID = -5798737406855917436L;

	private static final String EJBQL = "select appstoreUserRole from AppstoreUserRole appstoreUserRole";

	private static final String[] RESTRICTIONS = {
			"lower(appstoreUserRole.description) like lower(concat(#{appstoreUserRoleList.appstoreUserRole.description},'%'))",
			"lower(appstoreUserRole.name) like lower(concat(#{appstoreUserRoleList.appstoreUserRole.name},'%'))",};

	private AppstoreUserRole appstoreUserRole = new AppstoreUserRole();

	public AppstoreUserRoleList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public AppstoreUserRole getAppstoreUserRole() {
		return appstoreUserRole;
	}

	public List<SelectItem> getRoles() {

		   setEjbql( EJBQL );

		   List<SelectItem> items = new ArrayList<SelectItem>();

		   SelectItem si = new SelectItem();
	       si.setLabel( "Choose..." );
	       si.setValue( "0" );

	       items.add( si );
	    	
		   for( AppstoreUserRole ur : getResultList() ) {

			    SelectItem item = new SelectItem();
			    item.setValue( ur.getId() );
			    item.setLabel( ur.getName() );

			    items.add( item );
		   }

		   return items;
	}
}