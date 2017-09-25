package com.edu.pitt.dbmi.hbService.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.edu.pitt.dbmi.hbService.database.Database;
import com.edu.pitt.dbmi.hbService.database.MockDatabase;
import com.edu.pitt.dbmi.hbService.exceptions.DataNotFoundException;
import com.edu.pitt.dbmi.hbService.exceptions.PersonExistsException;
import com.edu.pitt.dbmi.hbService.models.Person;

public class PersonService {
	
	/*private Map<Long, Person> persons = MockDatabase.getPersons();
	
	// Mock some data
	public PersonService() {
		persons.put(1L, new Person("MOCK", "15-AUG-17", 1L, 1L, "Test", "Test 1"));
		persons.put(2L, new Person("MOCK", "15-AUG-17", 2L, 2L, "Test", "Test 1"));
		persons.put(3L, new Person("MOCK", "15-AUG-17", 3L, 3L, "Test", "Test 1"));
	}*/
	
	// Returns all the rows in the database
	public List<Person> getAllPersons() {
		Database db = new Database();
		db.connect();
		List<Person> list = new ArrayList<Person>();
		ResultSet rslt = db.queryAll();
		try {
			list = db.getResults(rslt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		db.disconnect();
		return list;
	}
	
	// Returns a list of persons matching the source id
	public List<Person> getPersonBySourceId(int sourceId) {
		Database db = new Database();
		db.connect();
		List<Person> list = new ArrayList<Person>();
		ResultSet rslt  = db.queryBySourceId(sourceId);
		try {
			list = db.getResults(rslt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.isEmpty())
			throw new DataNotFoundException("ERR: Person " + sourceId + " not found");
		db.disconnect();
		return list;
	}
	
	// Returns a list of all persons by source type
	public List<Person> getAllBySourceType(String sourceType) {
		Database db = new Database();
		db.connect();
		List<Person> list = new ArrayList<Person>();
		ResultSet rslt  = db.queryAllBySourceType(sourceType);
		try {
			list = db.getResults(rslt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.isEmpty())
			throw new DataNotFoundException("ERR: Person " + sourceType + " not found");
		db.disconnect();
		return list;
	}
	
	// Returns a list of persons matching the source type and source id
	public List<Person> getPersonBySource(String sourceType, int sourceId) {
		Database db = new Database();
		db.connect();
		List<Person> list = new ArrayList<Person>();
		ResultSet rslt = db.queryBySource(sourceType, sourceId);
		try {
			list = db.getResults(rslt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(list.isEmpty()) 
			throw new DataNotFoundException("ERR: Person " + sourceType + " " + sourceId + " not found");
		db.disconnect();
		return list;
	}
	
	// Searches database for research Id then adds new source id 
	public void addSource(String sourceType, int sourceId, int researchId) throws SQLException {
		Database db = new Database();
		db.connect();
		List<Person> list = new ArrayList<Person>();
		ResultSet rslt = db.queryByResearchId(researchId);
		try {
			list = db.getResults(rslt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Query return a List<Person> based on the researchId
		if(list.isEmpty())
			throw new DataNotFoundException("ERR: Research Id " + researchId + " not found");
		else {
			if(!checkSourceType(list, sourceType))
				throw new PersonExistsException("ERR: Person already has source id in source type: " + sourceType);
			else {
				rslt = db.addSource(sourceType, sourceId, researchId);
			}
		}
	}
	
	// Checks to see if the source type already exists in the list of person ids by research id
	private boolean checkSourceType(List<Person> list, String sourceType) {
		boolean bool = true;
		for(int i = 0; i < list.size(); i++) {
			if(sourceType.toUpperCase().contains(list.get(i).getSourceType()))
				bool = false;
		}
		return bool;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
