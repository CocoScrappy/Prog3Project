package com.data.model;

import java.util.Collection;
import java.sql.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long Id;

	@NotBlank
	@Size(min = 5, max = 15)
	@Column(unique = true)
	private String username;

	@NotBlank
	@Size(min = 5, max = 50)
	private String name;

	@Email
	@NotBlank
	private String email;

	@Past
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dateOfBirth;

	@NotBlank
	private String password;

	@Transient
	private String confirmPassword;

	@OneToMany
	private Set<Product> products;

	@OneToMany(mappedBy="borrower")
	private Set<Loan> loans;

	@ManyToMany(cascade = {PERSIST, MERGE}, fetch = FetchType.EAGER)
    @JoinTable( 
        name = "users_roles", 
        joinColumns = @JoinColumn(
          name = "user_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "role_id", referencedColumnName = "id")) 
	private Collection<Role> roles;

	public User() {

	}

	public User(long id, @NotEmpty @Size(min = 5, max = 10) String username,
			@NotEmpty @Size(min = 6, max = 20) String name, @Email @NotEmpty String email, @Past Date dateOfBirth,
			String password, @NotEmpty String confirmPassword, Set<Product> products, Set<Role> roles) {
		super();
		Id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.products = products;
		this.roles = roles;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		this.Id = id;
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
				+ products + ", roles=" + roles + "]";
	}

}
