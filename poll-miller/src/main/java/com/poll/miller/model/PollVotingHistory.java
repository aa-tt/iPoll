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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="POLL.TPOLHIST")
public class PollVotingHistory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id @Column(name="hist_id")
	private Integer histId;
	@Column(name="crt_date")
	private Date crtDate;
	@Column(name="upd_date")
	private Date updDate;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ques_id")
	@JsonIgnore
	private PollQuestion pollQuestion;
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "answ_id")
	@JsonIgnore
	private PollQuestion pollAnswer;
}
