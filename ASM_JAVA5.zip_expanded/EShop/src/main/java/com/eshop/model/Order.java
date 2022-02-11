// Generated with g9.

package com.eshop.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="OrderId", unique=true, nullable=false, precision=19)
    private long orderId;
    @Column(name="CreatedDate", nullable=false)
    private LocalDateTime createdDate;
    @Column(name="ShipDate", nullable=false)
    private LocalDateTime shipDate;
    @ManyToOne(optional=false)
    @JoinColumn(name="AccountId", nullable=false)
    private Account account;
    @OneToMany(mappedBy="order")
    private Set<OrderDetail> orderDetail;

}
