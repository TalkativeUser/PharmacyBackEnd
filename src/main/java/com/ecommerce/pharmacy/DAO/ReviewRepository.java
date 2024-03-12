package com.ecommerce.pharmacy.DAO;

import com.ecommerce.pharmacy.Entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;


@RepositoryRestResource
public interface ReviewRepository extends JpaRepository<Review,Long> {

    Page<Review> findReviewByProductId(@RequestParam("product_id") Long productId , Pageable pageable);
    Review findByUserAndProductId(String user,Long productId);
}
