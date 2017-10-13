package com.dev.ToDo.service;

import java.util.List;

import com.dev.ToDo.model.TodoItem;

public interface TodoItemService {

	TodoItem save(TodoItem item);

	List<TodoItem> findAll();

	List<TodoItem> findCompletedItems();

	List<TodoItem> findPendingItems();

	Boolean changeCompleted(Long id, Boolean completed);

	void delete(Long id);

	void saveAll(List<TodoItem> items);

	void deleteAll(List<Long> ids);

}
