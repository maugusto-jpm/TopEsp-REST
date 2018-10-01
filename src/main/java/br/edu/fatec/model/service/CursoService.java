package br.edu.fatec.model.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.fatec.model.Curso;

public interface CursoService {
    public List<Curso> getCursos();

    public Curso getCurso(Long id);

    public void salvar(Curso curso);

    public List<Curso> buscarPorNome(String Nome);
}