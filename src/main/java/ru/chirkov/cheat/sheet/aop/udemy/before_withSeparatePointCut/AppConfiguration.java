package ru.chirkov.cheat.sheet.aop.udemy.before_withSeparatePointCut;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("ru.chirkov.projects.aop.udemy.before_withSeparatePointCut")
@EnableAspectJAutoProxy
public class AppConfiguration {

}
