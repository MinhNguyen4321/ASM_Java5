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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ProductId", unique=true, nullable=false, precision=10)
    private int productId;
    @Column(name="ProductName", nullable=false, length=50)
    private String productName;
    @Column(name="ProductImage", nullable=false, length=150)
    private String productImage;
    @Column(name="Description", nullable=false)
    private String description;
    @Column(name="Quantity", nullable=false, precision=10)
    private int quantity;
    @Column(name="UnitPrice", nullable=false, precision=53)
    private double unitPrice;
    @Column(name="CreatedDate", nullable=false)
    private LocalDate createdDate;
    @Column(name="Available", nullable=false, precision=10)
    private int available;
    @OneToMany(mappedBy="product")
    private Set<OrderDetail> orderDetail;
    @ManyToOne(optional=false)
    @JoinColumn(name="CategoryId", nullable=false)
    private Category category;
    @OneToMany(mappedBy="product")
    private Set<Review> review;
    @OneToMany(mappedBy="product")
    private Set<ShoppingCart> shoppingCart;

}
