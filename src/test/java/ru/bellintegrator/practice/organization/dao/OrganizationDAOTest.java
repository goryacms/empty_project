package ru.bellintegrator.practice.organization.dao;

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
import ru.bellintegrator.practice.organization.model.Organization;


import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OrganizationDAOTest {

    @Autowired
    private OrganizationDAO organizationDAO;

    private static final int DEFAULT_COUNT_FROM_DB = 4;
    private static final int INSERT_COUNT_FROM_DB  = 5;
    private static final int DELETE_COUNT_FROM_DB  = 3;

    /**
     * update
     */
    @Test
    public void update() {
        Organization organization = new Organization();
        organization.setAddress("ул. Ворошилова");
        organization.setInn(545646546546545645L);
        organization.setKpp(545600000000000645L);
        organization.setActive(false);
        organization.setName("ОАО 'ЗИФ плюс'");
        organization.setId(3L);

        organizationDAO.update(organization);

        Organization organization1 = organizationDAO.loadById(3L);


        Assert.assertEquals("ОАО 'ЗИФ плюс'", organization1.getName());
    }

    /**
     * Проверка delete
     */
    @Test
    public void delete() {
        organizationDAO.delete(2L);

        Assert.assertEquals(DELETE_COUNT_FROM_DB, organizationDAO.all().size());

    }

    /**
     * Проверка all()
     */
    @Test
    public void all(){
        List<Organization> orgs = organizationDAO.all();
        Assert.assertEquals(DEFAULT_COUNT_FROM_DB, orgs.size());
    }

    /**
     * Проверка метода loadById
     */
    @Test
    public void loadById(){
        Organization org = organizationDAO.loadById(3L);
        Assert.assertEquals("1C:Битрикс", org.getName());

        Assert.assertEquals((Long) 11111111111111114L, org.getInn());
    }

    /**
     * save
     */
    @Test
    public void save(){
        Organization organization = new Organization();
        organization.setAddress("ул.Ворошилова");
        organization.setInn(4546546546548748947L);
        organization.setKpp(4546000000548748947L);
        organization.setActive(false);
        organization.setName("ОАО 'ЗИФ'");

        organizationDAO.save(organization);

        Assert.assertEquals(INSERT_COUNT_FROM_DB, organizationDAO.all().size());
    }

    /**
     * loadByParams
     */
    @Test
    public void loadByParams(){
        Organization organization = new Organization();
        organization.setName("икс");
        organization.setActive(false);

        List<Organization> orgs = organizationDAO.loadByParams(organization);
        Assert.assertEquals(2, orgs.size());
    }


}