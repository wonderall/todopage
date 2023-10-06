package com.reca.todo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Todo")
public class TodoEntity {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name="system-uuid", strategy="uuid")
	private String id; // 오브젝트 아이디
	
	private String userId; // 이 오브젝트를 생성한 유저의 아이디
	private String title; // TOdo 타이틀 예 ) 공부하기
	private boolean done; // 완료한 경유 true 

}
