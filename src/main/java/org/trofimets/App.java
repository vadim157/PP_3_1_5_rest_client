package org.trofimets;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.trofimets.config.Config;
import org.trofimets.entity.User;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

        User user = new User(3L,"James","Brown",(byte) 30);
        User userUpdate = new User(3L,"Thomas","Shelby",(byte) 30);


        Communication communication = context.getBean(Communication.class);

        String cookie = communication.getAllUsers();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookie);

        communication.saveUser(headers,user);
        communication.updateUser(headers,userUpdate);
        System.out.println(communication.delete(headers,3L));

    }


}
