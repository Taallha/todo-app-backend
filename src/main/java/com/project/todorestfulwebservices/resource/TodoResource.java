package com.project.todorestfulwebservices.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.todorestfulwebservices.entity.Todo;
import com.project.todorestfulwebservices.repository.TodoRepository;
import com.project.todorestfulwebservices.service.TodoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TodoResource {
		
	//TodoService service;
	TodoRepository todoRepository;
	
	public TodoResource(/*TodoService service*/TodoRepository todoRepository) {
//		this.service = service;
		this.todoRepository = todoRepository;
	}
	
	
	@GetMapping("/users/{username}/todos")
	public List<Todo> retrieveTodos(@PathVariable String username){
//		return service.findTodoByUsernmae(username);
		return todoRepository.findTodoByUsername(username);
	}
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todo retrieveTodo(@PathVariable String username, 
			@PathVariable int id) {
//		return service.findTodoById(id);
		return todoRepository.findById(id).get();
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Object> deleteTodo(@PathVariable String username,@PathVariable int id) {
		
//		service.deleteTodoById(id);
		todoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/users/{username}/todos/{id}")
	public Todo updateTodo(@PathVariable int id ,@RequestBody Todo todo) {
		
//		service.updateTodo(todo);
		todoRepository.save(todo);
		return todo;
	}
//	
	@PostMapping("/users/{username}/todos")
	public Todo createTodo(@RequestBody Todo todo) {
		return todoRepository.save(todo);
		
	}
	
	
}
