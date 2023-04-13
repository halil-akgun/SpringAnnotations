package com.tpe.app;

import com.tpe.AppConfiguration;
import com.tpe.domain.Message;
import com.tpe.service.MailService;
import com.tpe.service.MessageService;
import com.tpe.service.SmsService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Random;

public class MyApplication {
    public static void main(String[] args) {

        Message message = new Message();
        message.setMessage("Spring ile uygulama geliştirmek HARİKA:)");

        //config classını oku
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        //config classındaki component scan ile tüm componentleri tarayacak
        //her component tan bir tane bean oluşturup, context'te hazırda bekletecek
        // bean istedigimizde icerisindeki gerekli bagimliliklari enjekte ederek verir


        // MessageService service=context.getBean(MailService.class);
        // MessageService service=context.getBean(MessageService.class);//Spring akıllı
        //service.sendMessage(message);//newleme yapmadık
        //spring container dan rica ettik, bize hazır getirdi. IoC

        // MessageService service=context.getBean(SmsService.class);
//        MessageService service=context.getBean("smsservice",MessageService.class);
//        service.sendMessage(message);

        //interface i implement eden  birden fazla component ile işaretlenmiş class varsa
        //hangisini alması gerektiğini belirtmemiz gerekir.


        // ************************************
//        MessageService service = context.getBean(MailService.class);
//        service.sendMessage(message);
//        service.saveMessage(message);
        // enjekte edilecek obje secenegi birden fazla ise Qualifier ile belirtilmeli

//        Random random = new Random(); // spring bizim icin olustursun
        Random random = context.getBean("random2", Random.class);
        System.out.println(random.nextInt(101));

//        MessageService service = context.getBean(MailService.class);
//        MessageService service2 = context.getBean(MailService.class);
//        System.out.println(service.hashCode());
//        System.out.println(service2.hashCode());
        // spring'de beanlerin default scope'u: singleton
        // singleton: tum uygulama icin tek bir bean olusturulur
        // prototype: her obje istendiginde yeni bir obje(bean) olusturulur

        SmsService service3 = context.getBean(SmsService.class);
        service3.sendMessage(message);
        service3.printContact();
        service3.printProperties();


        // tum uygulamadaki bean'lerin isimleri
//        String[] beanNames = context.getBeanDefinitionNames();
//        for (String name : beanNames) {
//            System.out.println(name);
//        }


        context.close();//contextten obje isteyemeyiz.
    }
}