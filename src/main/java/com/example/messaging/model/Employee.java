package com.example.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

	private String empName;
	private String empId;

	public Employee(@JsonProperty("empName") String empName, @JsonProperty("empId") String empId) {
		this.empName = empName;
		this.empId = empId;

	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", empId=" + empId + "]";
	}

}
