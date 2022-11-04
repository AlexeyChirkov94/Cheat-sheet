package ru.chirkov.cheat.sheet.aop.spring4forprofessionals.annotations;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("ru.chirkov.projects.aop.annotations.spring4forprofessionals.one")
@EnableAspectJAutoProxy
public class Config {
}
