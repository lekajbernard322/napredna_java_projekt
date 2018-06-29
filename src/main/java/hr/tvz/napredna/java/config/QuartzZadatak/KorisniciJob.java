package hr.tvz.napredna.java.config.QuartzZadatak;


import java.util.Date;
import java.util.stream.Collectors;

import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;

public class KorisniciJob extends QuartzJobBean {

    @Autowired
    SessionRegistry sessionRegistry;


    public void executeInternal(JobExecutionContext jec){

        String usernames = sessionRegistry
        		.getAllPrincipals()
        		.stream()
        		.map(p -> ((User) p).getUsername())
        		.collect(Collectors.joining(","));
        System.out.println("Trenutno prijavljeni korisnici(" 
        		+ new Date().toString() + "): " + usernames);
    }


}
