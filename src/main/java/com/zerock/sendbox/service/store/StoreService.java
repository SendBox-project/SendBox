package com.zerock.sendbox.service.store;

import com.zerock.sendbox.entity.Orders;
import com.zerock.sendbox.entity.Store;
import com.zerock.sendbox.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;

    //1차코드
    // 검색어가 주어졌을 때 검색 결과 조회
    public List<Store> findAllByKeyword(String storeName) {

        return storeRepository.findAllByStoreName(storeName);
    }

    // 검색어가 없을 때 전체 매장 리스트 조회
    public List<Store> getAllStores(Pageable pageable) {

        return storeRepository.findAllStores(pageable);
    }


    //3차 코드
//    public List<Store> findAllByKeyword(@Param("storeNo") Integer storeNo, Pageable pageable) {
//        return storeRepository.findAllByKeyword(storeNo);
//    }
}
