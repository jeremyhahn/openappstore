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

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "appstore_currency_code")
public class AppstoreCurrencyCode implements java.io.Serializable {

	private static final long serialVersionUID = -8004500828044330771L;
	private Long id;
	private String code;
	private String symbol;
	private Set<AppstoreApp> appstoreApps = new HashSet<AppstoreApp>(0);

	public AppstoreCurrencyCode() {
	}

	public AppstoreCurrencyCode(String code) {
		this.code = code;
	}
	public AppstoreCurrencyCode(String code, Set<AppstoreApp> appstoreApps) {
		this.code = code;
		this.appstoreApps = appstoreApps;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "code", nullable = false, length = 3)
	@NotNull
	@Length(max = 3)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name = "symbol", nullable = false, length = 7)
	@NotNull
	@Length(max = 5)
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol( String symbol ) {
	    this.symbol = symbol;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstoreCurrencyCode")
	public Set<AppstoreApp> getAppstoreApps() {
		return this.appstoreApps;
	}

	public void setAppstoreApps(Set<AppstoreApp> appstoreApps) {
		this.appstoreApps = appstoreApps;
	}

}
