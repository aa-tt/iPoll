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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poll.miller.model.PollAnswer;
import com.poll.miller.model.PollQuestion;
import com.poll.miller.repo.PollAnswerRepo;
import com.poll.miller.repo.PollQuestionRepo;

import io.swagger.annotations.ApiOperation;

@RestController
public class PollAnswerController {
	
	@Autowired PollQuestionRepo pollQuestionRepo;
	@Autowired PollAnswerRepo pollAnswerRepo;
	
	@ApiOperation(value = "Register answers for a Poll")
	@RequestMapping(value="/pollQuestion/{quesId}/pollAnswer", method = RequestMethod.POST, consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createPollAnswers(@PathVariable Integer quesId, @RequestBody List<PollAnswer> pollAnswers) {
		pollAnswers.forEach(p -> p.setPollQuestion(pollQuestionRepo.getPollQuestionById(quesId)));
		try {
			pollAnswers.forEach(p -> this.pollAnswerRepo.registerPollAnswer(p));
			return new ResponseEntity<String>("Suck Not", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String> ("It Sucked", HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
}
