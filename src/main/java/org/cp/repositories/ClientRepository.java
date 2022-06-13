package org.cp.repositories;

import java.util.List;

import org.cp.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
	@Query("select c from Client c where c.nom like %:mc%")
	public List<Client> searchByMC(@Param("mc") String mc);
	public List<Client> findByNomIgnoreCaseContaining(String mc);
}
