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
import ru.bellintegrator.practice.guides.model.DocUser;
import ru.bellintegrator.practice.organization.dao.OrganizationDAO;
import ru.bellintegrator.practice.organization.model.Organization;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OrganizationDAOTest {

    @Autowired
    private OrganizationDAO organizationDAO;

    /**
     * update
     */
    @Test
    public void update() {
        Organization organization = organizationDAO.loadById((long) 3);
        organization.setName("ОАО 'ЗИФ плюс'");
        organizationDAO.update(organization);

        Assert.assertEquals("ОАО 'ЗИФ плюс'", organization.getName());
    }

    /**
     * Проверка delete
     */
    @Test
    public void delete() {
        organizationDAO.delete((long) 2);

        Assert.assertEquals(2, organizationDAO.all().size());

    }

    /**
     * Проверка all()
     */
    @Test
    public void all(){
        List<Organization> orgs = organizationDAO.all();
        Assert.assertEquals(3, orgs.size());
    }

    /**
     * Проверка метода loadById
     */
    @Test
    public void loadById(){
        Organization org = organizationDAO.loadById((long) 3);
        Assert.assertEquals("1C:Битрикс", org.getName());

        Assert.assertEquals((Long) 11111111111111114L, org.getInn());
    }

    /**
     * save
     */
    public void save(){
        Organization organization = new Organization();
        organization.setAddress("ул.Ворошилова");
        organization.setInn(4546546546548748947L);
        organization.setKpp(4546000000548748947L);
        organization.setActive(false);
        organization.setName("ОАО 'ЗИФ'");

        organizationDAO.save(organization);

        Assert.assertEquals(4, organizationDAO.all().size());
    }

}