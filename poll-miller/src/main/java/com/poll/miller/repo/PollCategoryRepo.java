package com.poll.miller.repo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.poll.miller.model.PollAnswer;
import com.poll.miller.model.PollCategory;
import com.poll.miller.model.PollQuestion;

@Repository
public class PollCategoryRepo {
	
	@PersistenceContext EntityManager em;
	
	public List<PollCategory> getPollCategories() {
		Query query = em.createQuery("SELECT c FROM POLL.TPOLCATG c", PollCategory.class);
		List<PollCategory> qs = query.getResultList();
		return qs;
	}

	@Transactional
	public void addPollCategory(String categoryName) {
		
		PollCategory c = new PollCategory();
		c.setCategoryName(categoryName);
		em.persist(c);
	}
}
