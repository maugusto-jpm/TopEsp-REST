package br.edu.fatec.controller;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import br.edu.fatec.model.Curso;
import br.edu.fatec.model.service.CursoService;
import br.edu.fatec.views.UsuarioView;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @RequestMapping(value = "/get/{nome}")
    @JsonView(UsuarioView.CursoCompleto.class)
    public ResponseEntity<Collection<Curso>> pesquisar(@PathVariable("nome") String nome) {
        return new ResponseEntity<Collection<Curso>>(cursoService.buscarPorNome(nome), HttpStatus.OK);
    }

    @RequestMapping(value = "/getById")
    @JsonView(UsuarioView.CursoCompleto.class)
    public ResponseEntity<Curso> get(@RequestParam(value = "id") Long id) {
        Curso curso = cursoService.getCurso(id);
        if (curso == null) {
            return new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Curso>(curso, HttpStatus.OK);
    }

    @RequestMapping(value = "/getTodos")
    @JsonView(UsuarioView.CursoCompleto.class)
    public ResponseEntity<List<Curso>> getTodos() {
        List<Curso> cursos = cursoService.getCursos();
        return new ResponseEntity<List<Curso>>(cursos, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    @JsonView(UsuarioView.CursoCompleto.class)
    public ResponseEntity<List<Curso>> salvar(@RequestBody Curso curso) {
        System.out.println(curso);
        cursoService.salvar(curso);
        Long retorno = curso.id;
        System.out.println("Curso salvo com id: " + retorno);
        if (retorno >= 0) {
            System.out.println("Curso salvo: " + retorno);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        System.out.println("Curso não salvo: " + retorno);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @RequestMapping(value = "/salvarVarios", method = RequestMethod.POST)
    @JsonView(UsuarioView.CursoCompleto.class)
    public ResponseEntity<List<Curso>> salvarVarios(@RequestBody List<Curso> cursos) {
        cursos.stream().forEach(cursoService::salvar);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}