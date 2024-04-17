package com.zerock.sendbox.controller.store;

import com.zerock.sendbox.entity.Orders;
import com.zerock.sendbox.entity.Store;
import com.zerock.sendbox.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    //1차코드
    //검색 결과
    @GetMapping("/searchList")
    public String searchList(@RequestParam(value = ":storeName", required = false) String storeName,
                             @PageableDefault(size = 20) Pageable pageable, Model model) {
        List<Store> storeList;
        // 검색어가 있으면 검색 결과를 보여주고 없으면 전체 리스트를 보여줌
        if (storeName != null && !storeName.isEmpty()) {
            storeList = storeService.findAllByKeyword(storeName);
        }
        else {
            storeList = storeService.getAllStores(pageable);
        }

        // 매장 정보와 방 정보를 모두 모델에 저장
        model.addAttribute("storeList", storeList);

        return "admin/store/search_store";
    }


    //2차 코드
//    @GetMapping("/searchList")
//    public String searchList(@RequestParam (value= "storeName", required = false) String storeName, Model model,
//                             @PageableDefault(size = 20) Pageable pageable) {
//        storeName = "";
//        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // 페이지 번호가 0이면 0, 아니면 -1
//        pageable = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());
//
//        List<Store> searchList = storeService.findAllByKeyword(storeName);
//        int start = (int) pageable.getOffset();
//        int end = Math.min((start + pageable.getPageSize()), searchList.size());
//
//        List<Store> pageContent = searchList.subList(start, end);
//        Page<Store> store = new PageImpl<>(pageContent, pageable, searchList.size());
//
//
//        model.addAttribute("reservations", store);
//
//        return "admin/store/search_store";
//    }
    //3차 코드
//    @GetMapping("/searchList")
//    public String searchList(@RequestParam (value= "storeNo", required = false) Integer storeNo, Model model,
//                             @PageableDefault(size = 20) Pageable pageable) {
//        storeNo = 1;
//        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // 페이지 번호가 0이면 0, 아니면 -1
//        pageable = PageRequest.of(page, pageable.getPageSize(), pageable.getSort());
//        System.out.println("pageable = " + pageable);
//        System.out.println("storeNo = " + storeNo);
//        List<Store> searchList = storeService.findAllByKeyword(storeNo, pageable);
//        int start = (int) pageable.getOffset();
//        int end = Math.min((start + pageable.getPageSize()), searchList.size());
//
//        List<Store> pageContent = searchList.subList(start, end);
//        Page<Store> store = new PageImpl<>(pageContent, pageable, searchList.size());
//        System.out.println("size = " + store.getSize());
//        System.out.println("elements = " + store.getTotalElements());
//        System.out.println("totalpages = " + store.getTotalPages());
//        System.out.println("number = " + store.getNumber());
//        System.out.println("element = " + store.getNumberOfElements());
//        System.out.println("pageable = " + store.getPageable());
//
//        model.addAttribute("reservations", store);
//
//        return "admin/store/search_store";
//    }

}
