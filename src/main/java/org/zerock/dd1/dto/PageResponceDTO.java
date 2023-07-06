package org.zerock.dd1.dto;

import java.util.List;

import lombok.Data;

@Data
public class PageResponceDTO<E> {

    private List<E> dtoList;
    
    private Long totalCount;

    private List<Integer> pageNums;

    private boolean prev, next;

    private PageRequestDTO requestDTO;


    //생성자
    public PageResponceDTO(List<E> dtoList, long totalCount, PageRequestDTO pageRequestDTO) {

        this.dtoList = dtoList; 
        this.totalCount = totalCount; 
        this.requestDTO = pageRequestDTO;
    }


}
