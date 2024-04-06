package com.spider.productservice.repositories;

import com.spider.productservice.models.SelfProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<SelfProduct,Long> {
//    no need to add default methods only needed custom methods
//    default methods
    Optional<SelfProduct> findById(Long id);
    @Override
    List<SelfProduct> findAll();

    @Override
    SelfProduct save(SelfProduct entity);

    List<SelfProduct> findByTitleContaining(String phrase);
}
