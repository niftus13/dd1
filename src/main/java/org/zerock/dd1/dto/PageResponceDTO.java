package org.zerock.dd1.dto;

import java.util.List;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class PageResponceDTO<E> {

    private List<E> dtoList;
    
    private Long totalCount;

    private List<Integer> pageNums;

    private boolean prev, next;

    private PageRequestDTO requestDTO;

    private int page, size,start,end;



    //생성자
    public PageResponceDTO(List<E> dtoList, long totalCount, PageRequestDTO pageRequestDTO) {

        this.dtoList = dtoList; 
        this.totalCount = totalCount; 
        this.requestDTO = pageRequestDTO;

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        // 13 -> 1.3 -> 2
        int tempEnd = (int)(Math.ceil(page/10.0) *10); 

        this.start = tempEnd - 9;
        this.prev = this.start != 1;

        //20 170 
        int realEnd = (int)Math.ceil(totalCount/(double)size);

        this.end = tempEnd > realEnd ? realEnd : tempEnd;

        this.next = (this.end * this.size) < totalCount ;

        this.pageNums = IntStream.rangeClosed(start, end).boxed().toList();

    }


}
