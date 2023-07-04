package org.zerock.dd1.repository.search;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.zerock.dd1.domain.Board;

public interface BoardSearch {

    // 리스트 뽑는 작업
    Page<Board> search1(String keyword, String searchType, Pageable pageable);

   
    
}
