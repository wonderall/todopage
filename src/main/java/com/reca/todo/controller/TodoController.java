package com.reca.todo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.reca.todo.dto.ResponseDTO;
import com.reca.todo.dto.TodoDTO;
import com.reca.todo.model.TodoEntity;
import com.reca.todo.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {
	
	@Autowired
	private TodoService service;
	
	@PostMapping
	public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
		try {
			String temporaryUserId = "teporary-user";
			
			// TodoEntity로 변환
			TodoEntity entity = TodoDTO.toEntity(dto);
			//id를 null로 초기화 생성 시 id 값이 없음
			entity.setId(null);
			
			// 임지 유저 아이디 등록
			entity.setUserId(temporaryUserId);
			
			// 서비스 이용 Todo 엔터티 생성
			List<TodoEntity> entities = service.create(entity);
			
			//TodoDTO 리스트로 변환
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			//TodoDTO 를 리턴
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response =  ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
			
		}
	}
	
	@GetMapping
	public ResponseEntity<?> retriveTodoList() {
		String temporaryUserId = "teporary-user";
		List<TodoEntity> entities = service.retrive(temporaryUserId);
		
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		return ResponseEntity.ok().body(response);
	}
	
	@PutMapping
	public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto){
		String temporaryUserId = "teporary-user";
		
		//(1)dto를 entity로 변환한다.
		TodoEntity entity = TodoDTO.toEntity(dto);
		
		//(2) id를 temporaryUserId로 초기화
		entity.setUserId(temporaryUserId);
		
		//(3) 서비스를 이용해 entity를 업데이트
		List<TodoEntity> entities = service.update(entity);
		
		//(4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TOdoDTO 리스트로 변환
		List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
		
		//(5) 변환된 TOdoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
		
		//(6) ResponseDTO를 리턴
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto){
		try {
			String temporaryUserId = "temporary-user";
			
			TodoEntity entity = TodoDTO.toEntity(dto);
			
			entity.setUserId(temporaryUserId);
			
			List<TodoEntity> entities = service.delete(entity);
			
			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();
			
			return ResponseEntity.ok().body(response);
		}catch(Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response =ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping("/test")
	public ResponseEntity<?> testTodo(){
		String str = service.testService();
		List<String> list = new ArrayList<>();
		list.add(str);
		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
		return ResponseEntity.ok().body(response);
	}
}
