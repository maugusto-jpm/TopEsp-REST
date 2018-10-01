package br.edu.fatec.model.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.edu.fatec.model.Aluno;
import br.edu.fatec.model.Curso;
import br.edu.fatec.model.repositories.*;

@Service("cursoService")
public class CursoServiceImp implements CursoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    @Transactional
    public List<Curso> getCursos() {
        return (List<Curso>) cursoRepository.findAll();
    }

    @Override
    @Transactional
    public Curso getCurso(Long id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        if (curso.isPresent())
            return curso.get();
        return null;
    }

    @Override
    @Transactional
    public void salvar(Curso curso) {
        cursoRepository.save(curso);
        if (curso.alunos != null && !curso.alunos.isEmpty()){
            for (Aluno aluno : curso.alunos){
                aluno.curso = curso;
                alunoRepository.save(aluno);
            }
            //curso.alunos.stream().forEach(alunoRepository::save);
        }
	}

    @Override
    @Transactional
    public List<Curso> buscarPorNome(String nome) {
        return cursoRepository.findByNomeContainingIgnoreCase(nome);
    }
}