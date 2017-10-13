package com.dev.ToDo.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.ToDo.model.TodoItem;

@Repository
public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {

	List<TodoItem> findByTitle(String title);
	
	List<TodoItem> findByCompleted(Boolean completed);

	List<TodoItem> findAll();
	
}
