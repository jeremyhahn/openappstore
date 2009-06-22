package com.makeabyte.appstore.server.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

@Entity
@Table(name = "appstore_app")
public class AppstoreApp implements java.io.Serializable {

	private static final long serialVersionUID = -6253517832305674088L;
	private Long id;
	private AppstoreAppType appstoreAppType;
	private AppstoreCurrencyCode appstoreCurrencyCode;
	private String appId;
	private String name;
	private String description;
	private Float cost;
	private byte[] source;
	private long size;
	private String extension;
	private Set<AppstoreUserAppMap> appstoreUserAppMaps = new HashSet<AppstoreUserAppMap>(0);
	private Set<AppstoreAppPlatformMap> appstoreAppPlatformMaps = new HashSet<AppstoreAppPlatformMap>(0);
	private Set<AppstoreAppCategoryMap> appstoreAppCategoryMaps = new HashSet<AppstoreAppCategoryMap>(0);

	public AppstoreApp() {
	}

	public AppstoreApp(AppstoreAppType appstoreAppType,
			AppstoreCurrencyCode appstoreCurrencyCode, String appId) {
		this.appstoreAppType = appstoreAppType;
		this.appstoreCurrencyCode = appstoreCurrencyCode;
		this.appId = appId;
	}
	public AppstoreApp(AppstoreAppType appstoreAppType,
			AppstoreCurrencyCode appstoreCurrencyCode, String appId,
			String name, String description, Float cost, byte[] source,
			Set<AppstoreUserAppMap> appstoreUserAppMaps,
			Set<AppstoreAppPlatformMap> appstoreAppPlatformMaps,
			Set<AppstoreAppCategoryMap> appstoreAppCategoryMaps) {
		this.appstoreAppType = appstoreAppType;
		this.appstoreCurrencyCode = appstoreCurrencyCode;
		this.appId = appId;
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.source = source;
		this.appstoreUserAppMaps = appstoreUserAppMaps;
		this.appstoreAppPlatformMaps = appstoreAppPlatformMaps;
		this.appstoreAppCategoryMaps = appstoreAppCategoryMaps;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "typeId", nullable = false)
	@NotNull
	public AppstoreAppType getAppstoreAppType() {
		return this.appstoreAppType;
	}

	public void setAppstoreAppType(AppstoreAppType appstoreAppType) {
		this.appstoreAppType = appstoreAppType;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "currencyId", nullable = false)
	@NotNull
	public AppstoreCurrencyCode getAppstoreCurrencyCode() {
		return this.appstoreCurrencyCode;
	}

	public void setAppstoreCurrencyCode(
			AppstoreCurrencyCode appstoreCurrencyCode) {
		this.appstoreCurrencyCode = appstoreCurrencyCode;
	}

	@Column(name = "appId", nullable = false)
	@NotNull
	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "description", length = 65535)
	@Length(max = 65535)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "cost", precision = 12, scale = 0)
	public Float getCost() {
		return this.cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	@Column(name = "size")
	public long getSize() {
		return this.size;
	}
	public void setSize( long size ) {
		this.size = size;
	}
	
	
	@NotNull
	@Column(name = "ext", length = 7)
	public String getExtension() {
		return this.extension;
	}

	public void setExtension( String extension ) {
		this.extension = extension;
	}

	@Lob
	@NotNull
	@Column(name = "source", length = 2147483647)
	@Basic(fetch=FetchType.LAZY)
	public byte[] getSource() {
		return this.source;
	}

	public void setSource(byte[] source) {
		this.source = source;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstoreApp")
	public Set<AppstoreUserAppMap> getAppstoreUserAppMaps() {
		return this.appstoreUserAppMaps;
	}

	public void setAppstoreUserAppMaps(
			Set<AppstoreUserAppMap> appstoreUserAppMaps) {
		this.appstoreUserAppMaps = appstoreUserAppMaps;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstoreApp")
	public Set<AppstoreAppPlatformMap> getAppstoreAppPlatformMaps() {
		return this.appstoreAppPlatformMaps;
	}

	public void setAppstoreAppPlatformMaps(
			Set<AppstoreAppPlatformMap> appstoreAppPlatformMaps) {
		this.appstoreAppPlatformMaps = appstoreAppPlatformMaps;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstoreApp")
	public Set<AppstoreAppCategoryMap> getAppstoreAppCategoryMaps() {
		return this.appstoreAppCategoryMaps;
	}

	public void setAppstoreAppCategoryMaps(
			Set<AppstoreAppCategoryMap> appstoreAppCategoryMaps) {
		this.appstoreAppCategoryMaps = appstoreAppCategoryMaps;
	}
}