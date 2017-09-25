package com.edu.pitt.dbmi.hbService.database;

import java.util.HashMap;
import java.util.Map;

import com.edu.pitt.dbmi.hbService.models.Person;

public class MockDatabase {

	private static Map<Long, Person> persons = new HashMap<>();
	
	public static Map<Long, Person> getPersons() {
		return persons;
	}
}
