package com.poll.miller.repo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poll.miller.model.PollAnswer;
import com.poll.miller.model.PollQuestion;

@Repository
public class PollQuestionRepo {
	
	@PersistenceContext EntityManager em;
	
	@Transactional(rollbackFor=Exception.class)
	public void registerPollQuestion(PollQuestion pollQuestion) {
		Set<PollAnswer> pollAnswers = new HashSet<PollAnswer>();
		PollAnswer pollAnswer = new PollAnswer();
		
		pollAnswers.add(pollAnswer);
		pollQuestion.setPollAnswers(pollAnswers);
		em.persist(pollQuestion);
		if(em.contains(pollQuestion)) System.out.println("poll question created, questionId-> " + pollQuestion.getQuesId());
	}
	
	@Transactional(rollbackFor=Exception.class)
	public PollQuestion getPollQuestionById(Integer pollQuestionId) {
		return em.find(PollQuestion.class, pollQuestionId);
	}
}
