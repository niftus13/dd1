package org.zerock.dd1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.dd1.domain.Board;
import org.zerock.dd1.domain.Reply;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTest {
    
    @Autowired
    private ReplyRepository replyRepository;

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

}
