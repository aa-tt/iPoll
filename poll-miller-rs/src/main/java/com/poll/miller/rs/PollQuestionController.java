package com.poll.miller.rs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.poll.miller.model.PollQuestion;
import com.poll.miller.repo.PollQuestionRepo;

import io.swagger.annotations.ApiOperation;

@RestController
public class PollQuestionController {
	
	@Autowired PollQuestionRepo pollQuestionRepo;
	
	@ApiOperation(value = "List all Poll Questions")
	//@RequestMapping(value="/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/pollQuestions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PollQuestion> getPollQuestions() {
		return pollQuestionRepo.getPollQuestions();
	}
	
	@ApiOperation(value = "Find Poll Question by Id", notes = "Get Poll Question by specifying Id", httpMethod = "GET", response = PollQuestion.class)
	//@RequestMapping(value="/{customerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping(value="/pollQuestion/{pollQuestionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PollQuestion getPollQuestion(@PathVariable Integer pollQuestionId) {
		return pollQuestionRepo.getPollQuestionById(pollQuestionId);
	}
	
	//@ApiOperation(value = "Register a Poll", notes = "create/update a Poll Question", httpMethod = "POST", response = String.class)
	@ApiOperation(value = "Register a Poll")
	@RequestMapping(value="/pollQuestion", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createUpdatePollQuestion(@RequestBody PollQuestion pollQuestion) {
		try {
			this.pollQuestionRepo.registerPollQuestion(pollQuestion);
			return new ResponseEntity<String>("Suck Not", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> ("It Sucked", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
