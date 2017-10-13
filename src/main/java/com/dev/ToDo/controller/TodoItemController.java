package com.dev.ToDo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ToDo.model.TodoItem;
import com.dev.ToDo.service.TodoItemService;

@RestController
@RequestMapping("/api")
public class TodoItemController {

	@Autowired
	private TodoItemService itemService;
	
	@RequestMapping(value = "/item/save", method = RequestMethod.POST)
	public ResponseEntity<TodoItem> save(@RequestBody TodoItem item) {
		TodoItem i = itemService.save(item);
		if(null == i) {
			return new ResponseEntity<TodoItem>(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<TodoItem>(i, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/item/update", method = RequestMethod.POST)
	public ResponseEntity<TodoItem> update(@RequestBody TodoItem item) {
		TodoItem i = itemService.save(item);
		if(null == i) {
			return new ResponseEntity<TodoItem>(HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<TodoItem>(i, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/items/save", method = RequestMethod.POST)
	public void saveAll(@RequestBody List<TodoItem> items) {
		if(null == items || 1 > items.size()) {
			return;
		}
		items.stream().forEach(item -> itemService.save(item));
	}
	
	@RequestMapping(value = "/items", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<TodoItem> findAll() {
		return itemService.findAll();
	}
	
	@RequestMapping(value = "/items/completed", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<TodoItem> findCompletedItems() {
		return itemService.findCompletedItems();
	}
	
	@RequestMapping(value = "/items/pending", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<TodoItem> findPendingItems() {
		return itemService.findPendingItems();
	}
	
	@RequestMapping(value = "/item/changeCompleted", method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Boolean changeCompleted (@PathVariable("id") Long id, @PathVariable("completed") Boolean completed) {
		return itemService.changeCompleted(id, completed);
	}
	
	@RequestMapping(value = "/item/delete/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		itemService.delete(id);
	}
	
	@RequestMapping(value = "/items/delete", method = RequestMethod.DELETE)
	public void delete(List<Long> ids) {
		itemService.deleteAll(ids);
	}
	
}
