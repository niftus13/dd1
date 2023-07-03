package org.zerock.dd1.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.dd1.domain.Todo;
import org.zerock.dd1.dto.PageResponceDTO;
import org.zerock.dd1.dto.TodoDTO;
import org.zerock.dd1.repository.TodoRepository;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor
public class TodoServiceImple implements TodoService{

    private final TodoRepository todorepository;
    private final ModelMapper modelMapper;
    
    @Override
    public PageResponceDTO<TodoDTO> getList() {
        Pageable pageable =
        PageRequest.of(0, 20,Sort.by("tno").descending() );

        Page<Todo> result = todorepository.findAll(pageable);

        List<TodoDTO> dtoList = result.getContent().stream()
        .map(todo -> modelMapper.map(todo, TodoDTO.class))
        .collect(Collectors.toList());

        PageResponceDTO<TodoDTO> response = new PageResponceDTO<>();

        response.setDtoList(dtoList);
        
        return response;


    }

    @Override
    public TodoDTO register(TodoDTO dto) {
        // entity 타입으로 바꿈
        Todo entity = modelMapper.map(dto, Todo.class);

        Todo result = todorepository.save(entity);

        return modelMapper.map(result,TodoDTO.class);
    }
}
