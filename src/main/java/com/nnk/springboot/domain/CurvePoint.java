package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import java.sql.Timestamp;

@Entity
@Table(name = "curvepoint")
public class CurvePoint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "CurveId is mandatory")
	private Integer curveId;
	private Timestamp asOfDate;
	@DecimalMin(value = "1.0", message = "Term minimum value is 1.0")
	private Double term;
	@DecimalMin(value = "1.0", message = "Value minimum value is 1.0")
	private Double value;
	private Timestamp creationDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCurveId() {
		return curveId;
	}

	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}

	public Timestamp getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Timestamp asOfDate) {
		this.asOfDate = asOfDate;
	}

	public Double getTerm() {
		return term;
	}

	public void setTerm(Double term) {
		this.term = term;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

}
