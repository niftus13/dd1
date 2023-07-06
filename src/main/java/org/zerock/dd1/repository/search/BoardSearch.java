package org.zerock.dd1.repository.search;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.dd1.domain.Board;
import org.zerock.dd1.dto.BoardListRcntDTO;
import org.zerock.dd1.dto.PageRequestDTO;
import org.zerock.dd1.dto.PageResponceDTO;

public interface BoardSearch {

    // 리스트 뽑는 작업 ver.1
    Page<Board> search1(String keyword, String searchType, Pageable pageable);

    //ver.2
    Page<Object[]> searchWithRcnt(String keyword, String searchType, Pageable pageable);

    //ver.3
    PageResponceDTO<BoardListRcntDTO> searchDTORcnt(PageRequestDTO requestDTO);

    //Pageable 반환용
    default Pageable makePageable(PageRequestDTO requestDTO){

        Pageable pageable = PageRequest.of(
            requestDTO.getPage() -1,
            requestDTO.getSize(),
            Sort.by("bno").descending() );

        return pageable;
    }

    
    
}
