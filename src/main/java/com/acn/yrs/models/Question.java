package com.acn.yrs.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;


@Entity
@Table(name = "QUESTIONS")
public class Question extends ResponseObject{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	@Expose
	private Integer id;

	@Column(name = "prioritynumber", nullable = false)
	@Expose
	private Integer priorityNumber;

	@Column(name = "questiontxt", nullable = false)
	@Expose
	private String questionTxt;

	@Column(name = "isactive", nullable = false)
	protected Integer isActive;

	@ManyToOne
	@JoinColumn(name = "answertypeid")
	@Expose
	private AnswerType answerTypes;

	@Column(name = "yesweight", nullable = false)
	@Expose
	private Integer yesWeight;

	@Column(name = "noweight", nullable = false)
	@Expose
	private Integer noWeight;

	@Column(name = "trueweight", nullable = false)
	@Expose
	private Integer trueWeight;

	@Column(name = "falseweight", nullable = false)
	@Expose
	private Integer falseWeight;

    //modified this part
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "questionid")
	@Expose
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
	public String getQuestionTxt() {
		return questionTxt;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestionTxt(String questionTxt) {
		this.questionTxt = questionTxt;
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


	/*@Override
	public String toString() {

		   StringBuffer sb = new StringBuffer();
		    sb.append("Question = [ ");
		    sb.append(" priorityNumber: " +this.priorityNumber);
		    sb.append(" questionTxt: " +this.questionTxt);
		    sb.append(" yesWeight: " +this.yesWeight);
		    sb.append(" noWeight: " +this.noWeight);
		    sb.append(" trueWeight: " +this.trueWeight);
		    sb.append(" falseWeight: " +this.falseWeight);
		    sb.append("]");

		    return sb.toString();
	}*/

}
