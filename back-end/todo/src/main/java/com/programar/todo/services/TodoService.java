package com.programar.todo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.programar.todo.domain.Todo;
import com.programar.todo.repositories.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	public Optional<Todo> findById(Integer id) {
		return todoRepository.findById(id);
	}
	
	public List<Todo> findAllOpen(){
		List<Todo> list = todoRepository.findAllOpen();
		return list;
	}
	
	public List<Todo> findAllClose(){
		List<Todo> list = todoRepository.findAllClose();
		return list;
	}
	
	public List<Todo> findAll(){
		List<Todo> list = todoRepository.findAll();
		return list;
	}
	
	public Todo create(Todo todo) {
		todo.setId(null);
		return todoRepository.save(todo);
	}

	public void delete(Integer id) {
		todoRepository.deleteById(id);
		
	}

	public Todo update(Integer id, Todo obj) {
		Optional<Todo> newObj = findById(id);
		newObj.get().setTitulo(obj.getTitulo());
		newObj.get().setDataParaFinalizar(obj.getDataParaFinalizar());
		newObj.get().setDescricao(obj.getDescricao());;
		newObj.get().setFinalizado(obj.getFinalizado());
	
		Todo todo = new Todo();
		todo = newObj.get();
		return todoRepository.save(todo);
		
	}
}
