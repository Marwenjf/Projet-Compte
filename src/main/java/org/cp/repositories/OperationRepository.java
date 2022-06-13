package org.cp.repositories;

import org.cp.entities.Compte;
import org.cp.entities.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OperationRepository extends JpaRepository<Operation, Long> {
	@Query("select o from Operation o where o.compte.codeCompte= :codeCompte")
	public Page<Operation> listOperation(@Param("codeCompte") String codeCpte, Pageable pageable);
	
	public Page<Operation> findByCompte(Compte compte, Pageable pageable);
}
