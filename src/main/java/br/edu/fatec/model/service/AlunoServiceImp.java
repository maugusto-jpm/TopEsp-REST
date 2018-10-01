package br.edu.fatec.model.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.fatec.model.Aluno;
import br.edu.fatec.model.repositories.AlunoRepository;
import br.edu.fatec.model.repositories.CursoRepository;;

@Service("alunoService")
public class AlunoServiceImp implements AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    @Transactional
    public List<Aluno> getAlunos() {
        return (List<Aluno>) alunoRepository.findAll();
    }

    @Override
    @Transactional
    public Aluno getAluno(Long id) {
        Optional<Aluno> aluno = alunoRepository.findById(id);
        if (aluno.isPresent())
            return aluno.get();
        return null;
    }

    @Override
    @Transactional
    public void salvar(Aluno aluno) {
        System.out.println(aluno);
        if (aluno.curso != null && aluno.curso.id != null) {
            System.out.println(cursoRepository.save(aluno.curso));
        }
        System.out.println(alunoRepository.save(aluno));
    }

    @Override
    @Transactional
    public List<Aluno> buscarPorNome(String nome) {
        return alunoRepository.findByNomeContainingIgnoreCase(nome);
    }
}