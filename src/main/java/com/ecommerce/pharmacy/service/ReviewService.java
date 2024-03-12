package com.ecommerce.pharmacy.service;

import com.ecommerce.pharmacy.DTO.ReviewDTO;
import com.ecommerce.pharmacy.Entity.Review;

public interface ReviewService {
    public void postReview(String user , ReviewDTO reviewDTO)throws Exception;

    public Review putReview(String user,Long id, ReviewDTO reviewDTO) throws Exception;

    public void deleteReview(Long id) throws Exception;
}
