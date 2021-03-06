package com.programar.todo.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.programar.todo.domain.Todo;
import com.programar.todo.services.TodoService;


@RestController
@CrossOrigin("*")
@RequestMapping(value = "/todos")
public class TodoResource {

	@Autowired
	TodoService todoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Optional<Todo>> findById(@PathVariable Integer id) {
		Optional<Todo> obj = todoService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping(value = "/open")
	public ResponseEntity<List<Todo>> listOpen() {
		List<Todo> list = todoService.findAllOpen();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/close")
	public ResponseEntity<List<Todo>> listClose() {
		List<Todo> list = todoService.findAllClose();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/listAll")
	public ResponseEntity<List<Todo>> listAll() {
		List<Todo> list = todoService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Todo> create(@RequestBody Todo todo) {
		Todo todo1 = todoService.create(todo);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(todo.getId()).toUri();
		return ResponseEntity.created(uri).body(todo1);
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable Integer id) {
		todoService.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Todo> update(@PathVariable Integer id, @RequestBody Todo obj) {
		Todo newObj = todoService.update(id, obj);
		return ResponseEntity.ok().body(newObj);
	}
}
