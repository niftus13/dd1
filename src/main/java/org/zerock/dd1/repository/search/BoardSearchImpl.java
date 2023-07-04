package org.zerock.dd1.repository.search;

import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.dd1.domain.Board;
import org.zerock.dd1.domain.QBoard;

import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

    public BoardSearchImpl(){
        // 도메인 클래스 지정 이래야 컴파일 에러가 안남 
        super(Board.class);
    }

    @Override
    public List<Board> search1() {
        
        // 지금부터 쿼리를 쓰기위해 쿼리 도메인이 필요함 
        //무조건 컴파일 먼저 해줘야함

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        query.where(board.title.contains("1"));

        // SQL문을 객체화 시켜놓음 -> 모든 쿼리문이 query에 붙음
        // fetch = 목록 데이터를 가져옴 , 
        List<Board> list = query.fetch();
        Long count = query.fetchCount();

        log.info(list);
        log.info("count : " + count);

        return null;

    }

    
    
}
