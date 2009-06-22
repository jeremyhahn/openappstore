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

import com.makeabyte.appstore.server.entity.AppstoreUser;

@Name("appstoreUserList")
public class AppstoreUserList extends EntityQuery<AppstoreUser> {

	   private static final long serialVersionUID = 7950660803116149702L;

	   private static final String EJBQL = "select appstoreUser from AppstoreUser appstoreUser";
	   private static final String[] RESTRICTIONS = {
			"lower(appstoreUser.apiKey) like lower(concat(#{appstoreUserList.appstoreUser.apiKey},'%'))",
			"lower(appstoreUser.password) like lower(concat(#{appstoreUserList.appstoreUser.password},'%'))",
			"lower(appstoreUser.username) like lower(concat(#{appstoreUserList.appstoreUser.username},'%'))",};

	   private AppstoreUser appstoreUser = new AppstoreUser();

	   public AppstoreUserList() {

		   	  setEjbql(EJBQL);
		   	  setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		   	  setMaxResults(25);
	   }

	   public AppstoreUser getAppstoreUser() {

		      return appstoreUser;
	   }

	   public List<SelectItem> getUsers() {

		   	   setEjbql( EJBQL );
		   	   setRestrictionExpressionStrings(Arrays.asList( new String[] { } ) );

		   	   List<SelectItem> items = new ArrayList<SelectItem>(0);

		   	   SelectItem si = new SelectItem();
		   	   si.setLabel( "Choose..." );
		   	   si.setValue( "0" );

		   	   items.add( si );

		   	   for( AppstoreUser u : getResultList() ) {

		   		    SelectItem item = new SelectItem();
		   		    item.setValue( u.getId() );
		   		    item.setLabel( u.getUsername() );

		   		    items.add( item );
		   	   }

		   	   return items;
	   }

	   public long getIdByUsername() {

		      String[] RESTRICTION = { "lower(appstoreUser.username) like lower(concat(#{appstoreUserList.appstoreUser.username},'%'))" };
		      setEjbql( EJBQL );
		      setRestrictionExpressionStrings( Arrays.asList( RESTRICTION ) );

		      return getSingleResult().getId();
	   }
}