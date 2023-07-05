package org.zerock.dd1.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.dd1.domain.Board;
import org.zerock.dd1.domain.QBoard;
import org.zerock.dd1.domain.QReply;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{

    public BoardSearchImpl(){
        // 도메인 클래스 지정 이래야 컴파일 에러가 안남 
        super(Board.class);
    }

    @Override
    public Page<Board> search1(String keyword, String searchType, Pageable pageable) {
        
        // 지금부터 쿼리를 쓰기위해 쿼리 도메인이 필요함 
        //무조건 컴파일 먼저 해줘야함

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        if(keyword != null && searchType != null){

            // tc -> [t,c]
            String[] searchArr = searchType.split("");

            // (    )
            BooleanBuilder searchBuilder = new BooleanBuilder();

            // case or로 조건추가
            for (String type : searchArr) {
                switch(type){
                    case "t" -> searchBuilder.or(board.title.contains(keyword));
                    case "c" -> searchBuilder.or(board.content.contains(keyword));
                    case "w" -> searchBuilder.or(board.writer.contains(keyword));
                }
            }// end for
            query.where(searchBuilder);
        }

        // query.where(board.title.contains("1"));

        this.getQuerydsl().applyPagination(pageable, query);

        // SQL문을 객체화 시켜놓음 -> 모든 쿼리문이 query에 붙음
        // fetch = 목록 데이터를 가져옴 , 
        List<Board> list = query.fetch();
        Long count = query.fetchCount();

        log.info(list);
        log.info("count : " + count);

        return new PageImpl<>(list, pageable, count);

    }

    @Override
    public Page<Board> searchWithRcnt(String keyword, String searchType, Pageable pageable) {
        
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        // left outer join
        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));
        query.groupBy(board);

        JPQLQuery<Tuple> tupleQuery = 
            query.select(board.bno, board.title, board.writer, reply.countDistinct());
        this.getQuerydsl().applyPagination(pageable, tupleQuery);

        List<Tuple> tuples = tupleQuery.fetch();

        List<Object[]> arrList = 
            tuples.stream().map(tuple -> tuple.toArray()).collect(Collectors.toList());

        
        

        log.info(tuples);
        
        Long count = tupleQuery.fetchCount();

        log.info("count", count);

        
        return null;
    }
    
}
