package com.zerock.sendbox.service.store;

import com.zerock.sendbox.dto.store.ReviewDTO;
import com.zerock.sendbox.entity.Review;

import java.util.Collection;
import java.util.List;

public interface ReviewService {

    Integer register(ReviewDTO reviewDTO);

    List<ReviewDTO> getListByStoreNo(Integer storeNo);

    void modify(ReviewDTO reviewDTO);

    void remove(Integer reviewNo);

    List<ReviewDTO> getAllReviews(); // 반환 타입 변경

    Review getReviewById(Integer reviewNo);

    Integer registerReview(ReviewDTO reviewDTO);

    void updateReview(Integer reviewNo, ReviewDTO reviewDTO);

    void deleteReview(Integer reviewNo);

    Review dtoToEntity(ReviewDTO reviewDTO);

    ReviewDTO entityToDTO(Review review);
}

