package com.poll.miller.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="POLL.TPOLANSW")
public class PollAnswer implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "answ_seq")
	@SequenceGenerator(name = "answ_seq", sequenceName = "answ_seq", initialValue = 1, allocationSize = 1)	
	@Id @Column(name="answ_id")
	private Integer answId;
	@Column(name="answ_body", length=20)
	private String answBody;
	@Column(name="vote_cnt")
	private Integer voteCnt;
	@Column(name="crt_date")
	private Date crtDate;
	@Column(name="upd_date")
	private Date updDate;
	
	@ManyToOne
	@JoinColumn(name = "ques_id", referencedColumnName = "ques_id")
	@JsonIgnore
	private PollQuestion pollQuestion;

	public Integer getAnswId() {
		return answId;
	}

	public void setAnswId(Integer answId) {
		this.answId = answId;
	}

	public String getAnswBody() {
		return answBody;
	}

	public void setAnswBody(String answBody) {
		this.answBody = answBody;
	}

	public Integer getVoteCnt() {
		return voteCnt;
	}

	public void setVoteCnt(Integer voteCnt) {
		this.voteCnt = voteCnt;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}

	public PollQuestion getPollQuestion() {
		return pollQuestion;
	}

	public void setPollQuestion(PollQuestion pollQuestion) {
		this.pollQuestion = pollQuestion;
	}
}
