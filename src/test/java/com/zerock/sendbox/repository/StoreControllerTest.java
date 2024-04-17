package com.zerock.sendbox.repository;


import com.zerock.sendbox.controller.store.StoreController;
import com.zerock.sendbox.entity.Store;
import com.zerock.sendbox.service.store.StoreService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(StoreController.class)
public class StoreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService storeService;

    @InjectMocks
    private StoreController storeController;

    @Test
    public void testSearchList() throws Exception {
        // 테스트용 가게 리스트 생성
        List<Store> stores = new ArrayList<>();
        stores.add(new Store(101, 1, "test 1", "notice", "address", "phone", "filter", "thumbnail", "infoPhoto"));
        stores.add(new Store(105, 1, "test 2", "notice", "address", "phone", "filter", "thumbnail", "infoPhoto"));

        // StoreService의 메서드를 모의화하여 테스트용 데이터 반환 설정
        when(storeService.findAllByKeyword("test")).thenReturn(stores);

        // GET /store/searchList 요청 시뮬레이션 및 응답 검증
        mockMvc.perform(MockMvcRequestBuilders.get("/store/searchList")
                        .param("storeName", "test"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("admin/store/search_store"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("storeList"))
                .andExpect(MockMvcResultMatchers.model().attribute("storeList", stores)); // 반환된 리스트와 모델의 storeList가 일치하는지 검증
    }
}