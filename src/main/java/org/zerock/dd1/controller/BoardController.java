package org.zerock.dd1.controller;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dd1.dto.BoardListRcntDTO;
import org.zerock.dd1.dto.PageRequestDTO;
import org.zerock.dd1.dto.PageResponceDTO;
import org.zerock.dd1.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
public class BoardController {
    
    private final BoardService boardService;


    @GetMapping(value = "/list")
    public PageResponceDTO<BoardListRcntDTO> list (
        @ParameterObject PageRequestDTO pageRequestDTO){

        log.info(pageRequestDTO);

        return boardService.listRcnt(pageRequestDTO);
        
    }

}
