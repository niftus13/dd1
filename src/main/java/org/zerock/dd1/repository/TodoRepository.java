package org.zerock.dd1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.dd1.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{
    
}
