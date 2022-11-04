package ru.chirkov.cheat.sheet.aop.udemy.before_MergingSeparatePointCut;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("ru.chirkov.projects.aop.udemy.before_MergingSeparatePointCut")
@EnableAspectJAutoProxy
public class AppConfiguration {

}
