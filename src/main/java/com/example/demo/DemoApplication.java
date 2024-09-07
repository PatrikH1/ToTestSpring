package com.example.demo;

import com.example.demo.user.User;
import com.example.demo.user.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Prova kommando: ./gradlew.bat --quiet bootRun
// på katalogen /c/Patrik/git/demo (det går att göra cd och detta som sökväg)
// Starta gärna i ett Git Bash fönster så blir texten bättre.
//
// Det första GET-anropet
// http://localhost:8080/user
//
// Det andra GET-anropet
// http://localhost:8080/hello
// http://localhost:8080/hello?name=Patrik
//
// Det tredje GET-anropet
// http://localhost:8080/helloTwoName
// http://localhost:8080/helloTwoName?firstName=Patrik&secondName=Hulthén
//
// Some more comments.
// Använd också Postman för att prova.

@SpringBootApplication
@RestController
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retriveAllUsers()
	{
		return service.findAll();
	}

	// Retrieves a specific user detail
	@GetMapping("/users/{id}")
	public User retriveUser(@PathVariable int id)
	{
		return service.findOne(id);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("<h1>Hello %s!</h1>", name);
	}

	@GetMapping("/helloTwoName")
	public String helloTwoName(@RequestParam(value = "firstName", defaultValue = "World") String firstName,
							   @RequestParam(value = "secondName", defaultValue = "and World2") String secondName) {
		return String.format("<h1>Hello %s %s!</h1>", firstName, secondName);
	}

	@GetMapping(path="/hello-world")
	public String helloWorld()
	{
		return "Hello World";
	}

}