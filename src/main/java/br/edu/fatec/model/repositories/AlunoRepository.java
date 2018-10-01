package br.edu.fatec.model.repositories;

import java.util.*;
import br.edu.fatec.model.Aluno;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {
    public List<Aluno> findByNomeContainingIgnoreCase(String nome);
}