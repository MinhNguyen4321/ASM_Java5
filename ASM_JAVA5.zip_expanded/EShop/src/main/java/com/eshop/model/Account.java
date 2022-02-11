// Generated with g9.

package com.eshop.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = { @Index(name = "Account_Username_IX", columnList = "Username", unique = true),
		@Index(name = "Account_Email_IX", columnList = "Email", unique = true) })
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AccountId", unique = true, nullable = false, precision = 10)
	private int accountId;
	@Column(name = "Username", unique = true, nullable = false, length = 50)
	private String username;
	@Column(name = "Password", nullable = false, length = 50)
	private String password;
	@Column(name = "Fullname", nullable = false, length = 100)
	private String fullname;
	@Column(name = "Email", unique = true, nullable = false, length = 100)
	private String email;
	@Column(name = "Photo", nullable = false, length = 255)
	private String photo;
	@Column(name = "CreatedDate", nullable = false)
	private LocalDate createdDate;
	@Column(name = "IsActive", nullable = false, length = 1)
	private boolean isActive;
	@Column(name = "IsAdmin", nullable = false, length = 1)
	private boolean isAdmin;
	@OneToMany(mappedBy = "account")
	private Set<Order> order;
	@OneToMany(mappedBy = "account")
	private Set<Review> review;
	@OneToMany(mappedBy = "account")
	private Set<ShoppingCart> shoppingCart;
}
