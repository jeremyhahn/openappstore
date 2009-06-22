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

import javax.faces.event.ValueChangeEvent;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import com.makeabyte.appstore.server.entity.AppstoreApp;
import com.makeabyte.appstore.server.entity.AppstoreCategory;
import com.makeabyte.appstore.server.entity.AppstoreAppCategoryMap;
import com.makeabyte.appstore.server.entity.AppstoreAppCategoryMapId;

@Name("appstoreAppCategoryMapHome")
public class AppstoreAppCategoryMapHome	extends	EntityHome<AppstoreAppCategoryMap> {

	private static final long serialVersionUID = -5557319599678174575L;

	@In(create = true)
	AppstoreAppHome appstoreAppHome;

	@In(create = true)
	AppstoreCategoryHome appstoreCategoryHome;

	private static boolean appSelected = false;
	private static boolean categorySelected = false;

	public void setAppstoreAppCategoryMapId(AppstoreAppCategoryMapId id) {
		setId(id);
	}

	public AppstoreAppCategoryMapId getAppstoreAppCategoryMapId() {
		return (AppstoreAppCategoryMapId) getId();
	}

	public AppstoreAppCategoryMapHome() {
		setAppstoreAppCategoryMapId(new AppstoreAppCategoryMapId());
	}

	@Override
	public boolean isIdDefined() {
		if (getAppstoreAppCategoryMapId().getAppId() == 0)
			return false;
		if (getAppstoreAppCategoryMapId().getCategoryId() == 0)
			return false;
		return true;
	}

	@Override
	protected AppstoreAppCategoryMap createInstance() {
		AppstoreAppCategoryMap appstoreAppCategoryMap = new AppstoreAppCategoryMap();
		appstoreAppCategoryMap.setId(new AppstoreAppCategoryMapId());
		return appstoreAppCategoryMap;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		AppstoreApp appstoreApp = appstoreAppHome.getDefinedInstance();
		if (appstoreApp != null) {
			getInstance().setAppstoreApp(appstoreApp);
		}
		AppstoreCategory appstoreCategory = appstoreCategoryHome
				.getDefinedInstance();
		if (appstoreCategory != null) {
			getInstance().setAppstoreCategory(appstoreCategory);
		}
	}

	public boolean isWired() {

		if (getInstance().getAppstoreApp() == null)
			return false;

		if (getInstance().getAppstoreCategory() == null)
			return false;

		if( !appSelected || !categorySelected )
			return false;

		return true;
	}

	public AppstoreAppCategoryMap getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/**
	 * Handles JSF event associated with add or edit operation
	 * 
	 * @param e
	 */
	public void ValueChangeListener( ValueChangeEvent e ) {

		   String componentId = e.getComponent().getId();
		   Long id = Long.parseLong( e.getNewValue().toString() );

		   if( id.intValue() == 0 ) {

			   if( componentId.equals( "acmAppId" ) )
				   appSelected = false;
			   else if( componentId.equals( "acmCategoryId" ) )
				   categorySelected = false;

			   return;
		   }

		   if( componentId.equals( "acmAppId" ) ) {

			   instance.getId().setAppId( id );
			   appstoreAppHome.setId( Long.parseLong( e.getNewValue().toString() ) );
			   appSelected = true;
		   }

		   else if( componentId.equals( "acmCategoryId" ) ) {

 			    instance.getId().setCategoryId( id );
			    appstoreCategoryHome.setId( id );
			    categorySelected = true;
		   }
	}
}