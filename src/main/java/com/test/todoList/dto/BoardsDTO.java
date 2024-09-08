package com.test.todoList.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardsDTO {
    private int boardId; // 기본키
    private String content; // 내용
    private String addExp; // 추가 설명
    private LocalDate deadline; // 완료 기간일
    private LocalDate createDate; // 생성일
    private LocalDate modifyDate; // 수정일
    private Integer userId; // 외래키 유저

    private String name; // 유저이름
}
