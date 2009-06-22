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
package com.makeabyte.appstore.server.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AppstoreCategoryPlatformMapId implements java.io.Serializable {

	private static final long serialVersionUID = 2199667391495130905L;
	private long categoryId;
	private long platformId;

	public AppstoreCategoryPlatformMapId() {
	}

	public AppstoreCategoryPlatformMapId(long categoryId, long platformId) {
		this.categoryId = categoryId;
		this.platformId = platformId;
	}

	@Column(name = "categoryId", nullable = false)
	public long getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	@Column(name = "platformId", nullable = false)
	public long getPlatformId() {
		return this.platformId;
	}

	public void setPlatformId(long platformId) {
		this.platformId = platformId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AppstoreCategoryPlatformMapId))
			return false;
		AppstoreCategoryPlatformMapId castOther = (AppstoreCategoryPlatformMapId) other;

		return (this.getCategoryId() == castOther.getCategoryId())
				&& (this.getPlatformId() == castOther.getPlatformId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getCategoryId();
		result = 37 * result + (int) this.getPlatformId();
		return result;
	}

}
