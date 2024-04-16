package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.Store;
import com.zerock.sendbox.service.store.StoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllByKeyword() {
        String keyword = "test";
        List<Store> stores = new ArrayList<>();
        stores.add(new Store(101,1,"test 1","notice","address","phone","filter","thumbnail","infoPhoto"));
        stores.add(new Store(105, 1, "test 2", "notice", "address", "phone", "filter", "thumbnail", "infoPhoto"));

        when(storeRepository.findAllByStoreName(keyword)).thenReturn(stores);

        // 검색 결과
        List<Store> result = storeService.findAllByKeyword(keyword);

        // 결과
        assertEquals(stores.size(), result.size());
        verify(storeRepository, times(1)).findAllByStoreName(keyword);

        //출력
        for (Store store : result) {
            System.out.println(store);
        }
    }

    @Test
    void testGetAllStores() {
        List<Store> stores = new ArrayList<>();

        when(storeRepository.findAllStores(Pageable.unpaged())).thenReturn(stores);

        // 전체 가게 목록
        List<Store> result = storeService.getAllStores(Pageable.unpaged());

        // 결과
        assertEquals(stores.size(), result.size());
        verify(storeRepository, times(1)).findAllStores(Pageable.unpaged());
        //출력
        for (Store store : result) {
            System.out.println(store);
        }

    }
}