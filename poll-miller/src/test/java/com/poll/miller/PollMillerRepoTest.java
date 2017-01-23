package com.poll.miller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.poll.miller.config.AppConfig;
import com.poll.miller.model.PollQuestion;
import com.poll.miller.repo.PollQuestionRepo;

/**
 * @author Josh Long
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
//@SpringApplicationConfiguration(classes = AppPersistenceConfig.class)

public class PollMillerRepoTest {
	
    @Autowired
    private PollQuestionRepo pollQuestionRepo;

    /*@Before
    public void setup() {
    	ApplicationContext ctx = new AnnotationConfigApplicationContext(AppPersistenceConfig.class);
    	LocalContainerEntityManagerFactoryBean lemf = (LocalContainerEntityManagerFactoryBean)ctx.getBean(LocalContainerEntityManagerFactoryBean.class);
    	EntityManagerFactory emf = lemf.getNativeEntityManagerFactory();
    	EntityManager em = emf.createEntityManager();
    	em.getTransaction().begin();
    	
    	Customer customer = new Customer();
    	Set<Packag> packags = new HashSet<Packag>();
    	Packag packag = new Packag();
    	em.persist(packag);
    	packags.add(packag);
    	customer.setPackags(packags);
    	
    	em.persist(customer);
    	em.getTransaction().commit();
    }*/
    
    @Test
    public void testCustomerDAO_getCustomerById() {
    	
    	PollQuestion pollQuestion = new PollQuestion();
    	//System.out.println(pollQuestionRepo.registerPollQuestion(pollQuestion));
    	pollQuestionRepo.registerPollQuestion(pollQuestion);
    }
}
