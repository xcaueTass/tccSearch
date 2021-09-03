package br.com.tcc.webapp.repositories;

import br.com.tcc.webapp.entities.DataTcc;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DataTccRepository extends JpaRepository<DataTcc, Long> {

	@Query("SELECT t FROM DataTcc t WHERE t.nomeTcc = :nameTcc")
	Optional<DataTcc> findNameTcc(@Param("nameTcc") String nameTcc);
}
