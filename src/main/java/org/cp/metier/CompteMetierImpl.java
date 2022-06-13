package org.cp.metier;

import org.cp.entities.Compte;
import org.cp.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CompteMetierImpl implements CompteMetier{
    
	@Autowired
	private CompteRepository compteRepository; 
	@Override
	public Compte addCompte(Compte c) {
		// TODO Auto-generated method stub
		return compteRepository.save(c);
	}

	@Override
	public Compte consultCompte(String codeCpte) {
		Compte cp = compteRepository.findById(codeCpte).get();
		if (cp==null) throw new RuntimeException("Compte innexistant");
		return cp;
	}

}
