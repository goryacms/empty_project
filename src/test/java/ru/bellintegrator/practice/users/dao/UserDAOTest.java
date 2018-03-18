package ru.bellintegrator.practice.users.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.practice.Application;
import ru.bellintegrator.practice.guides.model.DocUser;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.users.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class UserDAOTest {
    @Autowired
    private UserDAO userDAO;

    @Test
    public void test() {
        User user = new User();

        /**
         * Проверка метода all
         */
        List<User> users = userDAO.all();
        Assert.assertEquals(20, users.size());

        /**
         * Проверка метода loadById
         */
        User us1 = userDAO.loadById((long) 20);
        Assert.assertEquals("Главный офис", us1.getOffice().getName());

        Assert.assertEquals(24, us1.getAge());

        Set<DocUser> docUsers = us1.getDocUsers();

        Assert.assertEquals(3, docUsers.size());

    }
}
