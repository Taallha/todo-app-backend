package com.project.todorestfulwebservices.helloWorld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HelloWorldResoruce {
	
	@GetMapping("/basicauth")
	public String basicAuthControl() {
		return "Success";
	}
	
	@GetMapping("/hello-world")
	public HelloWorldBean retrieveHelloWorldBean(){
		return new HelloWorldBean("estağfirullahillezi la ilahe illa hu el hayyum kayyum ve etübu ileyh");
	}
	
	@GetMapping("/str-hello-world")
	public String retrieveHello() {
		return "String kalpten döndürülmeye çalışıldı dönüldü";
	}
	
	@GetMapping("/path-variable/{name}")
	public HelloWorldBean retrievePathVariableHelloWorldBean(@PathVariable String name) {
		
		return new HelloWorldBean("üstünlük ancak "+name+" dadır");
	}
	
}
