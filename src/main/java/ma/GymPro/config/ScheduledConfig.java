package ma.GymPro.config;

import ma.GymPro.beans.Suspendu;
import ma.GymPro.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledConfig {
    @Autowired
    ClientRepository clientRepository;

    @Scheduled(cron = "0 01 00 * * ?")
    public void scheduleTaskUsingCronExpression() {
        clientRepository.VerificationAbonnement(new Suspendu());


    }
}
