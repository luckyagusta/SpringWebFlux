package com.webflux.demo.controller;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.webflux.demo.controller.constant.ApiPath;
import com.webflux.demo.controller.constant.ResponseCode;
import com.webflux.demo.entity.EmailPriority;
import com.webflux.demo.repository.EmailPriorityRepository;
import java.util.Collections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmailPriorityControllerTest {
  @Autowired
  WebTestClient webTestClient;

  private static String PRIORITY_ID = "EMAIL_PRIORITY-";
  private static String PRIORITY_NAME = "EMAIL-PRIORITY-NAME-";

  private static String NEW_PRIORITY_ID = "NEW_EMAIL_PRIORITY-";
  private static String NEW_PRIORITY_NAME = "NEW_EMAIL-PRIORITY-NAME-";

  private static String UPDATE_PRIORITY_ID = "UPDATE_EMAIL_PRIORITY-";
  private static String UPDATE_PRIORITY_NAME = "UPDATE_EMAIL-PRIORITY-NAME-";

  private static final String CODE = "$.code";
  private static final String MESSAGE = "$.message";
  private static final String DATA = "$.data";
  private static final String DATA_PRIORITY_NAME = "$..data[?(@.priorityName=='%s')]";
  @Autowired
  private EmailPriorityRepository emailPriorityRepository;

  @Before
  public void setUp(){
    PRIORITY_ID = PRIORITY_ID + System.currentTimeMillis();
    PRIORITY_NAME = PRIORITY_NAME + System.currentTimeMillis();

    NEW_PRIORITY_ID = NEW_PRIORITY_ID + System.currentTimeMillis();
    NEW_PRIORITY_NAME = NEW_PRIORITY_NAME + System.currentTimeMillis();

    UPDATE_PRIORITY_ID = UPDATE_PRIORITY_ID + System.currentTimeMillis();
    UPDATE_PRIORITY_NAME = UPDATE_PRIORITY_NAME + System.currentTimeMillis();

    emailPriorityRepository.insert(popolateExistEmailPriority()).block();
    emailPriorityRepository.insert(popolateUpdateEmailPriority()).block();

  }

  @After
  public void cleanUp(){

  }

  @Test
  public void emailPriority_create(){

    EmailPriority emailPriority = popolateNewEmailPriority();
    webTestClient.post().uri(ApiPath.EMAIL_PRIORITY)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.ALL)
        .body(Mono.just(emailPriority), EmailPriority.class)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath(CODE).isEqualTo(ResponseCode.SUCCESS.getCode())
        .jsonPath(MESSAGE).isEqualTo(ResponseCode.SUCCESS.getCode())
        .jsonPath(DATA).isEqualTo("Data has successfully saved.");
  }

  @Test
  public void emailPriority_createDuplicate(){
    EmailPriority emailPriority = popolateExistEmailPriority();
    webTestClient.post().uri(ApiPath.EMAIL_PRIORITY)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.ALL)
        .body(Mono.just(emailPriority), EmailPriority.class)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath(CODE).isEqualTo(ResponseCode.PRIORITY_NAME_EXIST.getCode())
        .jsonPath(MESSAGE).isEqualTo(ResponseCode.PRIORITY_NAME_EXIST.getCode());
  }
  @Test
  public void emailPriority_findOne(){
    webTestClient.get().uri(ApiPath.EMAIL_PRIORITY_DETAIL,
        Collections.singletonMap("priorityName", PRIORITY_NAME))
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath(CODE).isEqualTo(ResponseCode.SUCCESS.getCode())
        .jsonPath(MESSAGE).isEqualTo(ResponseCode.SUCCESS.getCode())
        .jsonPath(String.format(DATA_PRIORITY_NAME, PRIORITY_NAME))
        .isNotEmpty();
  }

  @Test
  public void emailPriority_update(){
    EmailPriority emailPriority = popolateUpdateEmailPriority();
    webTestClient.put()
        .uri(uriBuilder ->
            uriBuilder.path(ApiPath.EMAIL_PRIORITY)
                .queryParam("priorityName", UPDATE_PRIORITY_NAME)
                .build()
        )
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.ALL)
        .body(Mono.just(emailPriority), EmailPriority.class)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath(CODE).isEqualTo(ResponseCode.SUCCESS.getCode())
        .jsonPath(MESSAGE).isEqualTo(ResponseCode.SUCCESS.getCode())
        .jsonPath(DATA).isEqualTo("Data has successfully saved.");
  }

  @Test
  public void emailPriority_updateDuplicate(){
    EmailPriority emailPriority = popolateExistEmailPriority();
    webTestClient.put()
        .uri(uriBuilder ->
            uriBuilder.path(ApiPath.EMAIL_PRIORITY)
                .queryParam("priorityName", UPDATE_PRIORITY_NAME)
                .build()
        )
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.ALL)
        .body(Mono.just(emailPriority), EmailPriority.class)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath(CODE).isEqualTo(ResponseCode.DUPLICATE_DATA.getCode())
        .jsonPath(MESSAGE).isEqualTo(ResponseCode.DUPLICATE_DATA.getMessage());
  }


  /**
   * will used for updateConflict
   * @return
   */
  private EmailPriority popolateUpdateEmailPriority(){
    EmailPriority emailPriority = new EmailPriority();
    emailPriority.setId(UPDATE_PRIORITY_ID);
    emailPriority.setPriorityName(UPDATE_PRIORITY_NAME);
    emailPriority.setPriorityOrder(1);
    emailPriority.setSmtpHost("www.host.com");
    emailPriority.setSmtpPort("8080");
    emailPriority.setSmtpPassword("password");
    emailPriority.setSmtpUsername("username");
    return emailPriority;
  }

  /**
   * will used for create
   * @return
   */
  private EmailPriority popolateNewEmailPriority(){
    EmailPriority emailPriority = new EmailPriority();
    emailPriority.setId(NEW_PRIORITY_ID);
    emailPriority.setPriorityName(NEW_PRIORITY_NAME);
    emailPriority.setPriorityOrder(1);
    emailPriority.setSmtpHost("www.host.com");
    emailPriority.setSmtpPort("8080");
    emailPriority.setSmtpPassword("password");
    emailPriority.setSmtpUsername("username");
    return emailPriority;
  }

  /**
   * will used for get,delete
   * @return
   */
  private EmailPriority popolateExistEmailPriority(){
    EmailPriority emailPriority = new EmailPriority();
    emailPriority.setId(PRIORITY_ID);
    emailPriority.setPriorityName(PRIORITY_NAME);
    emailPriority.setPriorityOrder(1);
    emailPriority.setSmtpHost("www.host.com");
    emailPriority.setSmtpPort("8080");
    emailPriority.setSmtpPassword("password");
    emailPriority.setSmtpUsername("username");
    return emailPriority;
  }
}
