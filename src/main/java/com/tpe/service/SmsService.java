package com.tpe.service;

import com.tpe.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.Random;

@Component("smsservice")
public class SmsService implements MessageService {

    @Autowired
    private Random random;

    @PostConstruct // SmsService bu classin constructor'i kullanildiktan hemen sonra calisir
    public void init() {
        System.out.println("smsService objesi uretiliyor");
    }

    @PreDestroy // class objesinin yok edilmesinden hemen once bu method calisir (javax.annotation-api / pom.xml)
    public void destroy() {
        System.out.println("smsService objesi sonlandiriliyor");
    }

    @Override
    public void sendMessage(Message message) {
        System.out.println("Ben bir SMS servisiyim. Mesajınız:" + message.getMessage());
        System.out.println("random: " + random.nextInt(101));
    }

    @Override
    public void saveMessage(Message message) {
    }

    @Value("${app.email}")
    private String email;
    @Value("${app.phone}")
    private String phone;


    public void printContact() {
        System.out.println("email: " + email);
        System.out.println("phone: " + phone);
    }

    @Autowired
    private Properties properties;

    public void printProperties() {
        System.out.println("contact email :" + properties.get("mymail"));
        System.out.println("properties mail: " + properties.get("mymail")); // ayni
        System.out.println("Java_Home: " + properties.get("myjavahome"));
    }
}
