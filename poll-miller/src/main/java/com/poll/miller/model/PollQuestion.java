package com.poll.miller.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="POLL.TPOLQUES")
//@JsonAutoDetect
public class PollQuestion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id @Column(name="ques_id")
	private Integer quesId;
	@Column(name="ques_body", length=20)
	private String quesBody;
	@Column(name="crt_date")
	private Date crtDate;
	@Column(name="upd_date")
	private Date updDate;
	
	@OneToMany(fetch = FetchType.EAGER,
			targetEntity=PollAnswer.class, 
			mappedBy = "pollQuestion", 
			cascade=CascadeType.ALL)
	private Set<PollAnswer> pollAnswers;

	public Integer getQuesId() {
		return quesId;
	}

	public void setQuesId(Integer quesId) {
		this.quesId = quesId;
	}

	public Set<PollAnswer> getPollAnswers() {
		return pollAnswers;
	}

	public void setPollAnswers(Set<PollAnswer> pollAnswers) {
		this.pollAnswers = pollAnswers;
	}
}
