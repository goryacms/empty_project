package ru.bellintegrator.practice.organization.controller;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.bellintegrator.practice.organization.view.OrganizationView;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
/**
 * Интеграционный тест
 */
public class OrganizationControllerIT {
    TestRestTemplate restTemplate = new TestRestTemplate();

    private final String ORG_ID_URL =  "http://localhost:8888/api/organization/";

    @Test
    public void testOrganizationById() throws Exception{
        long id = 1L;
        ResponseEntity<OrganizationView> resp = restTemplate.getForEntity(ORG_ID_URL + id, OrganizationView.class);

        assertThat(resp.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
