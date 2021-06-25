package com.rca.spring.exam.soapws.domains;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name="suppliers")
public class SupplierModel {


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



    public SupplierModel() {}

    public SupplierModel(String names, String email, String mobile) {
        this.names = names;
        this.email = email;
        this.mobile = mobile;
    }

    public SupplierModel(Long id, String names, String email, String mobile) {
        this.id = id;
        this.names = names;
        this.email = email;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }



    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", names='" + names + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}