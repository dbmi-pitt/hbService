package com.edu.pitt.dbmi.hbService.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
	
	private String loadSource;
	private String loadDate;
	private int researchId;
	private int sourceId;
	private String sourceType;
	private String sourceName;
	
	public Person() {}
	
	public Person(String loadSource, String loadDate, int researchId, int sourceId, String sourceType,
			String sourceName) {
		super();
		this.loadSource = loadSource;
		this.loadDate = loadDate;
		this.researchId = researchId;
		this.sourceId = sourceId;
		this.sourceType = sourceType;
		this.sourceName = sourceName;
	}

	public String getLoadSource() {
		return loadSource;
	}

	public void setLoadSource(String loadSource) {
		this.loadSource = loadSource;
	}

	public String getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(String loadDate) {
		this.loadDate = loadDate;
	}

	public int getResearchId() {
		return researchId;
	}

	public void setResearchId(int researchId) {
		this.researchId = researchId;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
}
