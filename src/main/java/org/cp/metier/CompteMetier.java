package org.cp.metier;

import org.cp.entities.Compte;

public interface CompteMetier {
   public Compte addCompte(Compte c);
   public Compte consultCompte(String codeCpte);
}
