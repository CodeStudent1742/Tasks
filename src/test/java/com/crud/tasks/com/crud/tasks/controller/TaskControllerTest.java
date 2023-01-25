package com.crud.tasks.com.crud.tasks.controller;

import com.crud.tasks.com.crud.tasks.domain.Task;
import com.crud.tasks.com.crud.tasks.domain.TaskDto;
import com.crud.tasks.com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private TaskController taskController;
    @MockBean
    private DbService service;
    @MockBean
    private TaskMapper taskMapper;

    @Test
    void shouldFetchEmptyTaskList() throws Exception {
        //GIVEN
        when(taskController.getTasks()).thenReturn(new ResponseEntity(new ArrayList<>(), HttpStatus.OK));

        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

//    @Test
//    void shouldFetchTaskList() throws Exception {
//        //GIVEN
//        //WHEN & THEN
//    }

    @Test
    void shouldFetchTaskById() throws Exception {
        //GIVEN
//        Task task = new Task(1L, "task1", "content1");
        TaskDto taskDto = new TaskDto(1L, "task1", "content1");
//        when(service.getTask(1L)).thenReturn(task);
        when(taskController.getTask(1L)).thenReturn(new ResponseEntity(taskDto, HttpStatus.OK));
        //WHEN & THEN
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks/{taskId}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("task1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content1")));
    }

    @Test
    void shouldCreateNewTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(10L, "title1", "content1");
        Task task = new Task(10L, "title1", "content1");

        when(taskController.createTask(any(TaskDto.class))).thenReturn(new ResponseEntity(task, HttpStatus.OK));
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content1")));
    }

    @Test
    void shouldUpdateTask() throws Exception {
        // Given
        TaskDto taskDto = new TaskDto(10L, "title1", "content1");
        Task task = new Task(10L, "title1", "content1");
        TaskDto taskDtoReturned = new TaskDto(15L, "title1", "content1");

        when(taskController.updateTask(any(TaskDto.class))).thenReturn(new ResponseEntity(taskDtoReturned , HttpStatus.OK));
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(15)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("title1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("content1")));
    }

    @Test
    void shouldDeleteTask() throws Exception {
        Task task = new Task(1L, "title1", "content1");
        when(taskController.deleteTask(any(Long.class))).thenReturn(new ResponseEntity(HttpStatus.OK));
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("/v1/tasks/1 ")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200));

    }


}