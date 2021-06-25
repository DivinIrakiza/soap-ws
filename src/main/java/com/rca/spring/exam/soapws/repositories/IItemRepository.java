package com.rca.spring.exam.soapws.repositories;

import com.rca.spring.exam.soapws.domains.ItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<ItemModel, Long> {
}
