package com.zerock.sendbox.controller.store;

import com.zerock.sendbox.dto.store.ReviewDTO;
import com.zerock.sendbox.entity.Review;
import com.zerock.sendbox.service.store.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 모든 리뷰 목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        List<ReviewDTO> reviewDTOList = new ArrayList<>(reviewService.getAllReviews());
        return ResponseEntity.ok(reviewDTOList);
    }

    // 특정 리뷰 조회
    @GetMapping("/{reviewNo}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable Integer reviewNo) {
        ReviewDTO reviewDTO = reviewService.entityToDTO(reviewService.getReviewById(reviewNo));
        return ResponseEntity.ok(reviewDTO);
    }

    // 리뷰 등록
    @PostMapping("/register")
    public ResponseEntity<Integer> registerReview(@RequestBody ReviewDTO reviewDTO) {
        Integer savedReviewNo = reviewService.registerReview(reviewDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReviewNo);
    }

    // 리뷰 수정
    @PutMapping("/{reviewNo}")
    public ResponseEntity<Void> updateReview(@PathVariable Integer reviewNo, @RequestBody ReviewDTO reviewDTO) {
        reviewService.updateReview(reviewNo, reviewDTO);
        return ResponseEntity.ok().build();
    }

    // 리뷰 삭제
    @DeleteMapping("/{reviewNo}")
    public ResponseEntity<Void> deleteReview(@PathVariable Integer reviewNo) {
        reviewService.deleteReview(reviewNo);
        return ResponseEntity.ok().build();
    }
}

