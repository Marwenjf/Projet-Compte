package org.cp.metier;

import java.util.List;

import org.cp.entities.Client;

public interface ClientMetier {
  public Client addClient(Client c);
  public List<Client> listClient();
  public List<Client> searchByMC(String mc);
}
