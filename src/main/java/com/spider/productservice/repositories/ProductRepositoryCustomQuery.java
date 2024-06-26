package com.spider.productservice.repositories;

import com.spider.productservice.dtos.ProductCategoryDto;
import com.spider.productservice.models.SelfProduct;
import com.spider.productservice.repositories.projections.ProductCategoryProjectionImpl;
import com.spider.productservice.repositories.projections.ProductWithIdAndTitle;
import com.spider.productservice.repositories.projections.ProductWithProductNameAndCategoryName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryCustomQuery extends JpaRepository<SelfProduct, Long> {
    //native query
    // products is the name of the table in Mysql database
    // check testQueries in test file
    @Query(value = "select * from products", nativeQuery = true)
    List<SelfProduct> findAllTheProducts();
    //    for HQL queries you can use the entity class name directly if there is no specified name is not defined for that class.
    //    if name is defined then you have to use the name of the entity instead of class name
    //    using HqL query
    //    here products = name of the selfProduct entity
    //    category = category inside the Self product class
    //    no need on condition it follows the same rule defined
    //    passing value to the constructor
    //    check testQueries in test file
    @Query(value = "SELECT new com.spider.productservice.dtos.ProductCategoryDto(p.title,c.title) " +
            "FROM products p JOIN p.category c")
    List<ProductCategoryDto> findProductCategory();

    // some random query
    //    you can refer then class name directly if there is no specific entity name defined in class
    //    check testQueries in test file
    @Query("select p from products p where p.category.id=1")
    List<SelfProduct> someRandomeQuery();

    // this method will return product with id and title
    //    check testQueries in test file
    @Query("select p.id as id, p.title as title from products p")
    List<ProductWithIdAndTitle> getProductWithIdAndTitle();

    // getting product and category name using projection
    //    check testQueries in test file
    @Query(value = "SELECT p.title as productName , c.title as categoryName " +
            "FROM products p JOIN p.category c")
    List<ProductWithProductNameAndCategoryName> getProductCategory1();

    //     getting product category using projection implementation
    //    check testQueries in test file
    @Query(value = "SELECT new com.spider.productservice.repositories.projections.ProductCategoryProjectionImpl(p.title , c.title) " +
            "FROM products p JOIN p.category c")
    List<ProductCategoryProjectionImpl> getProductCategory2();

    //    parameterised HQL query
    @Query(value = "select * from products where category_id =:category_id", nativeQuery = true)
    List<SelfProduct> findByCategory_Id(@Param("category_id") Long id);

}
