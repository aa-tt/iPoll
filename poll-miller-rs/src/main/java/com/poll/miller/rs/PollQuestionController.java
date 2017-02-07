package com.poll.miller.rs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poll.miller.model.PollAnswer;
import com.poll.miller.model.PollQuestion;
import com.poll.miller.repo.PollQuestionRepo;

import io.swagger.annotations.ApiOperation;

@RestController
public class PollQuestionController {
	
	@Autowired PollQuestionRepo pollQuestionRepo;
	
	@ApiOperation(value = "List all Poll Questions")
	@RequestMapping(value="/pollQuestions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PollQuestion> getPollQuestions() {
		return pollQuestionRepo.getPollQuestions();
	}
	
	@ApiOperation(value = "List all Categories")
	@RequestMapping(value="/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<String> getCategories() {
		return pollQuestionRepo.getCategories();
	}
	
	@ApiOperation(value = "Find Poll Question by Id", notes = "Get Poll Question by specifying Id", httpMethod = "GET", response = PollQuestion.class)
	@RequestMapping(value="/pollQuestion/{pollQuestionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PollQuestion getPollQuestion(@PathVariable Integer pollQuestionId) {
		return pollQuestionRepo.getPollQuestionById(pollQuestionId);
	}
	
	@ApiOperation(value = "Register a Poll")
	@RequestMapping(value="/pollQuestion", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createUpdatePollQuestion(@RequestBody PollQuestion pollQuestion) {
		try {
			//pollQuestion.getPollAnswers().forEach(a -> System.out.println(a.getAnswBody()));
			Integer quesId = this.pollQuestionRepo.registerPollQuestion(pollQuestion);
			//return new ResponseEntity<String>("Suck Not", HttpStatus.OK);
			return new ResponseEntity<String>(quesId.toString(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> ("50", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	/*@ApiOperation(value = "Edit a Poll")
	@RequestMapping(value="/pollQuestion/{pollQuestionId}", method = RequestMethod.PUT, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> editPollQuestion(@PathVariable Integer pollQuestionId, @RequestBody PollQuestion pollQuestion) {
		try {
			System.out.println("PUT--" + new ObjectMapper().writeValueAsString(pollQuestion));
			this.pollQuestionRepo.editPollQuestion(pollQuestionId, pollQuestion);
			return new ResponseEntity<String>("Suck Not", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> ("It Sucked", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}*/
	
	/*@ApiOperation(value = "Patch a Poll")
	@RequestMapping(value="/pollQuestion/{pollQuestionId}", method = RequestMethod.PATCH, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> patchPollQuestion(@PathVariable Integer pollQuestionId, @RequestBody PollQuestion pollQuestion) {
		try {
			System.out.println("PATCH--" + new ObjectMapper().writeValueAsString(pollQuestion));
			this.pollQuestionRepo.editPollQuestion(pollQuestionId, pollQuestion);
			return new ResponseEntity<String>("Suck Not", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> ("It Sucked", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}*/
	
	@ApiOperation(value = "Delete a Poll")
	@RequestMapping(value="/pollQuestion/{pollQuestionId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletePollQuestion(@PathVariable Integer pollQuestionId) {
		String message = "Poll Not Removed";
		try {
			message = this.pollQuestionRepo.removePollQuestion(pollQuestionId);
			
		} catch (Exception e) {
			return new ResponseEntity<String> (message, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
