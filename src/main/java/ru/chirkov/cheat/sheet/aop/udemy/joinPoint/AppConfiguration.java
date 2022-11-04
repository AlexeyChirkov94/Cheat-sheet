package ru.chirkov.cheat.sheet.aop.udemy.joinPoint;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("ru.chirkov.projects.aop.udemy.joinPoint")
@EnableAspectJAutoProxy
public class AppConfiguration {

}
