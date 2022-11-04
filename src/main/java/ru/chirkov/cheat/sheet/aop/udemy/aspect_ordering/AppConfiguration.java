package ru.chirkov.cheat.sheet.aop.udemy.aspect_ordering;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("ru.chirkov.projects.aop.udemy.aspect_ordering")
@EnableAspectJAutoProxy
public class AppConfiguration {

}
