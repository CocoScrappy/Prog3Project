package com.data.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;

	@NotEmpty
	@Size(min = 5, max = 10)
	@Column(unique = true)
	private String username;

	@NotEmpty
	@Size(min = 6, max = 20)
	private String name;

	@Email
	@NotEmpty
	private String email;

	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

//	@Size(min = 8, max = 16)
	private String password;

	@Transient
	@NotEmpty
	private String confirmPassword;

	@OneToMany
	private Set<Product> products;
	
	
//	@ManyToOne
//    @JoinColumn(name="user_id", nullable=false)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable( 
        name = "users_roles", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")) 
	private Collection<Role> roles = new HashSet<>();

	public User() {

	}

	public User(long id, @NotEmpty @Size(min = 5, max = 10) String username,
			@NotEmpty @Size(min = 6, max = 20) String name, @Email @NotEmpty String email, @Past Date dateOfBirth,
			String password, @NotEmpty String confirmPassword, Set<Product> products) {
		super();
		Id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.products = products;
	}

	public long getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public Set<Product> getProducts() {
		return products;
	}

	
	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [Id=" + Id + ", username=" + username + ", name=" + name + ", email=" + email + ", dateOfBirth="
				+ dateOfBirth + ", password=" + password + ", confirmPassword=" + confirmPassword + ", products="
				+ products + "]";
	}

}
