package ma.GymPro.repositories;

import ma.GymPro.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    public Coupon findByReference(String reference);


}
