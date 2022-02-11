// Generated with g9.

package com.eshop.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="OrderDetailId", unique=true, nullable=false, precision=19)
    private long orderDetailId;
    @Column(name="UnitPrice", nullable=false, precision=53)
    private double unitPrice;
    @Column(name="Quantity", nullable=false, precision=10)
    private int quantity;
    @ManyToOne(optional=false)
    @JoinColumn(name="OrderId", nullable=false)
    private Order order;
    @ManyToOne(optional=false)
    @JoinColumn(name="ProductId", nullable=false)
    private Product product;
}
