package com.example.demo;

import com.example.demo.user.User;
import com.example.demo.user.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Prova kommando: ./gradlew.bat --quiet bootRun
// på katalogen /c/Patrik/git/demo (det går att göra cd och detta som sökväg)
// Starta gärna i ett Git Bash fönster så blir texten bättre.
//
// To see information about UserDaoService.java, go to the following link:
// https://www.javatpoint.com/restful-web-services-path-variable
//
// To get the PostmanCollection, see files direct under demo,
// PostmanCollection.json
//
// To start Postman:
// Use Search and write Postman
//
// Det första GET-anropet
// http://localhost:8080/user
// http://localhost:8080/user/3
//
// Det andra GET-anropet
// http://localhost:8080/hello
// http://localhost:8080/hello?name=Patrik
//
// Det tredje GET-anropet
// http://localhost:8080/helloTwoName
// http://localhost:8080/helloTwoName?firstName=Patrik&secondName=Hulthén
//
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

	// Retrieves a specific user
	@GetMapping("/users/{id}")
	public User retriveUser(@PathVariable int id)
	{
		return service.findOne(id);
	}

	// Method that posts a new user
	@PostMapping("/users")
	public void createUser(@RequestBody User user)
	{
		User sevedUser=service.save(user);
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