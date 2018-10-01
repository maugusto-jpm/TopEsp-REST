package br.edu.fatec.controller;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import br.edu.fatec.model.Aluno;
import br.edu.fatec.model.service.AlunoService;
import br.edu.fatec.views.Views;
/*
json-generator.com/
[
  '{{repeat(10)}}',
  {
    nome: '{{city()}}',
    professor: '{{surname()}}',
    duracao: '{{integer(3, 14)}} meses',
    alunos: [
      '{{repeat(10)}}',
      {
        nome: '{{firstName()}}',
        idade: '{{integer(23, 39)}}'
      }
    ]
  }
]
*/

@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @RequestMapping(value = "/getById")
    @JsonView(Views.AlunoCompleto.class)
    public ResponseEntity<Aluno> get(@RequestParam(value = "id") Long id) {
        Aluno aluno = alunoService.getAluno(id);
        if (aluno == null) {
            return new ResponseEntity<Aluno>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/getByNome/{nome}")
    @JsonView(Views.AlunoPesquisa.class)
    public ResponseEntity<List<Aluno>> getByNome(@PathVariable("nome") String nome) {
        List<Aluno> alunos = alunoService.buscarPorNome(nome);
        if (alunos == null || alunos.isEmpty()) {
            return new ResponseEntity<List<Aluno>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Aluno>>(alunos, HttpStatus.OK);
    }

    @RequestMapping(value = "/getTodos")
    @JsonView(Views.Aluno.class)
    public ResponseEntity<List<Aluno>> getTodos() {
        List<Aluno> alunos = alunoService.getAlunos();
        if (alunos == null || alunos.isEmpty()) {
            return new ResponseEntity<List<Aluno>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Aluno>>(alunos, HttpStatus.OK);
    }



    @ResponseBody
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @JsonView(Views.AlunoCompleto.class)
    public ResponseEntity<List<Aluno>> salvar(@RequestBody Aluno aluno) {
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

    @ResponseBody
    @RequestMapping(value = "/salvarVarios", method = RequestMethod.POST)
    @JsonView(Views.AlunoCompleto.class)
    public ResponseEntity<List<Aluno>> salvarVarios(@RequestBody List<Aluno> alunos) {
        boolean sucesso = true;
        for (Aluno aluno : alunos){
            alunoService.salvar(aluno);
            if (aluno.id == null) sucesso = false;
        }
        if (sucesso)
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}