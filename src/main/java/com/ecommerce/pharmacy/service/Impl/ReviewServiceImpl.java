package com.ecommerce.pharmacy.service.Impl;

import com.ecommerce.pharmacy.DAO.ReviewRepository;
import com.ecommerce.pharmacy.DTO.ReviewDTO;
import com.ecommerce.pharmacy.Entity.Review;
import com.ecommerce.pharmacy.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.sql.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void postReview(String user , ReviewDTO reviewDTO)throws Exception{
        Review validateReview = reviewRepository.findByUserAndProductId(user, reviewDTO.getProduct().getId());
        if (validateReview != null) {
            throw new Exception("Review Already created");
        }
        Review review = new Review();
        review.setDate(Date.valueOf(LocalDate.now()));
        review.setUser(user);
        review.setRating(reviewDTO.getRating());
        review.setProduct(reviewDTO.getProduct());

        if (reviewDTO.getDescription().isPresent()){
            review.setDescription(reviewDTO.getDescription().map(
                    Objects::toString
            ).orElse(null));
        }
        reviewRepository.save(review);
    }

    @Override
    public Review putReview(String user,Long id, ReviewDTO reviewDTO) throws Exception {
        Optional<Review> review = reviewRepository.findById(id);
        if (!review.isPresent()) {
            throw new Exception("Review not found");
        }
        review.get().setDate(Date.valueOf(LocalDate.now()));
        review.get().setUser(user);
        review.get().setRating(reviewDTO.getRating());
        review.get().setProduct(reviewDTO.getProduct());

        if (reviewDTO.getDescription().isPresent()){
            review.get().setDescription(reviewDTO.getDescription().map(
                    Objects::toString
            ).orElse(null));
        }
        return review.get();
    }

    @Override
    public void deleteReview(Long id) throws Exception {
        Optional<Review> review = reviewRepository.findById(id);
        if (!review.isPresent()){
            throw new Exception("review not found");
        }
        reviewRepository.delete(review.get());
    }


}
