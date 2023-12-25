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
        personDao = null;
    }

    @Test
    public void getPerson() {
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
    }

    @Test
    public void deletePerson() {
    }
}