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

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.makeabyte.appstore.server.entity.AppstoreAppType;

@Name("appstoreAppTypeList")
public class AppstoreAppTypeList extends EntityQuery<AppstoreAppType> {

	private static final long serialVersionUID = -6134105234120249658L;

	private static final String EJBQL = "select appstoreAppType from AppstoreAppType appstoreAppType";

	private static final String[] RESTRICTIONS = {
			"lower(appstoreAppType.description) like lower(concat(#{appstoreAppTypeList.appstoreAppType.description},'%'))",
			"lower(appstoreAppType.name) like lower(concat(#{appstoreAppTypeList.appstoreAppType.name},'%'))",};

	private AppstoreAppType appstoreAppType = new AppstoreAppType();

	public AppstoreAppTypeList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public AppstoreAppType getAppstoreAppType() {
		return appstoreAppType;
	}
}