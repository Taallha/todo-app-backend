package com.project.todorestfulwebservices.service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.project.todorestfulwebservices.entity.Todo;

//@Component 
public class TodoService {
	
	static List<Todo> todoList = new ArrayList<>();
	static Integer todoCount = 0;
	
	static {
		
		
		todoList.add(new Todo(++todoCount,"Talha","Learning Spring REST"
				,LocalDate.now().plusYears(1),false));
		todoList.add(new Todo(++todoCount,"Talha","Learning Spring Security"
				,LocalDate.now().plusYears(1),false));
		todoList.add(new Todo(++todoCount,"Talha","Learning Spring Jpa Data"
				,LocalDate.now().plusYears(1),false));
		
	}
	
	public Todo createTodo(String username, String description, LocalDate targetDate, boolean isDone) {
		Todo todo = new Todo(++todoCount,username,description,targetDate,isDone);
		todoList.add(todo);
		return todo;	
		
	}
	 
	public List<Todo> findTodoByUsernmae(String username){
		
		Predicate<? super Todo> predicate = 
				todo -> todo.getUsername().equalsIgnoreCase(username);
		return todoList.stream().filter(predicate).toList();
		
	}
	
	public void deleteTodoById(Integer id) {
		
		Todo deletedTodo = findTodoById(id);
		todoList.remove(deletedTodo);
	}
	
	public Todo findTodoById(Integer id) {
		
		Predicate<? super Todo> predicate = todo -> todo.getId() == id;
		Todo todo = todoList.stream().filter(predicate).findFirst().get(); 
		return todo;
	}
	
	public void updateTodo(Todo todo) {
		
		deleteTodoById(todo.getId());
		todoList.add(todo);
	}
	
	
	

}
