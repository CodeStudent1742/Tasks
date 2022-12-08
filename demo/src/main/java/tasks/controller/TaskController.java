package tasks.controller;

import org.springframework.web.bind.annotation.*;
import tasks.domain.TaskDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    List<TaskDto>tasks = new ArrayList<>();

    @GetMapping
    public List<TaskDto> getTasks() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{taskId}")
    public TaskDto getTask(@PathVariable Long taskId) {

        return new TaskDto(1L, "test title", "test_content");

    }

    @DeleteMapping(value = "{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        getTasks().remove(taskId);
    }

    @PutMapping
    public TaskDto updateTask(TaskDto taskDto) {

        return new TaskDto(1L, "Edited test title", "Test content");
    }

    @PostMapping
    public void createTask(@RequestBody TaskDto taskDto) {

    }
}
