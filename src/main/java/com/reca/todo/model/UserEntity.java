//package com.reca.todo.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.UniqueConstraint;
//
//import org.hibernate.annotations.GenericGenerator;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Entity
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")}, name="user")
//public class UserEntity {
//	
//	@Id
//	@GeneratedValue(generator="system-uuid")
//	@GenericGenerator(name="system-uuid", strategy="uuid")
//	private String id;// 유저 고유 부여 id
//	
//	@Column(nullable =false)
//	private String username;
//	
//	private String password;
//	private String role;
//	private String authProvider;
//
//}
