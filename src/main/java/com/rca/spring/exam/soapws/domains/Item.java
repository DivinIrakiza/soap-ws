package com.rca.spring.exam.soapws.domains;

import com.sun.istack.NotNull;
import javax.persistence.*;

@Entity
@Table(name="items")
public class Item {
    private static final Long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotNull
    private String names;

    @Column(name="price")
    @NotNull
    private Double email;


    @JoinColumn(name="institution_id", nullable=false)
    @NotNull
    @ManyToOne
    private Institution institution;



}