package ru.bellintegrator.practice.office.dao;

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
import ru.bellintegrator.practice.office.model.Office;
import ru.bellintegrator.practice.organization.dao.OrganizationDAO;
import ru.bellintegrator.practice.organization.model.Organization;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class OfficeDAOTest {
    @Autowired
    private OfficeDAO officeDAO;

    @Autowired
    private OrganizationDAO organizationDAO;

    private static final int DEFAULT_COUNT_FROM_DB = 8;
    private static final int INSERT_COUNT_FROM_DB  = 9;
    private static final int DELETE_COUNT_FROM_DB  = 7;

    /**
     * update
     */
    @Test
    public void update() {
        Office office = new Office();
        office.setAddress("ул. Глазунова 7");
        office.setActive(false);
        office.setName("Отдел тех контроля");
        office.setId(3L);

        officeDAO.update(office);

        Office office1 = officeDAO.loadById(3L);

        Assert.assertEquals("ул. Глазунова 7", office1.getAddress());
    }

    /**
     * Проверка delete
     */
    @Test
    public void delete() {
        officeDAO.delete(2L);

        Assert.assertEquals(DELETE_COUNT_FROM_DB, officeDAO.all().size());
    }

    /**
     * Проверка all()
     */
    @Test
    public void all(){
        List<Office> offices = officeDAO.all();
        Assert.assertEquals(DEFAULT_COUNT_FROM_DB, offices.size());
    }

    /**
     * Проверка метода loadById
     */
    @Test
    public void loadById(){
        Office office = officeDAO.loadById(7L);

        Assert.assertEquals("Отдел продаж", office.getName());
        Assert.assertEquals((Long) 11111111111111114L, office.getOrganization().getInn());
    }

    /**
     * save
     */
    @Test
    public void save(){
        Office office = new Office();
        office.setAddress("ул.Ворошилова");
        office.setActive(false);
        office.setName("Служба главного технолога");

        Organization org = organizationDAO.loadById(4L);
        office.setOrganization(org);

        officeDAO.save(office);

        Assert.assertEquals(INSERT_COUNT_FROM_DB, officeDAO.all().size());

        Office office1 = officeDAO.loadById(9L);
        Assert.assertEquals("Служба главного технолога", office1.getName());
    }

    /**
     * loadByParams
     */
    @Test
    public void loadByParams(){
        Office office = new Office();

        Organization organization = new Organization();
        organization.setId(3L);
        office.setOrganization(organization);

        office.setName("Отдел");

        office.setActive(true);

        List<Office> office2 = officeDAO.loadByParams(office);

        Assert.assertEquals(2, office2.size());
        Assert.assertEquals("ул. Куйбышева, 19", office2.get(0).getAddress());


    }
}
