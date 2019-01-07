package com.webflux.demo.controller;

import org.mapstruct.factory.Mappers;

public class Mapper {
  public static final BeanMapper BEAN_MAPPER = Mappers.getMapper(BeanMapper.class);

  private Mapper() {
    // hide
  }
}
