package org.zerock.dd1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// 목록 데이터 뽑기
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardListRcntDTO {
    
    private Long bno;
    private String title;
    private String writer;
    private Long replyCount;

}
