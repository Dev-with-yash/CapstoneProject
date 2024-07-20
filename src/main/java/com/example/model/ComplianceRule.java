package com.example.model;


import java.util.function.Predicate;


public class ComplianceRule {
	
	 private String ruleId;
	 private String description;
	 private Predicate<Configuration> rule;
	
	 public boolean evaluate(Configuration config) {
	 return rule.test(config);
	 }
	
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Predicate<Configuration> getRule() {
		return rule;
	}
	public void setRule(Predicate<Configuration> rule) {
		this.rule = rule;
	}
	public ComplianceRule(String ruleId, String description, Predicate<Configuration> rule) {
		super();
		this.ruleId = ruleId;
		this.description = description;
		this.rule = rule;
	}
	
	@Override
	public String toString() {
		return "ComplianceRule [ruleId=" + ruleId + ", description=" + description + "]";
	}
	
	}