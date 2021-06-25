package com.rca.spring.exam.soapws.repositories;

import com.rca.spring.exam.soapws.domains.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
}
