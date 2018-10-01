package br.edu.fatec.model.service;

import java.util.List;
import org.springframework.stereotype.Service;
import br.edu.fatec.model.Aluno;

public interface AlunoService {
    public List<Aluno> getAlunos();

    public Aluno getAluno(Long id);

    public void salvar(Aluno aluno);

    public List<Aluno> buscarPorNome(String Nome);
}