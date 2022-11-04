package ru.chirkov.cheat.sheet.aop.udemy.around_exceptions;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("ru.chirkov.projects.aop.udemy.around_exceptions")
@EnableAspectJAutoProxy
public class AppConfiguration {

}
