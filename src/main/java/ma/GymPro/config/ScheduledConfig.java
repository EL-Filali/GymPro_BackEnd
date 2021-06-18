package ma.GymPro.config;

import ma.GymPro.services.ScheduledServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledConfig {
    @Autowired
    ScheduledServices scheduledServices;

    @Scheduled(cron = "0 01 00 * * ?")
    public void verificationAbonnement() {
        scheduledServices.verificationExpirationAbonnement();
    }
    @Scheduled(cron = "0 01 00 * * ?")
    public void verificationCoupon() {
        scheduledServices.verificationExpirationCoupon();
    }
}
