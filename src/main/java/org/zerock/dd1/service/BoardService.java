package org.zerock.dd1.service;

import org.springframework.transaction.annotation.Transactional;
import org.zerock.dd1.dto.BoardDTO;
import org.zerock.dd1.dto.BoardListRcntDTO;
import org.zerock.dd1.dto.PageRequestDTO;
import org.zerock.dd1.dto.PageResponceDTO;

@Transactional
public interface BoardService {
    
    PageResponceDTO<BoardListRcntDTO> listRcnt (PageRequestDTO pageRequestDTO);

    BoardDTO getOne(Long bno);
    

    
}
