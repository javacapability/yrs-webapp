package com.acn.yrs.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.JoinFormula;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="QUESTIONNAIRE")
public class Questionnaire extends ResponseObject{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	@Expose
	private int id;


	@Column(name = "assessmentid")
	protected int assessmentId;

	@ManyToOne
	@JoinColumn(name = "questionid")
	@Expose
	private Question question;

	@ManyToOne
	@JoinColumn(name = "answerid", nullable=true)
	@Expose
	private Answer answer;


	@Column(name = "answertxt")
	@Expose
	private String answerTxt;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}



	/**
	 * @return the answerTxt
	 */
	public String getAnswerTxt() {
		return answerTxt;
	}

	/**
	 * @param answerTxt the answerTxt to set
	 */
	public void setAnswerTxt(String answerTxt) {
		this.answerTxt = answerTxt;
	}

	/**
	 * @return the assessment
	 */
	public int getAssessmentId() {
		return assessmentId;
	}

	/**
	 * @param assessment the assessment to set
	 */
	public void setAssessmentId(int assessmentId) {
		this.assessmentId = assessmentId;
	}

	/**
	 * @return the answer
	 */
	public Answer getAnswer() {
		return answer;
	}

	/**
	 * @param answer the answer to set
	 */
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}


}
