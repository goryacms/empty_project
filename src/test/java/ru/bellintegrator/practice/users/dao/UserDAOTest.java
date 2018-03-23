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
//import ru.bellintegrator.practice.guides.model.DocUser;
//import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.guides.dao.CitizenshipDAO;
import ru.bellintegrator.practice.guides.dao.DocDAO;
import ru.bellintegrator.practice.guides.model.Citizenship;
import ru.bellintegrator.practice.guides.model.Doc;
import ru.bellintegrator.practice.guides.model.DocUser;
import ru.bellintegrator.practice.users.model.User;

//import java.util.HashSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
//import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private DocDAO docDAO;

    @Autowired
    private CitizenshipDAO CitizDAO;

    private static final int DEFAULT_COUNT_FROM_DB = 20;
    private static final int INSERT_COUNT_FROM_DB  = 21;
    private static final int DELETE_COUNT_FROM_DB  = 19;

    /**
     * Проверка delete
     */
    @Test
    public void delete() {
        userDAO.delete(2L);

        Assert.assertEquals(DELETE_COUNT_FROM_DB, userDAO.all().size());
    }

    /**
     * Проверка all()
     */
    @Test
    public void all(){
        List<User> users = userDAO.all();
        Assert.assertEquals(DEFAULT_COUNT_FROM_DB, users.size());
    }

    /**
     * Проверка метода loadById
     */
    @Test
    public void loadById(){
        User user = userDAO.loadById(10L);

        Assert.assertEquals("Али", user.getFirstName());
        Assert.assertEquals("ул. Гагарина, 1-а", user.getOffice().getAddress());
    }

    /**
     * update
     */
    @Test
    public void update() {
        User us = new User();
        us.setId(19L);
        us.setFirstName("Иван");
        us.setMiddleName("Александрович");
        us.setLastName("Шпедт");
        us.setPosition("Драматург");
        us.setSalary(15000);

        String d1 = "21.10.2012";
        String d2 = "24.12.2011";
        SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");

        Date regDt = null; Date docDt = null;
        try {
            regDt = format.parse(d1);
            docDt = format.parse(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }




        us.setRegistrationDate(regDt);
        us.setPhone("35-15-90");

        // Загрузка документов
        DocUser docUser = new DocUser();
        docUser.setDocDate(docDt);
        docUser.setDocNumber(12123165115165165L);

        Doc doc = docDAO.loadById(1); // Это же справочник документов, значит берём из базы

        docUser.setDocs(doc);

        us.addDocUser(docUser);

        // Загрузка citizenship
        Citizenship citizen = CitizDAO.loadByCode(398L); // Тоже справочник
        us.setCitizenship(citizen);

        userDAO.update(us);


        User us1 = userDAO.loadById(19L);

        Assert.assertEquals("Иван", us1.getFirstName());
        Assert.assertEquals("Драматург", us1.getPosition());


    }

}
