package com.poll.miller.repo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poll.miller.model.PollAnswer;
import com.poll.miller.model.PollQuestion;

@Repository
public class PollAnswerRepo {
	
	@PersistenceContext EntityManager em;
	
	@Transactional(rollbackFor=Exception.class)
	public void registerPollAnswer(PollAnswer pollAnswer) {
		em.merge(pollAnswer);
	}
}
