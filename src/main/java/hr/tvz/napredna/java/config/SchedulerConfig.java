package hr.tvz.napredna.java.config;

import hr.tvz.napredna.java.config.QuartzZadatak.KorisniciJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {
    @Bean
    public JobDetail objavaJobDetail() {
        return JobBuilder.newJob(KorisniciJob.class)
                .withIdentity("objavaJob")
                .storeDurably()
                .build();
    }
    @Bean
    public Trigger objavaJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();
        return TriggerBuilder.newTrigger()
                .forJob(objavaJobDetail())
                .withIdentity("objavaTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}

