package com.zerock.sendbox.controller.owner;

import com.zerock.sendbox.dto.owner.OwnerSignUpDTO;
import com.zerock.sendbox.entity.Room;
import com.zerock.sendbox.entity.Store;
import com.zerock.sendbox.service.owner.OwnerSignUpService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/owner")
@RequiredArgsConstructor
@Log4j2
public class OwnerSignUpController {

    private final OwnerSignUpService ownerSignUpService;

    @GetMapping("/create_account_form")
    public String createOwnerMember() {
        log.info("sign.......");

        return "/owner/member/create_account_form";
    }

    @PostMapping("/create_account_form")
    public String createOwnerMember(OwnerSignUpDTO signUpDTO, RedirectAttributes redirectAttributes) {
        if(ownerSignUpService.isIdDuplicated(signUpDTO.getOwnerId())) {
            redirectAttributes.addFlashAttribute("message", "이미 사용 중인 아이디입니다.");
            return "/owner/member/create_account_form";
        }

        ownerSignUpService.join(signUpDTO);
        redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다.");
        return "owner/member/login_form";
    }

    //업체 등록 폼 조회
    @GetMapping("/storeRegisterForm")
    public String storeRegisterForm() {
        return "owner/member/storeRegisterForm";
    }

    //업체 등록하기
    /*@PostMapping("/updateStoreInfo")
    public String updateStoreInfo(Model model, HttpServletResponse response,
                                  @RequestParam(value = "storeNo", required = false) Integer storeNo,
                                  @RequestParam(value = "notice", required = false) String notice,
                                  @RequestParam(value = "region", required = false) String region,
                                  @RequestParam(value = "address", required = false) String address,
                                  @RequestParam(value = "phone", required = false) String phone,
                                  @RequestParam(value = "brn", required = false) String brn,
                                  @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail,
                                  @RequestParam(value = "infoPhoto", required = false) MultipartFile infoPhoto,
                                  @RequestParam(value = "roomNo1", required = false) Integer roomNo1,
                                  @RequestParam(value = "roomNo2", required = false) Integer roomNo2,
                                  @RequestParam(value = "roomNo3", required = false) Integer roomNo3,
                                  @RequestParam(value = "size1", required = false) String size1,
                                  @RequestParam(value = "size2", required = false) String size2,
                                  @RequestParam(value = "size3", required = false) String size3,
                                  @RequestParam(value = "price1", required = false) Integer price1,
                                  @RequestParam(value = "price2", required = false) Integer price2,
                                  @RequestParam(value = "price3", required = false) Integer price3,
                                  @RequestParam(value = "remain1", required = false) Integer remain1,
                                  @RequestParam(value = "remain2", required = false) Integer remain2,
                                  @RequestParam(value = "remain3", required = false) Integer remain3) throws IOException {
        Store storeInfo = new Store();
        storeInfo.setNotice(notice);
        storeInfo.setRegion(region);
        storeInfo.setAddress(address);
        storeInfo.setPhone(phone);
        storeInfo.setBrn(brn);

        if (!thumbnail.isEmpty()) { // 빈값이 아니면 신규 사진으로 수정
            // 서비스
            String thumbnailPath = storeInfo.uploadFile(thumbnail);
            storeInfo.setThumbnail(thumbnailPath);
        }

        if (!infoPhoto.isEmpty()) { // 빈값이 아니면 신규 사진으로 수정
            // 서비스
            String infoPhotoPath = storeInfo.uploadFile(infoPhoto);
            storeInfo.setInfoPhoto(infoPhotoPath);
        }

        Store result = ownerMypageService.save(storeInfo);

        List<Room> roomList = new ArrayList<>();

        Room room = new Room();
        room.setRoomNo(roomNo);
        room.setSize(size);
        room.setPrice(price);
        room.setRemain(remain);
        room.setStore(storeInfo);

        roomList.add(room);

        List<Room> roomUpdateInfo = ownerMypageService.saveAll(roomList); // 룸 리스트 정보 저장

        if (result != null && roomUpdateInfo.size() != 0) { // 성공 시 업데이트 정보 보내기
            model.addAttribute("storeInfo", result);
            model.addAttribute("roomList", roomUpdateInfo);
            response.setContentType("text/html; charset=UTF-8"); //응답의 content type을 설정, "text/html"은 전송될 데이터의 종류가 HTML임을 나타냄
            PrintWriter writer = response.getWriter(); //이 PrintWriter를 통해 HTML 코드나 다른 텍스트 데이터를 클라이언트로 전송
            writer.println("<script>alert('수정 완료입니다.');</script>");
            writer.flush();
            return "owner/member/storeForm";
        } else {                                          // 실패시 기존 정보 보내기
            model.addAttribute("storeInfo", storeInfo);
            model.addAttribute("roomList", roomUpdateInfo);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('수정 실패입니다.');</script>");
            writer.flush();
            return "owner/member/storeForm";
        }

    }*/
}