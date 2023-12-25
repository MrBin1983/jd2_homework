package it.loader;


import it.dao.PersonDao;
import it.dao.PersonDaoImpl;
import it.pojos.Person;
import it.util.HibernateUtil;


public class PersonLoader {

    public static void main(String[] args) {

        PersonDao personDao = new PersonDaoImpl(HibernateUtil.getSessionFactory());

        Person person1 = new Person(1, 21, "Ivan", "Ivanov");
        Person person2 = new Person(2, 38, "Lena", "Petrova");
        Person person3 = new Person(3, 15, "Nikita", "Konev");
        Person person4 = new Person(4, 35, "Yuli", "Slabko");

        personDao.addPerson(person1);
        personDao.addPerson(person2);
        personDao.addPerson(person3);
        personDao.addPerson(person4);

    }

}
