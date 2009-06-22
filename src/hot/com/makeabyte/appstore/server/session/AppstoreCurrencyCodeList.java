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

import com.makeabyte.appstore.server.entity.AppstoreCurrencyCode;

@Name("appstoreCurrencyCodeList")
public class AppstoreCurrencyCodeList extends EntityQuery<AppstoreCurrencyCode> {

	private static final long serialVersionUID = 5278527016725600953L;

	private static final String EJBQL = "select appstoreCurrencyCode from AppstoreCurrencyCode appstoreCurrencyCode";
	private static final String[] RESTRICTIONS = {"lower(appstoreCurrencyCode.code) like lower(concat(#{appstoreCurrencyCodeList.appstoreCurrencyCode.code},'%'))",};

	private AppstoreCurrencyCode appstoreCurrencyCode = new AppstoreCurrencyCode();

	public AppstoreCurrencyCodeList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public AppstoreCurrencyCode getAppstoreCurrencyCode() {
		return appstoreCurrencyCode;
	}
}
