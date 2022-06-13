package org.cp.metier;

import java.util.List;

import org.cp.entities.Client;
import org.cp.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientMetierImpl implements ClientMetier{
    
	@Autowired
	private ClientRepository clientRepository; 
	
	@Override
	public Client addClient(Client c) {
		// TODO Auto-generated method stub
		return clientRepository.save(c);
	}

	@Override
	public List<Client> listClient() {
		// TODO Auto-generated method stub
		return clientRepository.findAll();
	}

	@Override
	public List<Client> searchByMC(String mc) {
		// TODO Auto-generated method stub
		return clientRepository.findByNomIgnoreCaseContaining(mc);
	}

}
