package com.webflux.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class HelloWorldController {

  @GetMapping(path = "/helloSync")
  public String syncHello() throws InterruptedException {
    Thread.sleep(3000);
    return "hello world";
  }

  @GetMapping(path = "/helloAsync")
  public Mono<String> asyncHello(){
    return Mono.defer(() -> {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return Mono.just("hello world");
    }).subscribeOn(Schedulers.elastic());
  }
}
