package com.groupfour.chatapp.chatapp.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface DTO {

    //    This class is to convert DTO to entity for errors in controller class.
//    See https://auth0.com/blog/automatically-mapping-dto-to-entity-on-spring-boot-apis/
//    And the repo https://github.com/auth0-blog/questionmarks-server/blob/master/src/main/java/com/questionmarks/controller/ExamRestController.java



    Class value();
}