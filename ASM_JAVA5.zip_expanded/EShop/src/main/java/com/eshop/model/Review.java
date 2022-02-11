// Generated with g9.

package com.eshop.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="ReviewId", unique=true, nullable=false, precision=10)
    private int reviewId;
    @Column(name="ReviewContent", nullable=false)
    private String reviewContent;
    @Column(name="CreatedDate", nullable=false)
    private LocalDateTime createdDate;
    @ManyToOne(optional=false)
    @JoinColumn(name="AccountId", nullable=false)
    private Account account;
    @ManyToOne(optional=false)
    @JoinColumn(name="ProductId", nullable=false)
    private Product product;

}
