package ma.GymPro.services;

import ma.GymPro.beans.Achat;
import ma.GymPro.beans.Coupon;
import ma.GymPro.beans.Cours;
import ma.GymPro.dto.cart.CarteDTOResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientServices clientServices;

    @Test
    public void should_get_client_factures(){
        Page<Achat> factures = Mockito.mock(Page.class);
        when(clientServices.getFactures(0,10,"prix","email")).thenReturn(factures);
        assertEquals(factures,clientServices.getFactures(0,10,"prix","email"));
    }

    @Test
    public void should_get_client_facuture() throws Exception {
        Achat achat = new Achat();
        achat.setId(1L);
        when(clientServices.getFacture(1L,"email")).thenReturn(achat);
        assertEquals(achat,clientServices.getFacture(1L,"email"));
    }

    @Test
    public void should_get_client_cart() throws Exception {
        CarteDTOResponse carteDTOResponse=new CarteDTOResponse();
        carteDTOResponse.setAchatDetails(new ArrayList<>());
        when(clientServices.getCart("email")).thenReturn(carteDTOResponse);
        assertEquals(carteDTOResponse,clientServices.getCart("email"));
    }

    @Test
    public void should_check_coupon() throws Exception {
        Coupon coupon=new Coupon();
        coupon.setId(1L);
        when(clientServices.checkCoupon("reference")).thenReturn(coupon);
        assertEquals(coupon,clientServices.checkCoupon("reference"));
    }
}
