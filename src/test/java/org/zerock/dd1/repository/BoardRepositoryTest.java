package org.zerock.dd1.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.dd1.domain.Board;
import org.zerock.dd1.dto.BoardListRcntDTO;
import org.zerock.dd1.dto.BoardReadDTO;
import org.zerock.dd1.dto.PageRequestDTO;
import org.zerock.dd1.dto.PageResponceDTO;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;



@SpringBootTest
@Log4j2
public class BoardRepositoryTest {
    
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void testInsert(){

        // 덤프데이터 100개 넣기
        for(int i=0; i<100; i++){
                
        Board board = Board.builder()
        .title("Sample Title"+i)
        .content("Sample Content"+i)
        .writer("user00"+(i%10))
        .build();

        boardRepository.save(board);

        }

    }

    @Test
    public void readTest(){

        Long bno = 1L;

        Optional<Board> result = boardRepository.findById(bno);

        log.info("-------------------------");

        Board board = result.orElseThrow();

        log.info(board);
    }

    @Test
    public void testUpdate(){
        Long bno = 1L;

        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        board.changeTitle("Update Title");

        boardRepository.save(board);

    }

    @Test
    public void testQuery1(){

        java.util.List<Board> list = boardRepository.findByTitleContaining("1");
        
        log.info("..............");
        log.info(list.size());
        log.info(list);


    }

    @Test
    public void testQuery2(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.findByContentContaining("1", pageable);

        log.info("..............");
        log.info(result);
    }

    @Test
    public void testQuery1_1(){

        java.util.List<Board> list = boardRepository.listTitle("1");
        
        log.info("..............");
        log.info(list.size());
        log.info(list);
    }

    @Test
    public void testQuery1_2(){

        java.util.List<Object[]> list = boardRepository.listTitle2("1");
        
        log.info("..............");
        log.info(list.size());

        list.forEach(arr -> log.info(Arrays.toString(arr)));
    }

    @Test
    public void testQuery1_3(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Object[]> result = boardRepository.listTitle2("1", pageable);

        log.info(result);
    }

    @Test
    @Transactional
    @Commit
    public void testModify(){
        Long bno = 100L;
        String title = "modified title";

        int count =  boardRepository.modifyTitle(title, bno);

        log.info(count);

    }

    @Test
    public void testNative(){

        List<Object[]> result = boardRepository.listNative();

        result.forEach(arr -> log.info(Arrays.toString(arr)));

    }

    @Test
    public void testSearch1(){

        Pageable pageable  = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result =  boardRepository.search1("tcw", "1", pageable);

        log.info(result.getTotalElements());
        result.get().forEach(b -> log.info(b));
        
    }

    @Test
    public void testListWithRcnt(){

        List<Object[]> result = boardRepository.getListWithRcnt();

        for (Object[] result2 : result) {
            log.info(Arrays.toString(result2));
            
        }
    }

    @Test
    public void testListWithRcntSearch(){
        Pageable pageable  = PageRequest.of(0, 10, 
            Sort.by("bno").descending());

        boardRepository.searchWithRcnt("tcw", "1", pageable);

    }

    @Test
    public void testSearchDTORcnt(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResponceDTO<BoardListRcntDTO> responceDTO 
        = boardRepository.searchDTORcnt(pageRequestDTO);

        log.info("--------------------responceDTO : ");
        log.info(responceDTO);

    }

    @Test
    public void testReadOne(){

        Long bno = 72L;

        BoardReadDTO dto = boardRepository.readOne(bno);

        log.info(dto.getClass().getName());
        log.info(dto.getModDate());
        log.info(dto.getModDate());
        log.info(dto);
    }




}
