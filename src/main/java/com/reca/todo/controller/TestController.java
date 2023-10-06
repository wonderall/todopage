//package com.reca.todo.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.catalina.connector.Response;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.reca.todo.dto.ResponseDTO;
//import com.reca.todo.dto.TestRequestBodyDTO;
//
//@RestController
//@RequestMapping("test")
//public class TestController {
//	
//	@GetMapping
//	public String testController() {
//		return "Hello TODO!";
//	}
//	
//	
//	@GetMapping("/testGetMapping")
//	public String testGetMapping() {
//		return "testGetMapping!";
//	}
//	
//	
//	@GetMapping("/{id}")
//	public String PathVariablesTest(@PathVariable(required = false)int id) {
//		return "PathVariablesTest! "+id;
//	}
//
//	
//	@GetMapping("/testParam")
//	public String testParam(@RequestParam(required = false)int id) {
//		return "testParam! "+id;
//	}
//	
//	@GetMapping("/testRequestBody")
//	public ResponseDTO<String> testRequestBody() {
//		List<String> list = new ArrayList<>();
//		list.add("Hello World! I'm ResponseDTO");
//		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
//		return response;		
//	}
//	
//	
//	@GetMapping("/testResponseEntity")
//	public ResponseEntity<?> testResponseEntity() {
//		List<String> list = new ArrayList<>();
//		list.add("Hello World! I'm ResponseEntity And you got 400!");
//		ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
//		return ResponseEntity.badRequest().body(response);		
//	}
//	
//	
//	
//	
//}
