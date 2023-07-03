package org.zerock.dd1.service;


import org.zerock.dd1.dto.PageResponceDTO;
import org.zerock.dd1.dto.TodoDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TodoService {
    PageResponceDTO<TodoDTO> getList();
    
}
