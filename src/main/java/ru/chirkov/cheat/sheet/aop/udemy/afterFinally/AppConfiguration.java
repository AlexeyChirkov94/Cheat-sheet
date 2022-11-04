package ru.chirkov.cheat.sheet.aop.udemy.afterFinally;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("ru.chirkov.projects.aop.udemy.afterFinally")
@EnableAspectJAutoProxy
public class AppConfiguration {

}
