package ma.GymPro.services;

import ma.GymPro.beans.Abonnement;
import ma.GymPro.beans.Profil;
import ma.GymPro.beans.Service;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserServices userServices;

    @Test
    public void should_consult_profil(){
        Profil profil=new Profil();
        profil.setId(1L);
        when(userServices.getProfil("name")).thenReturn(profil);
        assertEquals(profil,userServices.getProfil("name"));
    }
    @Test
    public void should_update_profil(){
        Profil profil=new Profil();
        profil.setId(1L);
        doNothing().when(userServices).updateProfile(profil,"email");
        userServices.updateProfile(profil,"email");
        verify(userServices, times(1)).updateProfile(profil,"email");
    }

    @Test
    public void should_update_password(){
        doNothing().when(userServices).updateMotDePasse("newPASS","email");
        userServices.updateMotDePasse("newPASS","email");
        verify(userServices, times(1)).updateMotDePasse("newPASS","email");
    }
}
