package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.math.BigDecimal;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import com.example.configuration.WebsocketSourceConfiguration;
import com.example.demo.domains.Employee;
import com.example.demo.services.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;


//https://www.baeldung.com/spring-resttemplate-post-json

// tutorial https://www.baeldung.com/spring-boot-testresttemplate
// https://www.javaguides.net/2019/06/spring-resttemplate-get-post-put-and-delete-example.html
@Transactional
//Porta generata a caso
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeServiceTest {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    EmployeeService employeeService;
   
    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();

    //vero test, muove dati. Non come mocktest
    @Test
    public void getEmployee_test(){
        TestRestTemplate testRestTemplate = new TestRestTemplate();//apre una porta sul localhost a caso temporanea. Ritorna un oggetto di tipo String
        ResponseEntity<String> response = testRestTemplate.getForEntity(createURLWithPort("/api/employee/256504"), String.class);
 
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        System.out.println(response.toString());
    }


    // https://www.concretepage.com/spring-5/spring-resttemplate-getforentity
    @Test
    public void getEmployee_rest(){
        String url = createURLWithPort("/api/employee/{id}") ;
        Long empId= 256504L;
        //in questo caso l'oggetto di ritorno è tipizzato su Employee (parse del JSON)
        //getForEntity sono metodi http corrispondenti a get() post()
        ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(url, Employee.class, empId); 
        
        if (responseEntity.hasBody()){
            Employee e = responseEntity.getBody();
            assertEquals(e.getEmployee_id(),empId);

        }

    }

    @Test
    public void testRetrieveEmployee() throws JSONException {

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort("/api/employee/256504"),
                HttpMethod.GET, entity, String.class);

        // JSONAssert.assertEquals(expected, response.getBody(), false);
        // System.out.println(response.getBody());
        String s = response.getBody();
        try {
            //Mappatura dei campi
            Employee e = new ObjectMapper().readValue(s, Employee.class);
            Employee ee = employeeService.findById(e.getEmployee_id()).get();
            assertEquals(e, ee);
            System.out.println(e);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


    @Test
    public void postEmployee(){

        Employee newEmployee = new Employee();
        newEmployee.setEmployee_id(2L);

        newEmployee.setFirst_name("f21");
        newEmployee.setLast_name( "l1");
        newEmployee.setEmail( "a@a1");
        newEmployee.setSalary( BigDecimal.valueOf(100));
        newEmployee.setTotal_work_percentage(1);
        newEmployee.setMembership_count(2L);
        newEmployee.setDepartment_id(1000L);

		//Employee result = restTemplate.postForObject( createURLWithPort("/api/employee"), newEmployee, Employee.class);


                 
        HttpHeaders headers = new HttpHeaders();
 
        HttpEntity<Employee> request = new HttpEntity<>(newEmployee, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity( createURLWithPort("/api/employee"), request, String.class);
         
        //Verify bad request and missing header
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
       // assertTrue( result.getBody().contains("Missing request header"));
    }

    // {"employee_id":256504,"first_name":"Hilel","last_name":"Byers","email":"hilel.byers@ozu.edu.tr","salary":10317.0000,"total_work_percentage":0.1,"membership_count":1,"department_id":1004,"projects"

    @Test
    public void givenDataIsJson_whenDataIsPostedByPostForObject_thenResponseBodyIsNotNull()
            throws IOException {

        JSONObject employeeJsonObject = new JSONObject();
        try {
            employeeJsonObject.put("employee_id", 2);

            employeeJsonObject.put("first_name", "f21");
            employeeJsonObject.put("last_name", "l1");
            employeeJsonObject.put("email", "a@a1");
            employeeJsonObject.put("salary", 100);
            employeeJsonObject.put("total_work_percentage", 1);
            employeeJsonObject.put("membership_count", 2);
            employeeJsonObject.put("department_id", 1000);

            System.out.println(employeeJsonObject);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<String>(employeeJsonObject.toString(), headers);

            String employeeResultAsJsonStr = restTemplate.postForObject(
                    createURLWithPort("/api/employee"), request, String.class);
            JsonNode root = objectMapper.readTree(employeeResultAsJsonStr);
            System.out.println(root);
            assertNotNull(employeeResultAsJsonStr);
            assertNotNull(root);
            assertNotNull(root.path("first_name").asText());

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }



     @Test
     public void deleteEmployee_rest(){
         String url = createURLWithPort("/api/employee/{id}") ;
         Long empId= 2L;
         
         restTemplate.delete(createURLWithPort("/api/employee/"+empId), Employee.class, empId); 

         ResponseEntity<Employee> responseEntity = restTemplate.getForEntity(url, Employee.class, empId); 
         if (responseEntity.hasBody()){
             Employee e = responseEntity.getBody();
             assertEquals(e.getEmployee_id(),empId);
 
         }

 
     }
}
