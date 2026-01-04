package com.pas162.learning.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	// Save a new task
	public Task saveTask(TaskDTO taskDTO) {
		// 1. Create a fresh Entity "shell" 🐚
		Task task = new Task();

		// 2. Transfer the data from the DTO to the Entity ➡️
		task.setName(taskDTO.getName());
		task.setStatus(taskDTO.getStatus());

		// 3. The Database handles the ID and Timestamp automatically ⚙️
		return taskRepository.save(task);
	}

	// Get all tasks
	public List<TaskDTO> getAllTasks() {
		return taskRepository.findAll().stream().map(this::convertToDTO).toList();
	}

	public Task updateTask(Long id, TaskDTO taskDTO) {
		return taskRepository.findById(id).map(existingTask -> {
			existingTask.setName(taskDTO.getName());
			existingTask.setStatus(taskDTO.getStatus());
			return taskRepository.save(existingTask);
		}).orElseThrow(() -> new RuntimeException("Task not found with id " + id));
	}

	public void deleteTask(Long id) {
		if (!taskRepository.existsById(id)) {
			throw new RuntimeException("Cannot delete. Task not found with id: " + id);
		}

		taskRepository.deleteById(id);
	}

	public List<TaskDTO> getTasksByStatus(@RequestParam String status) {
		return taskRepository.findByStatus(status).stream().map(this::convertToDTO).toList();
	}

	private TaskDTO convertToDTO(Task task) {
		return new TaskDTO(task.getId(), task.getName(), task.getStatus());
	}
}