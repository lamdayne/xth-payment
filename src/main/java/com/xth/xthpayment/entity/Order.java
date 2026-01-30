package com.xth.xthpayment.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String orderCode;
    private String phone;
    private String email;
    private String fullName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String status;
    private Date orderDate;
}
