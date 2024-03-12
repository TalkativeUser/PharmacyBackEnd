package com.ecommerce.pharmacy.Controller;

import com.ecommerce.pharmacy.DTO.ReviewDTO;
import com.ecommerce.pharmacy.Entity.Review;
import com.ecommerce.pharmacy.service.ReviewService;
import com.ecommerce.pharmacy.utils.ExtractJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@CrossOrigin("http://localhost:4200")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/secure/add")
    public void postReview(@RequestHeader(value = "Authorization") String token,
                           @RequestBody ReviewDTO reviewDTO) throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        reviewService.postReview(user, reviewDTO);
    }

    @PutMapping("/secure/update")
    public Review putReview (@RequestHeader(value = "Authorization") String token,
                             @RequestBody ReviewDTO reviewDTO,
                             @RequestParam("review_id") Long id) throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        return reviewService.putReview(user,id,reviewDTO);
    }

    @DeleteMapping("/secure/delete")
    public void deleteReview(@RequestHeader(value = "Authorization") String token,
                             @RequestParam("review_id") Long id) throws Exception{
        String user = ExtractJWT.payloadJWTExtraction(token,"\"sub\"");
        if (user == null) {
            throw new Exception("User email is missing");
        }
        reviewService.deleteReview(id);
    }
}
