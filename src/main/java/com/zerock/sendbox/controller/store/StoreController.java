package com.zerock.sendbox.controller.store;

import com.zerock.sendbox.entity.Store;
import com.zerock.sendbox.service.store.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/store")
@Slf4j
public class StoreController {

    @Autowired
    private StoreService storeService;

    //검색 결과
    @GetMapping("/searchList")
    public String searchList(@RequestParam(value = "storeName", required = false, defaultValue="") String storeName,
                             @PageableDefault(size = 20) Pageable pageable, Model model) {

        List<Store> storeList;

        // 검색어가 있으면 검색 결과를 보여주고 없으면 전체 리스트를 보여줌
        if (storeName != null && !storeName.isEmpty()) {
            storeList = storeService.findAllByKeyword(storeName);
        } else {
            storeList = storeService.getAllStores(pageable);
        }

        // 매장 정보와 방 정보를 모두 모델에 저장
        model.addAttribute("storeList", storeList);

       /* //콘솔창에 스토어 리스트 정보 출력
        log.info("storeList: " + storeList);
        //콘솔창에 입력한 검색어 출력
        log.info("storeName: " + storeName);
        //콘솔창에 입력한 검색어를 검색한 결과 출력
        log.info("storeService.findAllByKeyword(storeName): " + storeService.findAllByKeyword(storeName));*/

        return "store/search_store";
    }

    //상세페이지
    @GetMapping("/detail/{storeName}")
    public String getStoreDetail(@PathVariable("storeName") String storeName, Model model) {
        Store store = storeService.getStoreDetail(storeName);
        model.addAttribute("store", store);
        return "store/store_detail";

    }

//    //퀵메뉴
//    @GetMapping("/{region}")
//    public String getStoresByRegion(@RequestParam("region") String region, Model model) {
//        List<Store> storeList = storeService.getStoresByRegion(region);
//        model.addAttribute("storeList", storeList);
//        return "admin/store/search_store";
//    }

}
