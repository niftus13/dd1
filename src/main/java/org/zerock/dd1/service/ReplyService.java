package org.zerock.dd1.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.dd1.dto.PageResponceDTO;
import org.zerock.dd1.dto.ReplyDTO;
import org.zerock.dd1.dto.ReplyPageRequestDTO;

@Transactional
public interface ReplyService {

    PageResponceDTO<ReplyDTO> list(ReplyPageRequestDTO requestDTO);

    Long register(ReplyDTO replyDTO);

    ReplyDTO read(Long rno);

    void remove(Long rno);

    void modify(ReplyDTO replyDTO);
    
}
