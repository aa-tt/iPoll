package com.poll.miller.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="POLL.TPOLCATG")
/*@NamedQueries({
	@NamedQuery(name="findAllQuesByCategory", query="SELECT c FROM POLL.TPOLCATG c WHERE c.categoryName LIKE %:cName%")
})*/
public class PollCategory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "catg_seq")
	@SequenceGenerator(name = "catg_seq", sequenceName = "catg_seq", initialValue = 1, allocationSize = 1)	
	@Id @Column(name="cat_id")
	private Integer categoryId;
	@Column(name="cat_name", length=64)
	private String categoryName;
	
	@ManyToOne
	@JoinColumn(name = "ques_id", referencedColumnName = "ques_id", nullable = true)
	@JsonIgnore
	private PollQuestion pollQuestion;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public PollQuestion getPollQuestion() {
		return pollQuestion;
	}

	public void setPollQuestion(PollQuestion pollQuestion) {
		this.pollQuestion = pollQuestion;
	}
}
