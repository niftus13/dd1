package org.zerock.dd1.controller;

import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dd1.dto.PageResponceDTO;
import org.zerock.dd1.dto.TodoDTO;
import org.zerock.dd1.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
@Log4j2
// api 문서 확인할때 동일출처정책으로 허락하기 위해
@CrossOrigin
// 어느 도메인이든 허락하는 어노테이션
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/list")
    public PageResponceDTO<TodoDTO> list(){

        return todoService.getList();
    }

    @PostMapping("/")
    public TodoDTO register(@RequestBody TodoDTO todoDTO){

        log.info("register......................");
        log.info(todoDTO);

        return todoService.register(todoDTO);

    }

}
