package com.pas162.learning.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	// Save a new task
	public Task saveTask(Task task) {
		task.setCreateAt(LocalDateTime.now());
		return taskRepository.save(task);
	}

	// Get all tasks
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public Task updateTask(Long id, Task taskDetails) {
		Task task = taskRepository.findById(id).orElseThrow();

		task.setName(taskDetails.getName());
		task.setContent(taskDetails.getContent());
		task.setStatus(taskDetails.getStatus());

		return taskRepository.save(task);
	}

	public void deleteTask(Long id) {
		if (!taskRepository.existsById(id)) {
			throw new RuntimeException("Cannot delete. Task not found with id: " + id);
		}

		taskRepository.deleteById(id);
	}
}