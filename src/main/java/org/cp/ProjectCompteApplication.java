package org.cp;

import java.util.Date;
import java.util.List;

import org.cp.entities.Client;
import org.cp.entities.Compte;
import org.cp.entities.CompteCourant;
import org.cp.entities.CompteEpargne;
import org.cp.entities.Operation;
import org.cp.metier.ClientMetier;
import org.cp.metier.CompteMetier;
import org.cp.metier.OperationMetier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;

@SpringBootApplication
public class ProjectCompteApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(ProjectCompteApplication.class, args);
		ClientMetier clientMetier = ctx.getBean(ClientMetier.class);
		clientMetier.addClient(new Client("A"));
		clientMetier.addClient(new Client("B"));
		clientMetier.addClient(new Client("BA"));
		
		System.out.println("-----------------------------");
		List<Client> clients = clientMetier.listClient();
		clients.forEach(c->{
			System.out.println(c.getNom());
		});
		System.out.println("-----------------------------");
		List<Client> clients2 = clientMetier.searchByMC("a");
		clients2.forEach(c->System.out.println(c.getNom()));
		System.out.println("--------------Compte---------------");
		CompteMetier compteMetier= ctx.getBean(CompteMetier.class);
		compteMetier.addCompte(new CompteCourant("CC1", new Date(), 9000, new Client(1L), 8000));
		compteMetier.addCompte(new CompteCourant("CC2", new Date(), 9000, new Client(1L), 8000));
		compteMetier.addCompte(new CompteEpargne("CE1", new Date(), 9000,new Client(1L) , 5.5));
	    Compte compte = compteMetier.consultCompte("CC1");
		System.out.println("Solde="+compte.getSolde());
		System.out.println("Date:"+compte.getDateCreation());
		System.out.println("--------------Operation---------------");
		OperationMetier operationMetier = ctx.getBean(OperationMetier.class);
		operationMetier.verser("CC1", 90000);
		operationMetier.retirer("CC1", 6000);
		operationMetier.verser("CC1", 10000);
		operationMetier.retirer("CC1", 7000);
		operationMetier.verser("CC1", 12000);
		operationMetier.retirer("CC1", 8000);
		operationMetier.virement("CC1", "CE1", 7000);
		
		Page<Operation> ops = operationMetier.listOperations("CC1", 0, 3);
		for (Operation o : ops.getContent()) {
			System.out.println(o.getNumeroOperation());
			System.out.println(o.getMontant());
			System.out.println(o.getDateOperation());
		}
		
	}

}
