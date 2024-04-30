package com.zerock.sendbox.service.store;

import com.zerock.sendbox.dto.store.ReviewDTO;
import com.zerock.sendbox.entity.Review;
import com.zerock.sendbox.entity.Store;
import com.zerock.sendbox.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Integer register(ReviewDTO reviewDTO) {
        Review review = dtoToEntity(reviewDTO);
        Review savedReview = reviewRepository.save(review);
        return savedReview.getReviewNo();
    }

    @Override
    public List<ReviewDTO> getListByStoreNo(Integer storeNo) {
        List<Review> reviews = reviewRepository.findByStore_StoreNo(storeNo);
        return reviews.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public void modify(ReviewDTO reviewDTO) {
        Review review = dtoToEntity(reviewDTO);
        reviewRepository.save(review);
    }

    @Override
    public void remove(Integer reviewNo) {
        reviewRepository.deleteById(reviewNo);
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(this::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public Review getReviewById(Integer reviewNo) {
        return reviewRepository.findById(reviewNo).orElse(null);
    }

    @Override
    public Integer registerReview(ReviewDTO reviewDTO) {
        return register(reviewDTO);
    }

    @Override
    public void updateReview(Integer reviewNo, ReviewDTO reviewDTO) {
        reviewDTO.setReviewNo(reviewNo);
        modify(reviewDTO);
    }

    @Override
    public void deleteReview(Integer reviewNo) {
        remove(reviewNo);
    }

    @Override
    public Review dtoToEntity(ReviewDTO reviewDTO) {
        return Review.builder()
                .reviewNo(reviewDTO.getReviewNo())
                .store(Store.builder().storeNo(reviewDTO.getStoreNo()).build())
                .userNo(reviewDTO.getUserNo())
                .content(reviewDTO.getContent())
                .build();
    }

    @Override
    public ReviewDTO entityToDTO(Review review) {
        return ReviewDTO.builder()
                .reviewNo(review.getReviewNo())
                .storeNo(review.getStore().getStoreNo())
                .userNo(review.getUserNo())
                .content(review.getContent())
                .regDate(review.getRegDate())
                .build();
    }
}
