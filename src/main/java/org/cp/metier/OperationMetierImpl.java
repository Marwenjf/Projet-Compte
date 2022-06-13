package org.cp.metier;

import java.util.Date;

import javax.transaction.Transactional;

import org.cp.entities.Compte;
import org.cp.entities.Operation;
import org.cp.entities.Retrait;
import org.cp.entities.Versement;
import org.cp.repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class OperationMetierImpl implements OperationMetier {
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private CompteMetier compteMetier;

	@Transactional
	@Override
	public void verser(String codeCpte, double montant) {
		Compte cp = compteMetier.consultCompte(codeCpte);
		Operation op = new Versement(new Date(), montant, cp);
		operationRepository.save(op);
		cp.setSolde(cp.getSolde() + montant);

	}

	@Transactional
	@Override
	public void virement(String cp1, String cp2, double montant) {
		retirer(cp1, montant);
		verser(cp2, montant);
	}

	@Transactional
	@Override
	public void retirer(String codeCpte, double montant) {
		Compte cp = compteMetier.consultCompte(codeCpte);
		if(cp.getSolde() < montant) throw new RuntimeException("Solde insuffisant");
		Operation op = new Retrait(new Date(), montant, cp);
		operationRepository.save(op);
		cp.setSolde(cp.getSolde() - montant);

	}

	@Override
	public Page<Operation> listOperations(String codeCpte, int page, int size) {
		// TODO Auto-generated method stub
		return operationRepository.listOperation(codeCpte, PageRequest.of(page, size,Sort.by("dateOperation")));
	}

}
