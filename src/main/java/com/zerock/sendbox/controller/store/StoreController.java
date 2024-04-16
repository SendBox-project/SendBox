package com.zerock.sendbox.controller.store;

import com.zerock.sendbox.entity.Store;
import com.zerock.sendbox.service.store.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    //검색 결과
    @GetMapping("/searchList")
    public String searchList(@RequestParam(value = "storeName", required = false) String storeName,
                             @PageableDefault Pageable pageable, Model model) {
        List<Store> storeList;
        // 검색어가 있으면 검색 결과를 보여주고 없으면 전체 리스트를 보여줌
        if (storeName != null && !storeName.isEmpty()) {
            storeList = storeService.findAllByKeyword(storeName);
        }
        else {
            storeList = storeService.getAllStores(pageable);
        }
       // 검색 결과를 보여주기 위해 model에 저장
        model.addAttribute("storeList", storeList);

        return "admin/store/search_store";
    }
}
