package com.acn.yrs.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "QUESTIONS")
public class Question {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "prioritynumber", nullable = false)
	private Integer priorityNumber;

	@Column(name = "question", nullable = false)
	private String question;

	@Column(name = "isactive", nullable = false)
	private Integer isActive;

	@ManyToOne
	@JoinColumn(name = "answertypeid")
	private AnswerType answerTypes;

	@Column(name = "yesweight", nullable = false)
	private Integer yesWeight;

	@Column(name = "noweight", nullable = false)
	private Integer noWeight;

	@Column(name = "trueweight", nullable = false)
	private Integer trueWeight;

	@Column(name = "falseweight", nullable = false)
	private Integer falseWeight;


	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	private List<Answer> answers;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the priorityNumber
	 */
	public Integer getPriorityNumber() {
		return priorityNumber;
	}

	/**
	 * @param priorityNumber the priorityNumber to set
	 */
	public void setPriorityNumber(Integer priorityNumber) {
		this.priorityNumber = priorityNumber;
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the isActive
	 */
	public Integer getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	/**
	 * @return the answerTypes
	 */
	public AnswerType getAnswerTypes() {
		return answerTypes;
	}

	/**
	 * @param answerTypes the answerTypes to set
	 */
	public void setAnswerTypes(AnswerType answerTypes) {
		this.answerTypes = answerTypes;
	}

	/**
	 * @return the yesWeight
	 */
	public Integer getYesWeight() {
		return yesWeight;
	}

	/**
	 * @param yesWeight the yesWeight to set
	 */
	public void setYesWeight(Integer yesWeight) {
		this.yesWeight = yesWeight;
	}

	/**
	 * @return the noWeight
	 */
	public Integer getNoWeight() {
		return noWeight;
	}

	/**
	 * @param noWeight the noWeight to set
	 */
	public void setNoWeight(Integer noWeight) {
		this.noWeight = noWeight;
	}

	/**
	 * @return the trueWeight
	 */
	public Integer getTrueWeight() {
		return trueWeight;
	}

	/**
	 * @param trueWeight the trueWeight to set
	 */
	public void setTrueWeight(Integer trueWeight) {
		this.trueWeight = trueWeight;
	}

	/**
	 * @return the falseWeight
	 */
	public Integer getFalseWeight() {
		return falseWeight;
	}

	/**
	 * @param falseWeight the falseWeight to set
	 */
	public void setFalseWeight(Integer falseWeight) {
		this.falseWeight = falseWeight;
	}

	/**
	 * @return the answers
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}
