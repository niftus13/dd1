package org.zerock.dd1.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.dd1.domain.Board;
import org.zerock.dd1.dto.BoardDTO;
import org.zerock.dd1.dto.BoardListRcntDTO;
import org.zerock.dd1.dto.PageRequestDTO;
import org.zerock.dd1.dto.PageResponceDTO;
import org.zerock.dd1.repository.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final ModelMapper modelMapper;
    

    @Override
    public PageResponceDTO<BoardListRcntDTO> listRcnt(PageRequestDTO pageRequestDTO) {
        
        log.info("------------");
        log.info(pageRequestDTO);

        return boardRepository.searchDTORcnt(pageRequestDTO);
    }


    @Override
    public BoardDTO getOne(Long bno) {
        
        Optional<Board> result = boardRepository.findById(bno);

        Board board = result.orElseThrow();

        BoardDTO dto = modelMapper.map(board, BoardDTO.class);

        return dto;

    }
    
}
