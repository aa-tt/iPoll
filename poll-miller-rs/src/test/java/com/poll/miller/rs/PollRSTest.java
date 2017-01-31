package com.poll.miller.rs;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poll.miller.config.AppWebMvcConfig;
import com.poll.miller.model.PollQuestion;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppWebMvcConfig.class })
@WebAppConfiguration
public class PollRSTest {
	
	private MockMvc mockMvc;
	
	AnnotationMethodHandlerAdapter handlerAdapter; // deprecated
    RequestMappingHandlerAdapter requestAdapter;   // this can be used in place of AnnotationMethodHandlerAdapter, But currently I'm using MockMvc
    
    @Autowired PollQuestionController pollQuestionController;
	
    @Before
    public void setup() {
    	this.mockMvc =  MockMvcBuilders.standaloneSetup(pollQuestionController).build();
    }
    
    //@Ignore
    @Test
    public void findQuestionById_Test() throws Exception {
    	
    	this.mockMvc.perform(get("/pollQuestion/1"))
        			.andExpect(status().isOk())
        			.andExpect(content().contentType("application/json"))
        			.andExpect(jsonPath("$.quesId").value(1));
    }
    
    //@Ignore
    @Test
    public void createUpdatePollQuestion_Test() throws Exception {
    	
    	PollQuestion pollQuestion = new PollQuestion();
    	//pollQuestion.setQuesId(10); //dont set id, as this will be set by hibernate
    	pollQuestion.setCrtDate(new Date());
    	String jsonObj = new ObjectMapper().writeValueAsString(pollQuestion);
    	this.mockMvc.perform(post("/pollQuestion").contentType(MediaType.APPLICATION_JSON).content(jsonObj))
        			.andExpect(status().isOk())
        			.andReturn();
    }

}
