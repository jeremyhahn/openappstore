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

import com.makeabyte.appstore.server.entity.AppstoreCategory;
import com.makeabyte.appstore.server.entity.AppstorePlatform;
import com.makeabyte.appstore.server.entity.AppstoreCategoryPlatformMap;
import com.makeabyte.appstore.server.entity.AppstoreCategoryPlatformMapId;

@Name("appstoreCategoryPlatformMapHome")
public class AppstoreCategoryPlatformMapHome extends EntityHome<AppstoreCategoryPlatformMap> {

	private static final long serialVersionUID = -6818651465035628766L;

	@In(create = true)
	AppstoreCategoryHome appstoreCategoryHome;

	@In(create = true)
	AppstorePlatformHome appstorePlatformHome;

	private static boolean categorySelected = false;
	private static boolean platformSelected = false;

	public void setAppstoreCategoryPlatformMapId(
			AppstoreCategoryPlatformMapId id) {
		setId(id);
	}

	public AppstoreCategoryPlatformMapId getAppstoreCategoryPlatformMapId() {
		return (AppstoreCategoryPlatformMapId) getId();
	}

	public AppstoreCategoryPlatformMapHome() {
		setAppstoreCategoryPlatformMapId(new AppstoreCategoryPlatformMapId());
	}

	@Override
	public boolean isIdDefined() {
		if (getAppstoreCategoryPlatformMapId().getCategoryId() == 0)
			return false;
		if (getAppstoreCategoryPlatformMapId().getPlatformId() == 0)
			return false;
		return true;
	}

	@Override
	protected AppstoreCategoryPlatformMap createInstance() {
		AppstoreCategoryPlatformMap appstoreCategoryPlatformMap = new AppstoreCategoryPlatformMap();
		appstoreCategoryPlatformMap.setId(new AppstoreCategoryPlatformMapId());
		return appstoreCategoryPlatformMap;
	}

	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	public void wire() {
		getInstance();
		AppstoreCategory appstoreCategory = appstoreCategoryHome
				.getDefinedInstance();
		if (appstoreCategory != null) {
			getInstance().setAppstoreCategory(appstoreCategory);
		}
		AppstorePlatform appstorePlatform = appstorePlatformHome
				.getDefinedInstance();
		if (appstorePlatform != null) {
			getInstance().setAppstorePlatform(appstorePlatform);
		}
	}

	public boolean isWired() {

		if( getInstance().getAppstoreCategory() == null )
			return false;

		if( getInstance().getAppstorePlatform() == null )
			return false;

		if( !categorySelected || !platformSelected )
			return false;

		return true;
	}

	public AppstoreCategoryPlatformMap getDefinedInstance() {
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

			   if( componentId.equals( "cpmCategoryId" ) )
				   categorySelected = false;
			   else if( componentId.equals( "cpmPlatformId" ) )
				   platformSelected = false;

			   return;
		   }

		   if( componentId.equals( "cpmCategoryId" ) ) {

			   instance.getId().setCategoryId( id );
			   appstoreCategoryHome.setId( Long.parseLong( e.getNewValue().toString() ) );
			   categorySelected = true;
		   }

		   else if( componentId.equals( "cpmPlatformId" ) ) {

 			    instance.getId().setPlatformId( id );
			    appstorePlatformHome.setId( id );
			    platformSelected = true;
		   }
	}
}