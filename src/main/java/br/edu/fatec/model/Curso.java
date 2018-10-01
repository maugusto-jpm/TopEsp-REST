package br.edu.fatec.model;

import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonView;
import br.edu.fatec.views.UsuarioView;

@Entity
@XmlRootElement(name = "Curso")
@XmlAccessorType(XmlAccessType.FIELD)
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({ UsuarioView.UsuarioCompleto.class, UsuarioView.CursoCompleto.class })
    public Long id;

    @JsonView({ UsuarioView.UsuarioCompleto.class, UsuarioView.CursoCompleto.class })
    public String nome;

    @JsonView({ UsuarioView.UsuarioCompleto.class, UsuarioView.CursoCompleto.class })
    public String professor;

    @JsonView({ UsuarioView.UsuarioCompleto.class, UsuarioView.CursoCompleto.class })
    public String duracao;

    @OneToMany
    @JoinColumn(name = "fk_aluno")
    @JsonView({ UsuarioView.CursoCompleto.class })
    public List<Aluno> alunos;

    @Override
    public String toString() {
        return nome;
    }
}