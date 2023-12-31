package org.zerock.dd1.controller;


import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    //조회
    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable Long tno){


        return todoService.getOne(tno);
    }

    //삭제
    @DeleteMapping("/{tno}")
    public Map<String, String> delete(@PathVariable Long tno){
        
        todoService.remove(tno);
        return Map.of("result","success");
    }

    // 수정
    @PutMapping("/{tno}")
    public Map<String, String> update(
    @PathVariable("tno") Long tno,    
    @RequestBody TodoDTO todoDTO){

        todoDTO.setTno(tno); // 안전장치
        todoService.modify(todoDTO);


        return Map.of("result","success");
    }
}
