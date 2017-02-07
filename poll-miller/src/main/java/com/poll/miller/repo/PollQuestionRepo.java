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
public class PollQuestionRepo {
	
	@PersistenceContext EntityManager em;
	
	@Transactional(rollbackFor=Exception.class)
	public Integer registerPollQuestion(PollQuestion pollQuestion) {
		/*Set<PollAnswer> pollAnswers = new HashSet<PollAnswer>();
		PollAnswer pollAnswer = new PollAnswer();
		
		pollAnswers.add(pollAnswer);
		pollQuestion.setPollAnswers(pollAnswers);*/
		em.merge(pollQuestion);
		if(em.contains(pollQuestion)) System.out.println("poll question created, questionId-> " + pollQuestion.getQuesId());
		return(pollQuestion.getQuesId());
	}
	
	@Transactional(rollbackFor=Exception.class)
	public PollQuestion getPollQuestionById(Integer pollQuestionId) {
		return em.find(PollQuestion.class, pollQuestionId);
	}
	
	@Transactional
	public List<PollQuestion> getAllQues() {
		Query queryQuesById = em.createNamedQuery("findAllQuesById");
		queryQuesById.setParameter("qId", 1);
		List qs = queryQuesById.getResultList();
		return qs;
	}

	public List<PollQuestion> getPollQuestions() {
		Query query = em.createQuery("SELECT q FROM POLL.TPOLQUES q", PollQuestion.class);
		List<PollQuestion> qs = query.getResultList();
		//qs.sort((q1, q2)-> ((PollQuestion) q1).getQuesText().compareTo(((PollQuestion) q2).getQuesText()));
		return qs;
	}

	@Transactional
	public String removePollQuestion(Integer pollQuestionId) {
		
		PollQuestion q = em.find(PollQuestion.class, pollQuestionId);
		try{
			if(q != null) em.remove(q);
		}catch(Exception e){
			return(e.getMessage());
		}
		return("Poll Removed");
	}

	@Transactional
	public void editPollQuestion(Integer pollQuestionId, PollQuestion pollQuestion) {
		PollQuestion q = em.find(PollQuestion.class, pollQuestionId);
		q.setQuesText(pollQuestion.getQuesText());
		em.merge(q);
	}

	public List<String> getCategories() {
		TypedQuery<String> query = em.createQuery("SELECT DISTINCT categoryName from POLL.TPOLCATG", String.class);
		List<String> categories = query.getResultList();
		return categories;
	}
}
