package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.domains.Employee;
import com.example.demo.domains.Task;
import com.example.demo.repositories.EmployeeRepo;
import com.example.demo.repositories.TaskRepository;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.TaskService;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.AssertFalse;
@Transactional
@SpringBootTest
class DemoApplicationTests {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	TaskService taskService;

	@Autowired
	TaskRepository taskRepository;

	@Test
	void contextLoads() {

		List<Task> t = taskService.findAll();
		Long id_task=t.get(0).getTask_id();
		System.out.println(id_task);
		assertEquals(1, 1);
        System.out.println("ciao");

	}

	@Test
	void contextLoads_d() {

		Optional<Task> t = taskService.findById(1959L);
		if (t.isPresent()) {
			// System.out.println(t);
			// taskService.delete(t.get().getTask_id());
			taskRepository.delete(t.get());

		}

	}

	// @Test
	// void contextLoads_d1()
	// {
	// 	//String name = RandomStringUtils.randomAlphabetic(8);
	// 	HttpUriRequest request = new HttpGet("http://localhost:8099/api/employee");

	// 	// When
	// 	HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

	// 	// Then
	// 	assertThat(
	// 			httpResponse.getStatusLine().getStatusCode(),
	// 			equalTo(HttpStatus.SC_NOT_FOUND));

	// }
}