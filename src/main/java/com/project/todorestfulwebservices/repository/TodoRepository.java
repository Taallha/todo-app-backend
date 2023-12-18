package com.project.todorestfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.todorestfulwebservices.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
	
	public List<Todo> findTodoByUsername(String Username);

}
