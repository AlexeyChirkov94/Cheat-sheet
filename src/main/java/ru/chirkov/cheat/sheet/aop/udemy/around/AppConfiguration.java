package ru.chirkov.cheat.sheet.aop.udemy.around;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("ru.chirkov.projects.aop.udemy.around")
@EnableAspectJAutoProxy
public class AppConfiguration {

}
