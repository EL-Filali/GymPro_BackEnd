package ma.GymPro.services;
import ma.GymPro.beans.Achat;
import ma.GymPro.repositories.AchatRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class AdminServicesTests {

    @Mock
    AchatRepository achatRepository;

    @InjectMocks
    AdminServices adminServices;

    AutoCloseable closeable;

    @BeforeEach
    public void init(){
        closeable= MockitoAnnotations.openMocks(this);

    }
    @AfterEach
    public void close() throws Exception {
        closeable.close();
    }
    @Test
    public void getFactureTest() throws Exception {
        Achat achat = new Achat(5l);
        Optional<Achat> achatOptional =  Optional.of(achat);
        System.out.println(achatOptional.get().getId());
        when(achatRepository.findById(5l)).thenReturn(achatOptional);
        Achat achat1=adminServices.getFacture(5l);
        assertEquals(achat1.getId(),5l);

    }




}
