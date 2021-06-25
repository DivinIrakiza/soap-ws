package com.rca.spring.exam.soapws.domains;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="suppliers")
public class Supplier {
    private static final Long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="names")
    @NotNull
    private String names;

    @Column(name="email")
    @NotNull
    private String email;

    @Column(name="mobile")
    @NotNull
    private String mobile;


}