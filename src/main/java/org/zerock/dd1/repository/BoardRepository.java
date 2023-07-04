package org.zerock.dd1.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.dd1.domain.Board;

public interface BoardRepository extends JpaRepository<Board,Long>{
    


    List<Board> findByTitleContaining(String title);

    Page<Board> findByContentContaining(String content, Pageable pageable);
}
