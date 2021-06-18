package ma.GymPro.repositories;

import ma.GymPro.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon,Long> {
    public Coupon findByReference(String reference);

    @Modifying
    @Query("update  Coupon  c set c.expired=true  where  current_date >c.dateExpiration ")
    void verificationCoupon();
}
