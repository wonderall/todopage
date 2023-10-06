//package com.reca.todo.security;
//
//import java.security.Key;
//import java.time.Instant;
//import java.time.temporal.ChronoUnit;
//import java.util.Date;
//
//import org.springframework.stereotype.Service;
//
//import com.reca.todo.model.UserEntity;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class TokenProvider {
//
//	private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
//	public String create(UserEntity userEntity) {
//		
//		Date expiryDate = Date.from(
//				Instant.now()
//				.plus(1, ChronoUnit.DAYS));
//		return Jwts.builder()
//				.signWith(key)
//				.setSubject(userEntity.getId())
//				.setIssuer("todo app")
//				.setIssuedAt(new Date())
//				.setExpiration(expiryDate)
//				.compact();
//	}
//	
//	public String validateAndGetUserId(String token) {
//		//parseClaimsJws 메서드가 Base 64로 디코딩 및 파싱.
//		// 해더와 페이로드를 setSigningkey로 넘어온 시크릿을 이용해 서명 후, token의 서명과 비교
//		
//		Claims claims = Jwts.parserBuilder()
//				.setSigningKey(key).build()
//				.parseClaimsJws(token)
//				.getBody();
//		
//		return claims.getSubject();
//		
//	}
//	
//	
//}
