package org.zerock.dd1.controller;

import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dd1.dto.PageResponceDTO;
import org.zerock.dd1.dto.TodoDTO;
import org.zerock.dd1.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/list")
    public PageResponceDTO<TodoDTO> list(){

        return todoService.getList();
    }
    


}
