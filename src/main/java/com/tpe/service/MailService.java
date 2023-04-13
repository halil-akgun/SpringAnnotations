package com.tpe.service;

import com.tpe.domain.Message;
import com.tpe.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component//bu classtan springin obje üretmesini istiyoruz.
@Scope("prototype")
public class MailService implements MessageService {

//    @Autowired // asagidaki field'i class'a enjekte ediyor  -  Field injection
//    @Qualifier("fileRepository")
//    private Repo repo;

//    private Repo repo;
//    @Autowired  // Setter injection
//    @Qualifier("fileRepository")
//    public void setRepo(Repo repo) {
//        this.repo = repo;
//    }

    private Repo repo;

    @Autowired // Constructor injection - Recommended(tek constrctr varsa @Autowired yazmak zorunlu degil)
    public MailService(@Qualifier("fileRepository") Repo repo) {
        this.repo = repo;
    }

    @PostConstruct // MailService bu classin constructor'i kullanildiktan hemen sonra calisir
    public void init() {
        System.out.println("MailService objesi uretiliyor");
    }

    @PreDestroy // class objesinin yok edilmesinden hemen once bu method calisir
    public void destroy() {
        System.out.println("MailService objesi sonlandiriliyor");
    }

    @Override
    public void sendMessage(Message message) {
        System.out.println("Ben bir mail servisiyim. Mesajınız:" + message.getMessage());
    }

    @Override
    public void saveMessage(Message message) {
        // reponun metodu icin objesi lazim
        repo.save(message);
    }
}
