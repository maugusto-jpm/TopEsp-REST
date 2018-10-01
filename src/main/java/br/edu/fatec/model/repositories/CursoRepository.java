package br.edu.fatec.model.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.edu.fatec.model.Curso;

public interface CursoRepository extends CrudRepository<Curso, Long> {
    public List<Curso> findByNomeContainingIgnoreCase(String nome);
}