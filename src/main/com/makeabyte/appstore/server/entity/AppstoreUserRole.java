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
@Table(name = "appstore_user_role")
public class AppstoreUserRole implements java.io.Serializable {

	private static final long serialVersionUID = 4346939063817180742L;
	private Long id;
	private String name;
	private String description;
	private Set<AppstoreUserRoleMap> appstoreUserRoleMaps = new HashSet<AppstoreUserRoleMap>(0);

	public AppstoreUserRole() {
	}

	public AppstoreUserRole(String name) {
		this.name = name;
	}
	public AppstoreUserRole(String name, String description,
			Set<AppstoreUserRoleMap> appstoreUserRoleMaps) {
		this.name = name;
		this.description = description;
		this.appstoreUserRoleMaps = appstoreUserRoleMaps;
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

	@Column(name = "name", nullable = false)
	@NotNull
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
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appstoreUserRole")
	public Set<AppstoreUserRoleMap> getAppstoreUserRoleMaps() {
		return this.appstoreUserRoleMaps;
	}

	public void setAppstoreUserRoleMaps(
			Set<AppstoreUserRoleMap> appstoreUserRoleMaps) {
		this.appstoreUserRoleMaps = appstoreUserRoleMaps;
	}

}
