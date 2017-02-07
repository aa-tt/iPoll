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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="POLL.TPOLQUES")
//@JsonAutoDetect
@NamedQuery(name="findAllQuesById", query="SELECT q FROM POLL.TPOLQUES q WHERE q.quesId = :qId")
public class PollQuestion implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "ques_seq")
	@SequenceGenerator(name = "ques_seq", sequenceName = "ques_seq", initialValue = 1, allocationSize = 1)
	@Id @Column(name="ques_id")
	private Integer quesId;
	@Column(name="ques_text", length=256)
	private String quesText;
	@Column(name="ques_desc", length=256)
	private String quesDesc;
	@Column(name="crt_date")
	private Date crtDate;
	@Column(name="upd_date")
	private Date updDate;
	@Column(name="visitors_cnt")
	private Integer visitorsCount;
	@Column(name="vote_cnt")
	private Integer voteCount;
	@Column(name="followers_cnt")
	private Integer followersCount;
	@Column(name="status")
	private String status;
	@Column(name="passcode")
	private Integer passcode;
	
	@OneToMany(fetch = FetchType.EAGER,
			//targetEntity=PollAnswer.class, 
			//mappedBy = "pollQuestion", 
			cascade= CascadeType.ALL)
	@JoinColumn(name = "ques_id")
	private Set<PollAnswer> pollAnswers;
	
	@OneToMany(fetch = FetchType.EAGER,
			targetEntity=PollCategory.class, 
			//mappedBy = "pollQuestion", 
			cascade={CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "ques_id")
	private Set<PollCategory> pollCategories;

	public Integer getQuesId() {
		return quesId;
	}

	public void setQuesId(Integer quesId) {
		this.quesId = quesId;
	}

	public String getQuesText() {
		return quesText;
	}

	public void setQuesText(String quesText) {
		this.quesText = quesText;
	}

	public String getQuesDesc() {
		return quesDesc;
	}

	public void setQuesDesc(String quesDesc) {
		this.quesDesc = quesDesc;
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

	public Integer getVisitorsCount() {
		return visitorsCount;
	}

	public void setVisitorsCount(Integer visitorsCount) {
		this.visitorsCount = visitorsCount;
	}

	public Integer getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}

	public Integer getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(Integer followersCount) {
		this.followersCount = followersCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPasscode() {
		return passcode;
	}

	public void setPasscode(Integer passcode) {
		this.passcode = passcode;
	}

	public Set<PollAnswer> getPollAnswers() {
		return pollAnswers;
	}

	public void setPollAnswers(Set<PollAnswer> pollAnswers) {
		this.pollAnswers = pollAnswers;
	}

	public Set<PollCategory> getPollCategories() {
		return pollCategories;
	}

	public void setPollCategories(Set<PollCategory> pollCategories) {
		this.pollCategories = pollCategories;
	}
}
