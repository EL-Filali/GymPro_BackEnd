package ma.GymPro.services;

import ma.GymPro.beans.Client;
import ma.GymPro.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ResponsableServices {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;



    public Client saveClient(Client client ){
        client.setPassword(bCryptPasswordEncoder.encode(client.getProfil().getCin()+"@2021"));
        return clientRepository.save(client);
    }
}
