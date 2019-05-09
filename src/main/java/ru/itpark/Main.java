package ru.itpark;

import lombok.var;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;
import org.springframework.core.io.ClassPathResource;
import ru.itpark.annotationConf.AnnotationPostService;
import ru.itpark.annotationConf.AnnotationRequestClient;
import ru.itpark.groovy.GroovyPostService;
import ru.itpark.groovy.GroovyRequestClient;
import ru.itpark.java.JavaConfiguration;
import ru.itpark.java.JavaPostService;
import ru.itpark.java.JavaRequestClient;
import ru.itpark.programmatic.ProgrammaticCachedAnnotationBPP;
import ru.itpark.programmatic.ProgrammaticPostService;
import ru.itpark.programmatic.ProgrammaticRequestClient;
import ru.itpark.util.CustomPropertyPlaceholderConfigurer;
import ru.itpark.xml.XmlPostService;
import ru.itpark.xml.XmlRequestClient;

public class Main {
    public static void main(String[] args) {

        {
            System.out.println("-----------------XML--------------------");
            ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
            XmlPostService xmlPostService = ctx.getBean("service", XmlPostService.class);
            XmlRequestClient xmlClient = xmlPostService.getClient();
            System.out.println(xmlClient.getPost("3"));
            System.out.println(xmlClient.getPost("3"));
            System.out.println(xmlClient.getPost("5"));
            System.out.println(xmlClient.getPost("5"));
            System.out.println(xmlClient.getPost("5"));
            System.out.println(xmlClient.getPost("8"));
            xmlClient.printURL();
            System.out.println();
            System.out.println();
        }

        {
            System.out.println("-----------------Annotation--------------------");
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext("ru.itpark.annotationConf");
            AnnotationRequestClient annotationRequestClient = ctx.getBean("postService", AnnotationPostService.class).getClient();
            System.out.println(annotationRequestClient.getPost("3"));
            System.out.println(annotationRequestClient.getPost("3"));
            System.out.println(annotationRequestClient.getPost("5"));
            System.out.println(annotationRequestClient.getPost("5"));
            System.out.println(annotationRequestClient.getPost("5"));
            System.out.println(annotationRequestClient.getPost("8"));
            annotationRequestClient.printURL();
            System.out.println();
            System.out.println();
        }

        {
            System.out.println("-----------------Programmatic--------------------");
            var ctx = new AnnotationConfigApplicationContext();
            ctx.registerBean(CustomPropertyPlaceholderConfigurer.class, () -> {
                var configurer = new CustomPropertyPlaceholderConfigurer();
                configurer.setLocations("application.json");
                return configurer;
            });
            ctx.registerBean("postService", ProgrammaticPostService.class);
            ctx.registerBean("requestClient", ProgrammaticRequestClient.class);
            ctx.registerBean("cachedAnnotationBPP", ProgrammaticCachedAnnotationBPP.class);
            ctx.refresh();
            ProgrammaticPostService programmaticPostService = (ProgrammaticPostService) ctx.getBean("postService");
            ProgrammaticRequestClient programmaticRequestClient = programmaticPostService.getClient();
            System.out.println(programmaticRequestClient.getPost("3"));
            System.out.println(programmaticRequestClient.getPost("3"));
            System.out.println(programmaticRequestClient.getPost("5"));
            System.out.println(programmaticRequestClient.getPost("5"));
            System.out.println(programmaticRequestClient.getPost("5"));
            System.out.println(programmaticRequestClient.getPost("8"));
            programmaticRequestClient.printURL();
            System.out.println();
            System.out.println();
        }


        {
            System.out.println("-----------------Java--------------------");
            var ctx = new AnnotationConfigApplicationContext(JavaConfiguration.class);
            JavaPostService javaPostService = ctx.getBean("postService", JavaPostService.class);
            JavaRequestClient javaRequestClient = javaPostService.getClient();
            System.out.println(javaRequestClient.getPost("3"));
            System.out.println(javaRequestClient.getPost("3"));
            System.out.println(javaRequestClient.getPost("5"));
            System.out.println(javaRequestClient.getPost("5"));
            System.out.println(javaRequestClient.getPost("5"));
            System.out.println(javaRequestClient.getPost("8"));
            javaRequestClient.printURL();
            System.out.println();
            System.out.println();
        }


        {
            System.out.println("-----------------Groovy--------------------");
            var ctx = new GenericGroovyApplicationContext("beans.groovy");
            GroovyPostService groovyPostService = ctx.getBean("postService", GroovyPostService.class);
            GroovyRequestClient groovyRequestClient = groovyPostService.getClient();
            System.out.println(groovyRequestClient.getPost("3"));
            System.out.println(groovyRequestClient.getPost("3"));
            System.out.println(groovyRequestClient.getPost("5"));
            System.out.println(groovyRequestClient.getPost("5"));
            System.out.println(groovyRequestClient.getPost("5"));
            System.out.println(groovyRequestClient.getPost("8"));
            groovyRequestClient.printURL();
        }
    }
}
