package ma.GymPro.services;

import ma.GymPro.beans.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResponsableServicesTests {

    @Mock
    private ResponsableServices responsableServices;


    @Test
    public void should_register_client(){
        Client client=new Client();
        client.setId(1L);
        when(responsableServices.saveClient(client)).thenReturn(client);
        assertEquals(client,responsableServices.saveClient(client));
    }

    @Test
    public void should_save_abonnement(){
        Service abonnement=new Abonnement();
        doNothing().when(responsableServices).createAbonnement(abonnement);
        responsableServices.createAbonnement(abonnement);
        verify(responsableServices, times(1)).createAbonnement(abonnement);
    }

    @Test
    public void should_get_all_coupons(){
        Page<Coupon> coupons = Mockito.mock(Page.class);
        when(responsableServices.getAllCoupons(0,10,"remise")).thenReturn(coupons);
        assertEquals(coupons,responsableServices.getAllCoupons(0,10,"remise"));
    }
}
