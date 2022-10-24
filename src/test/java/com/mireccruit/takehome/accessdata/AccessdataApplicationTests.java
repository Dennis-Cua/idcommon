package com.mireccruit.takehome.accessdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mireccruit.takehome.accessdata.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AccessdataApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void newTaskAPI() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders
						.put("/newTask")
						.content(asJsonString(new Task("Task 1", "Doing a first task",true)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
	}

	@Test
	public void updateTaskAPI() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders
						.post("/updateTask/{id}", 1)
						.content(asJsonString(new Task(1, "Task 2", "Accompliishing second task" ,true)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Task 2"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Accompliishing second task"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(true));
	}


	@Test
	public void getTaskListAPI() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders
						.get("/GetTaskList")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
	}

	@Test
	public void getTaskByIDAPI() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders
						.get("/GetTask/{id}", 1)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
	}


	@Test
	public void deleteTaskAPI() throws Exception
	{
		mvc.perform( MockMvcRequestBuilders.delete("/deleteTask/{id}", 1) )
				.andExpect(status().isAccepted());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
