package com.zerock.sendbox.feign;

import com.zerock.sendbox.dto.payment.ApproveReqDto;
import com.zerock.sendbox.dto.payment.ApproveResDto;
import com.zerock.sendbox.dto.payment.PaymentReqDto;
import com.zerock.sendbox.dto.payment.PaymentResDto;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "payment", url = "https://open-api.kakaopay.com")
public interface PaymentClient {

    // 카카오페이 결제 요청 api
    @PostMapping(value = "/online/v1/payment/ready", headers = {"Authorization= SECRET_KEY ${kakao.secret-key}","Content-Type= application/json"}) //변수 받고 바로 카카오 api 실행
    PaymentResDto paymentRequest(@RequestBody PaymentReqDto paymentReqDto); //서비스에서 보낸 매개변수 paymentReqDto를 받기 위해

    //카카오페이 결제 승인 api
    @PostMapping(value = "/online/v1/payment/approve", headers = {"Authorization= SECRET_KEY ${kakao.secret-key}","Content-Type= application/json"})
    ApproveResDto paymentApprove(@RequestBody ApproveReqDto approveReqDto);


}

