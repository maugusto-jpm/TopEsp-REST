package br.edu.fatec.controller;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import br.edu.fatec.model.Aluno;
import br.edu.fatec.model.service.AlunoService;
import br.edu.fatec.views.UsuarioView;

@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @RequestMapping(value = "/get/{nome}")
    @JsonView(UsuarioView.UsuarioCompleto.class)
    public ResponseEntity<Collection<Aluno>> pesquisar(@PathVariable("nome") String nome) {
        return new ResponseEntity<Collection<Aluno>>(alunoService.buscarPorNome(nome), HttpStatus.OK);
    }

    @RequestMapping(value = "/getById")
    @JsonView(UsuarioView.UsuarioCompleto.class)
    public ResponseEntity<Aluno> get(@RequestParam(value = "id") Long id) {
        Aluno aluno = alunoService.getAluno(id);
        if (aluno == null) {
            return new ResponseEntity<Aluno>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll")
    @JsonView(UsuarioView.UsuarioCompleto.class)
    public ResponseEntity<List<Aluno>> getAll() {
        List<Aluno> alunos = alunoService.getAlunos();
        return new ResponseEntity<List<Aluno>>(alunos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @JsonView(UsuarioView.UsuarioCompleto.class)
    public ResponseEntity<List<Aluno>> salvarAluno(@RequestBody Aluno aluno) {
        System.out.println(aluno);
        alunoService.salvar(aluno);
        Long retorno = aluno.id;
        System.out.println("Aluno salvo com id: " + retorno);
        if (retorno >= 0) {
            System.out.println("Aluno salvo: " + retorno);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        System.out.println("Aluno não salvo: " + retorno);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}