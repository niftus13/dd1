package org.zerock.dd1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.dd1.domain.Board;
import org.zerock.dd1.domain.Reply;
import org.zerock.dd1.dto.ReplyPageRequestDTO;
import org.zerock.dd1.service.ReplyService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {
    
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private ReplyService replyService;

    @Test
    public void insertOne(){

        Long bno = 100L;

        Board board = Board.builder().bno(bno).build();

        Reply reply = Reply.builder()
        .replyText("Reply...")
        .replyer("replyer00")
        .board(board)
        .build();

        replyRepository.save(reply);
    }

    // testInsert 코드는 전부 주석처리해야한다. AWS
    // 테스트코드가 build 도중 실행되기 때문에 
    @Test
    public void InsertReply(){
        Long[] bnoArr = {99L, 96L, 92L, 84L, 82L};

        for (Long bno : bnoArr) {

            Board board = Board.builder().bno(bno).build();

            for(int i = 0; i<50; i++){
                
                Reply reply = Reply.builder()
                    .replyText("Reply..."+bno+i)
                    .replyer("replyer..."+i)
                    .board(board)
                    .build();
                replyRepository.save(reply);
            }

        }//end for
    }

    @Test
    public void testQuery1(){

        Long bno = 99L;
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").ascending());

        Page<Reply> result = replyRepository.listBoard(bno, pageable);

        result.get().forEach( r -> log.info(r));

    }

    @Test
    public void testCountReply(){

        Long bno = 99L;
        
        Long count = replyRepository.getCountBoard(bno);

        log.info(count);

    }
    @Test
    public void testListLast(){

        ReplyPageRequestDTO requestDTO = ReplyPageRequestDTO.builder().bno(99L).last(true).build();
        
        replyService.list(requestDTO);

        
    }

    

}
