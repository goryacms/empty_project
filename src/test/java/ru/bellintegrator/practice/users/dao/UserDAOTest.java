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
import ru.bellintegrator.practice.guides.dao.DocUserDAO;
import ru.bellintegrator.practice.guides.model.Citizenship;
import ru.bellintegrator.practice.guides.model.Doc;
import ru.bellintegrator.practice.guides.model.DocUser;
import ru.bellintegrator.practice.guides.view.DocUserView;
import ru.bellintegrator.practice.office.dao.OfficeDAO;
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.users.model.User;

//import java.util.HashSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    private OfficeDAO officeDAO;

    @Autowired
    private DocDAO docDAO;

    @Autowired
    private DocUserDAO docUserDAO;

    @Autowired
    private CitizenshipDAO CitizDAO;

    private static final int DEFAULT_COUNT_FROM_DB = 20;
    private static final int INSERT_COUNT_FROM_DB  = 21;
    private static final int DELETE_COUNT_FROM_DB  = 19;


    private static final SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
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


        DocUser first = user.getDocUser();

        long docCode = first.getDoc().getId();
        String docName = first.getDoc().getName();
        long docNumber = first.getDocNumber();



        Assert.assertEquals(7L, docCode);
        Assert.assertEquals("Военный билет", docName);
        Assert.assertEquals(6843161613L, docNumber);


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
        us.setSalary((double) 15000);

        String d1 = "21.10.2012";
        String d2 = "24.12.2011";

        Date regDt = null; Date docDt = null;
        try {
            regDt = format.parse(d1);
            docDt = format.parse(d2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        us.setRegistrationDate(regDt);
        us.setPhone("35-15-90");

        // Загрузка документов (Код документа = 21 (паспорт гражданина...) , для user_id = 19)
        DocUserView docUserView  = new DocUserView();
        docUserView.userId = 19L;
        docUserView.docId = 21;

        DocUser docUser = (DocUser) docUserDAO.loadByParams( docUserView);
        docUser.setDocDate(docDt);
        docUser.setDocNumber(12123165115165165L);

        // меняю код документа с 21 на 7
        Doc doc = docDAO.loadById(7); // Это же справочник документов, значит берём из базы

        docUser.setDoc(doc);

        //us.addDocUser(docUser);
        us.setDocUser(docUser);

        // Загрузка citizenship
        Citizenship citizen = CitizDAO.loadByCode(398L); // Тоже справочник
        us.setCitizenship(citizen);

        userDAO.update(us);


        // Проверка обновлённых данных

        User us1 = userDAO.loadById(19L);

        Assert.assertEquals("Иван", us1.getFirstName());
        Assert.assertEquals("Драматург", us1.getPosition());



        // Проверка DocUser

        DocUser first = us1.getDocUser();

        long docCode = first.getDoc().getId();
        String docName = first.getDoc().getName();
        long docNumber = first.getDocNumber();


        Assert.assertEquals(7L, docCode); // был 21
        Assert.assertEquals("Военный билет", docName); // был Паспорт гражданина......
        // было 216846512154L
        Assert.assertEquals(12123165115165165L, docNumber);


        // Проверка изменений в docUser (уже непосредственно из docUserDAO)
        DocUserView docUserView2  = new DocUserView();
        docUserView2.userId = 19L;
        docUserView2.docId = 7;

        DocUser docUser2 = (DocUser) docUserDAO.loadByParams( docUserView2);
        long num = docUser2.getDocNumber();

        Assert.assertEquals(12123165115165165L, num);
    }


    /**
     * loadByParams
     */
    @Test
    public void loadByParams(){
        User us = new User();

        Office office = new Office();
        office.setId(8L);

        us.setOffice(office);

        us.setFirstName("Никита");
        us.setLastName("Селезнёв");
        us.setMiddleName("Алек");

        List<User> us1 = userDAO.loadByParams(us);
        Assert.assertEquals("Программист", us1.get(0).getPosition());
    }

    /**
     * save
     */
    @Test
    public void save(){
        User us = new User();

        Office office = officeDAO.loadById(4L);
        us.setOffice(office);

        us.setFirstName("Евгений");
        us.setMiddleName("Анатольевич");
        us.setLastName("Каплан");

        us.setPosition("Агроном");

        String d1 = "21.10.2012";
        String d2 = "07.10.2009";
        String d3 = "12.12.2007";
        Date regDt = null;
        Date docDt = null;
        Date docDt2 = null;
        try {
            regDt = format.parse(d1);
            docDt = format.parse(d2);
            docDt2 = format.parse(d3);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        us.setRegistrationDate(regDt);

        // Загрузка документов
        // первый документ
        DocUser docUser = new DocUser();
        docUser.setDocDate(docDt);
        docUser.setDocNumber(12123165115165165L);

        Doc doc = docDAO.loadById(21); // справочник документов, берём из базы
        docUser.setDoc(doc);

        us.setDocUser(docUser);

        // Загрузка citizenship
        Citizenship citizen = CitizDAO.loadByCode(398L);
        us.setCitizenship(citizen);

        userDAO.save(us);

        Assert.assertEquals(INSERT_COUNT_FROM_DB, userDAO.all().size());

    }



}
