package com.zerock.sendbox.service.store;

import com.zerock.sendbox.entity.Store;
import com.zerock.sendbox.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {
    @Autowired
    StoreRepository storeRepository;

    // 검색어가 주어졌을 때 검색 결과 조회
    public List<Store> findAllByKeyword(String storeName) {

        return storeRepository.findAllByStoreNameContaining(storeName);
    }

    // 검색어가 없을 때 전체 매장 리스트 조회
    public List<Store> getAllStores(Pageable pageable) {

        return storeRepository.findAllStores(pageable);
    }


    //퀵메뉴
    public List<Store> getStoresByRegion(String region) {
        return storeRepository.findByRegion(region);
    }

}
