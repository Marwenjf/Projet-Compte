package org.cp.metier;

import org.cp.entities.Operation;
import org.springframework.data.domain.Page;

public interface OperationMetier {
   public void verser(String codeCpte,double montant);
   public void virement(String cp1,String cp2,double montant);
   public void retirer(String codeCpte,double montant);
   public Page<Operation> listOperations(String codeCpte,int page,int size); 
}
