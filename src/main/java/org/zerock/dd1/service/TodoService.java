package org.zerock.dd1.service;


import org.zerock.dd1.dto.PageResponceDTO;
import org.zerock.dd1.dto.TodoDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TodoService {
    PageResponceDTO<TodoDTO> getList();

    TodoDTO register(TodoDTO dto);

    //조회기능
    TodoDTO getOne(Long tno);

    //삭제기능
    void remove(Long tno);
    
}
