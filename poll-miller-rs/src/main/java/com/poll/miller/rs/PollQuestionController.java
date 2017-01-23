package com.poll.miller.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poll.miller.model.PollQuestion;
import com.poll.miller.repo.PollQuestionRepo;

import io.swagger.annotations.ApiOperation;

@RestController
public class PollQuestionController {
	
	@Autowired PollQuestionRepo pollQuestionRepo;
	
	@ApiOperation(value = "Find Poll Question by Id", notes = "Get Poll Question by specifying Id", httpMethod = "GET", response = PollQuestion.class)
	//@RequestMapping(value="/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/pollQuestion/{pollQuestionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PollQuestion getCustomer(@PathVariable Integer pollQuestionId) {
		return pollQuestionRepo.getPollQuestionById(pollQuestionId);
	}
}
