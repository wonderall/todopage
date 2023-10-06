//package com.reca.todo.controller;
//
//import java.net.URI;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.reca.todo.dto.ResponseDTO;
//import com.reca.todo.dto.UserDTO;
//import com.reca.todo.model.UserEntity;
//import com.reca.todo.security.TokenProvider;
//import com.reca.todo.service.UserService;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@RestController
//@RequestMapping("/auth")
//public class UserController {
//	
//	@Autowired
//	private UserService userService;
//	
//	@Autowired
//	private TokenProvider tokenProvider;
//	
//	@GetMapping
//	public ModelAndView loginPage(ModelAndView mv) {
//		mv.setViewName("login.html");
//		return mv;
//	}
//	
//	@PostMapping("/signup")
//	public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO){
//		try {
//			if(userDTO==null || userDTO.getPassword()==null) {
//				throw new RuntimeException("invalid Password value");
//			}
//			
//			UserEntity user = UserEntity.builder()
//					.username(userDTO.getUsername())
//					.password(userDTO.getPassword())
//					.build();
//			UserEntity registeredUser = userService.create(user);
//			UserDTO responseUserDTO = UserDTO.builder()
//					.id(registeredUser.getId())
//					.username(registeredUser.getUsername())
//					.build();
//			
//			return ResponseEntity.ok().body(responseUserDTO);
//			
//		}catch(Exception e) {
//			ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
//			return ResponseEntity.badRequest().body(responseDTO);
//		}
//	}
//	
//	@PostMapping("/signin")
//	public ResponseEntity<?> authenticate(HttpServletRequest request,HttpServletResponse response){
//		System.out.println("안오니?");
//		UserEntity user = userService.getByCredentials(request.getParameter("username"), request.getParameter("password"));
//		System.out.println(user);
//		final String token = tokenProvider.create(user);
//		System.out.println(token);
//		response.setHeader("Authorization",token);
//		if(user != null) {
//			
//			final UserDTO responseUserDTO = UserDTO.builder()
//					.username(user.getUsername())
//					.id(user.getId())
//					.token(token)
//					.build();
//			HttpHeaders headers = new HttpHeaders();
//	        headers.setLocation(URI.create("/todo"));
//			System.out.println("if문");
//
//	        return ResponseEntity.ok().body(responseUserDTO);;
//		}else {
//		
//			return ResponseEntity.badRequest().body(responseDTO);
//		}
//	}
//
//}
