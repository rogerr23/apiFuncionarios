package br.com.roger.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.roger.entities.Funcionario;

@Repository
public interface FuncionarioRepository extends MongoRepository<Funcionario, UUID> {

	// gte: maior ou igual a (greather than equal)
	// lte menor ou igual a (less than equal)

	@Query("{'dataAdmissao' : { $gte: ?0, %lte: ?1}}")
	List<Funcionario> findByDatas(Date dataMin, Date dataMax);

}
