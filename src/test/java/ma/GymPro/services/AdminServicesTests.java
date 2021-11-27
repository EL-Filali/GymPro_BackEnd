package ma.GymPro.services;

import ma.GymPro.beans.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdminServicesTests {


    @Mock
    private AdminServices adminServices;


    @Test
    public void should_get_facutures(){
        Page<Achat> factures = Mockito.mock(Page.class);
        when(adminServices.getFactures(0,10,"prix")).thenReturn(factures);
        assertEquals(factures,adminServices.getFactures(0,10,"prix"));
    }

    @Test
    public void should_get_facuture() throws Exception {
        Achat achat = new Achat();
        achat.setId(1L);
        when(adminServices.getFacture(1L)).thenReturn(achat);
        assertEquals(achat,adminServices.getFacture(1L));
    }


    @Test
    public void should_get_responsable_by_id() throws Exception {
        Employe responsable=new Responsable();
        responsable.setId(1L);
        when(adminServices.getEmployeById(1L)).thenReturn(responsable);
        assertEquals(responsable,adminServices.getEmployeById(1L));
    }

    @Test
    public void should_get_coach_by_id() throws Exception{
        Employe caoch=new Coach();
        caoch.setId(1L);
        when(adminServices.getEmployeById(1L)).thenReturn(caoch);
        assertEquals(caoch,adminServices.getEmployeById(1L));
    }

    @Test
    public void should_get_liste_employes(){
        Page<Employe> employees = Mockito.mock(Page.class);
        when(adminServices.getAllEmployes(0,10,"salaire")).thenReturn(employees);
        assertEquals(employees,adminServices.getAllEmployes(0,10,"salaire"));
    }

    @Test
    public void should_register_coach(){
        Coach coach=new Coach();
        coach.setId(1L);
        when(adminServices.addEmploye(coach)).thenReturn(coach);
        assertEquals(coach,adminServices.addEmploye(coach));
    }





}
