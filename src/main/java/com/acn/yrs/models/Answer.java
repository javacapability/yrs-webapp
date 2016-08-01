package com.acn.yrs.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "ANSWERS")
public class Answer extends ResponseObject{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	@Expose
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "questionid")
	protected Question question;

	@Expose
	@Column(name = "answernumber", nullable = false)
	private Integer answerNumber;

	@Expose
	@Column(name = "answertxt", nullable = false)
	private String answerTxt;

	@Expose
	@Column(name = "weight", nullable = false)
	private Integer weight;

	@Override
	public String toString() {
		   StringBuffer sb = new StringBuffer();
		    sb.append("Answer = [ ");
		    sb.append(" questionId: " +this.question.getId());
		    sb.append(" answerNumber: " +this.answerNumber);
		    sb.append(" answerTxt: " +this.answerTxt);
		    sb.append(" weight: " +this.weight);
		    sb.append("]");
		    return sb.toString();
	}

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
	 * @return the answerNumber
	 */
	public Integer getAnswerNumber() {
		return answerNumber;
	}

	/**
	 * @param answerNumber the answerNumber to set
	 */
	public void setAnswerNumber(Integer answerNumber) {
		this.answerNumber = answerNumber;
	}

	/**
	 * @return the weight
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
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


}
