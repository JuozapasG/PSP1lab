package com.example.bibliotekos_panaudojimas_3dalis;

import com.example.bibliotekos_panaudojimas_3dalis.services.UserService;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // GAUTOS VALIDATORIUS IMPLEMENTACIJOS YRA SUNKIAI NAUDOJAMOS, REIKIA PATEIKTI LABAI SPECIFINIUS DUOMENIS, NEGALIMA PRIDETI CUSTOM TELEFONO DUOMENU


}
