package com.zerock.sendbox.repository;

import com.zerock.sendbox.entity.AdminMember;
import com.zerock.sendbox.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private AdminMemberRepository adminMemberRepository;

    @Test
    public void insertBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            // AdminMember 생성 및 저장
            AdminMember adminMember = AdminMember.builder()
                    .adminId("admin" + i) // admin_id 값을 설정
                    .approval(i)
                    .gender("0")
                    .part("abc")
                    .phone("11111111")
                    .adminNo(i)
                    .name("user" + i)
                    .password("1234")
                    .mail("user" + i + "@aaa.com")
                    .build();
            adminMemberRepository.save(adminMember);

            // 저장된 AdminMember의 adminNo를 사용하여 Board 생성
            Board board = Board.builder()
                    .adminNo(adminMember.getAdminNo()) // AdminMember의 adminNo 설정
                    .thumbnail("a")
                    .boardType("1")
                    .cnt(i)
                    .title("Title..." + i)
                    .content("Content...." + i)
                    .build();
            boardRepository.save(board);
        });
    }
}
