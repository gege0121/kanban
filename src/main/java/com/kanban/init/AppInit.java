package com.kanban.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import javax.servlet.ServletContext;

@SpringBootApplication(scanBasePackages = "com.kanban")
@ServletComponentScan(basePackages = {"com.kanban.filter"})
public class AppInit extends SpringBootServletInitializer {

    @Autowired
    private ServletContext servletContext;

    public static void main(String[] args) {
        ConfigurableApplicationContext app = SpringApplication.run(AppInit.class,args);
        ServletContext servletContext = app.getBean(ServletContext.class);
        servletContext.setInitParameter("contextConfigLocation", "<NONE>");
    }
}

