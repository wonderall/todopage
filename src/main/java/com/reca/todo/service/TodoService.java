package com.reca.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reca.todo.model.TodoEntity;
import com.reca.todo.persistence.TodoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoService {

	@Autowired
	private TodoRepository repository;

	public String testService() {
		// TodoEntity 생성
		TodoEntity entity = TodoEntity.builder().userId("test").title("My first todo item").build();
		repository.save(entity);
		// 검색
		TodoEntity saveEntity = repository.findById(entity.getId()).get();

		return saveEntity.getTitle();
	}

	public List<TodoEntity> create(final TodoEntity entity) {

		validate(entity);
		
		repository.save(entity);
		log.info("Entity Id : {} is saved.", entity.getId());
		
		return repository.findByUserId(entity.getUserId());

	}
	
	public List<TodoEntity> retrive(String userid){
		return repository.findByUserId(userid);
	}
	
	public List<TodoEntity> update(final TodoEntity entity){
		
		validate(entity);
		
		Optional<TodoEntity> original = repository.findById(entity.getId());
		
		original.ifPresent(todo->{
			todo.setTitle(entity.getTitle());
			todo.setDone(entity.isDone());
			
			repository.save(todo);
		});
		
		return retrive(entity.getUserId());
	}
	
	public List<TodoEntity> delete(final TodoEntity entity){
		
		validate(entity);
		
		try {
			repository.delete(entity);			
		}catch(Exception e) {
			log.error("error deleting entity", entity.getId(), e);
			throw new RuntimeException("error deleting entity"+ entity.getId());
		}		
		return retrive(entity.getUserId());
		
	}
	
	private void validate(final TodoEntity entity) {
		if (entity == null) {
			log.warn("Entity cannot be null.");
			throw new RuntimeException("Entity cannot be null.");
		}
		if(entity.getUserId()==null) {
			log.warn("Unknown user.");
			throw new RuntimeException(" Unknown user.");
		}
	}
}
