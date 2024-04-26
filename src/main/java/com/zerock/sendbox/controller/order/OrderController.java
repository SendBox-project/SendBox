package com.zerock.sendbox.controller.order;

import com.zerock.sendbox.service.order.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

public class OrderController {

    @Autowired
    OrderService orderService;

    //장바구니 담기
    @PostMapping("/submitOrder")
    public String submitOrder(@RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate,
                              @RequestParam("totalAmount") String totalAmount,
                              @RequestParam("storeNo") int storeNo,
                              RedirectAttributes redirectAttributes) {
        // 서비스를 호출하여 총 가격을 계산하고 반환합니다.
        int totalPrice = orderService.calculateTotalPrice(startDate, endDate, totalAmount, storeNo);

        // 총 가격을 리다이렉트할 때 함께 전달합니다.
        redirectAttributes.addFlashAttribute("totalPrice", totalPrice);

        // 주문 정보 등을 처리한 후 성공 페이지로 리다이렉션합니다.
        return "redirect:/store/detail/{storeNo}";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("startDate") String startDate,
                            @RequestParam("endDate") String endDate,
                            @RequestParam("totalAmount") String totalAmount,
                            @RequestParam("storeNo") int storeNo,
                            RedirectAttributes redirectAttributes) {
        // 서비스를 호출하여 장바구니에 추가하고 처리합니다.
        orderService.addToCart(startDate, endDate, totalAmount, storeNo);

        // 장바구니 페이지로 리다이렉션합니다.
        return "redirect:/store/detail/{storeNo}";
    }
}
