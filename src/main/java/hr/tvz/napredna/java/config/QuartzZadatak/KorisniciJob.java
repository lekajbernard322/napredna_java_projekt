package hr.tvz.napredna.java.config.QuartzZadatak;


import hr.tvz.napredna.java.model.Korisnik;
import hr.tvz.napredna.java.repository.ZadatakRepository;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.User;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class KorisniciJob extends QuartzJobBean {

    @Autowired
    SessionRegistry sessionRegistry;


    public void executeInternal(JobExecutionContext jec){

        List<Object> principals = sessionRegistry.getAllPrincipals();

        List<String> usersNamesList = new ArrayList<String>();

        System.out.print("Trenutno prijavljeni korisnici (" + new Date().toString() + "): ");
        Iterator korisnici = principals.iterator();
        while( korisnici.hasNext() ){
            Object principal = korisnici.next();
            if (principal instanceof User) {
                usersNamesList.add(((User) principal).getUsername());
                System.out.print(((User) principal).getUsername());
            }
            if( korisnici.hasNext() )
                System.out.print(", ");
        }
        System.out.println();

    }


}
