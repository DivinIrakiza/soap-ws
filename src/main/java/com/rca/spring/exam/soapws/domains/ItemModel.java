package com.rca.spring.exam.soapws.domains;

import com.rca.spring.exam.soapws.enums.EItemStatus;
import com.sun.istack.NotNull;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="items")
public class ItemModel {
    private static final Long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    @NotNull
    private String name;

    @Column(name="price")
    @NotNull
    private String itemCode;

    @Column(name="price")
    @NotNull
    private BigDecimal price;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private EItemStatus status;

    @JoinColumn(name="supplier_id", nullable=false)
    @NotNull
    @ManyToOne
    private SupplierModel supplier;

    public ItemModel() { }


    public ItemModel(Long id, String name, String itemCode, BigDecimal price, EItemStatus status, SupplierModel supplier) {
        this.id = id;
        this.name = name;
        this.itemCode = itemCode;
        this.price = price;
        this.status = status;
        this.supplier = supplier;
    }

    public ItemModel(String name, String itemCode, BigDecimal price, EItemStatus status, SupplierModel supplier) {
        this.name = name;
        this.itemCode = itemCode;
        this.price = price;
        this.status = status;
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public EItemStatus getStatus() {
        return status;
    }

    public void setStatus(EItemStatus status) {
        this.status = status;
    }

    public SupplierModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierModel supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", supplier=" + supplier +
                '}';
    }
}