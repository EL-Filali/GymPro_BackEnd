package ma.GymPro.services;

import ma.GymPro.beans.Suspendu;
import ma.GymPro.repositories.ClientRepository;
import ma.GymPro.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduledServices {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CouponRepository couponRepository;

    public void verificationExpirationAbonnement(){
        clientRepository.VerificationAbonnement(new Suspendu());
    }
    public void verificationExpirationCoupon(){
        couponRepository.verificationCoupon();
    }
}
