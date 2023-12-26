package it.dao;

import it.configuration.HibernateUtilTest;
import it.configuration.TestConfiguration;
import it.pojos.Person;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class PersonDaoImplTest {

    PersonDao personDao;

    @Before
    public void setUp() throws Exception {
        personDao = new PersonDaoImpl(HibernateUtilTest.getSessionFactory());
        Connection conn = TestConfiguration.getConnection();
        conn.createStatement().executeUpdate(
                "DELETE FROM T_PERSON;"
        );
    }

    @After
    public void tearDown() throws Exception {
        Connection conn = TestConfiguration.getConnection();
        conn.createStatement().executeUpdate(
                "DELETE FROM T_PERSON;"
        );
        personDao = null;
    }

    @Test
    public void getPerson() throws SQLException, ClassNotFoundException {
        // Given
        Connection conn = TestConfiguration.getConnection();
        conn.createStatement().executeUpdate(
                "insert into T_PERSON values (1, 25, 'Merlin', 'Monro');"
        );
        conn.close();
        // When
        Person person = personDao.getPerson(1);

        // Then
        assertEquals((Integer) 25, person.getAge());
        assertEquals("Merlin", person.getName());
        assertEquals("Monro", person.getSurname());
    }

    @Test
    public void loadPerson() {
    }

    @Test
    public void addPerson() throws SQLException, ClassNotFoundException {
        // Given
        Person person = new Person(
                1, 25, "Merlin", "Monro"
        );
        // When
        personDao.addPerson(person);

        // Then
        Connection conn = TestConfiguration.getConnection();
        ResultSet rs = conn.createStatement().executeQuery(
                "select id from T_PERSON where NAME='Merlin' and SURNAME='Monro';"
        );
        rs.next();
        int actualCount = rs.getInt(1);
        assertEquals(1, actualCount);
        conn.close();
    }

    @Test
    public void deletePerson() throws SQLException, ClassNotFoundException {
        // Given
        Connection conn = TestConfiguration.getConnection();
        conn.createStatement().executeUpdate(
                "insert into T_PERSON values (1, 25, 'Merlin', 'Monro');"
        );
        ResultSet rs1 = conn.createStatement().executeQuery(
                "select id from T_PERSON where NAME='Merlin' and SURNAME='Monro';"
        );
        rs1.next();
        int actualCount1 = rs1.getInt(1);
        assertEquals(1, actualCount1);
        Person person = new Person(
                1, 25, "Merlin", "Monro"
        );
        // When
        personDao.deletePerson(person);
        // Then

        ResultSet rs2 = conn.createStatement().executeQuery(
                "select count(*) from T_PERSON;"
        );
        rs2.next();
        int actualCount2 = rs2.getInt(1);
        assertEquals(0, actualCount2);
        conn.close();
    }
}