package com.dev.ToDo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ToDo.dao.TodoItemRepository;
import com.dev.ToDo.model.TodoItem;
import com.dev.ToDo.service.TodoItemService;

@Service
public class TodoItemServiceImpl implements TodoItemService {

	@Autowired
	private TodoItemRepository dao;
	
	@Transactional
	@Override
	public TodoItem save(TodoItem item) {
		return dao.save(item);
	}
	
	@Transactional
	@Override
	public void saveAll(List<TodoItem> items) {
		if(null == items || 1 > items.size()) {
			return;
		}
		for(TodoItem item: items) {
			dao.save(item);
		}
	}
	
	@Override
	public List<TodoItem> findAll() {
		return dao.findAll();
	}
	
	@Override
	public List<TodoItem> findCompletedItems() {
		return dao.findByCompleted(true);
	}
	
	@Override
	public List<TodoItem> findPendingItems() {
		return dao.findByCompleted(false);
	}
	
	@Transactional
	@Override
	public Boolean changeCompleted (Long id, Boolean completed) {
		TodoItem item = dao.findOne(id);
		if (null == item) {
			return false;
		}
		item.setCompleted(completed);
		save(item);
		return true;
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		dao.delete(id);
	}
	
	@Transactional
	@Override
	public void deleteAll(List<Long> ids) {
		if (null == ids || 1 > ids.size()) {
			return;
		}
		ids.stream().forEach(id -> dao.delete(id));
	}
	
}
