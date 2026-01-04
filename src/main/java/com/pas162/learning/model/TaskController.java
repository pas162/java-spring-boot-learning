package com.pas162.learning.model;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping
	public List<TaskDTO> getAllTasks() {
		return taskService.getAllTasks();
	}

	@PostMapping
	public Task createTask(@RequestBody TaskDTO taskDTO) {
		return taskService.saveTask(taskDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
		// We call the service, which returns the updated entity
		Task updatedTask = taskService.updateTask(id, taskDTO);

		// We convert it back to a DTO to send to the frontend
		TaskDTO responseDTO = new TaskDTO(updatedTask.getId(), updatedTask.getName(), updatedTask.getStatus());

		return ResponseEntity.ok(responseDTO);
	}

	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
	}

	@GetMapping("/filter")
	public List<TaskDTO> filterTasks(@RequestParam(required = false) String status) {
		if (status == null || status.isEmpty()) {
			return taskService.getAllTasks(); // Reset to all
		}
		return taskService.getTasksByStatus(status); // Apply filter
	}
}
