package com.rca.spring.exam.soapws.repositories;

import com.rca.spring.exam.soapws.domains.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<Item, Long> {
}
