package com.reca.todo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.reca.todo.model.TodoEntity;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String>{

	@Query(value="select * from todo where user_id = ?1", nativeQuery = true)
	List<TodoEntity> findByUserId(String userId);
}
