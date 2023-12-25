package it.dao;

import it.pojos.Person;

public interface PersonDao {

    Person getPerson(int id);

    Person loadPerson(int id);

    void addPerson(Person person);

    void deletePerson(Person person);


}
