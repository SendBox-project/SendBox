package com.zerock.sendbox.service.order;

import com.zerock.sendbox.entity.Orders;
import com.zerock.sendbox.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class OrderService {

    @Autowired
    RoomRepository roomRepository;


    public int calculateTotalPrice(String startDate, String endDate, String totalAmount, int storeNo) {
        // 방 사이즈에 따른 가격 조회
        int roomPrice = roomRepository.findPriceByStoreAndRoomSize(storeNo, roomsprice);

        // 선택한 일수 계산
        int numberOfDays = calculateNumberOfDays(startDate, endDate);

        // 총 가격 계산
        int totalPrice = roomPrice * numberOfDays;

        return totalPrice;
    }

    private int calculateNumberOfDays(String startDate, String endDate) {
        // 시작 날짜와 종료 날짜 사이의 일수를 계산하는 로직
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return (int) ChronoUnit.DAYS.between(start, end);
    }

    public void addToCart(String startDate, String endDate, String totalAmount, int storeNo) {
        Orders orderItem = new Orders();
        orderItem.getStartDate();
        orderItem.getEndDate();
        orderItem.getTotalAmount();
        orderItem.getOrderNo();

        // 주문 정보를 저장합니다.
        OrdersRepository.save(orderItem);
    }
}
