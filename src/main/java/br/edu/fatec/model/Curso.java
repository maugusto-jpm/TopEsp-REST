package br.edu.fatec.model;

import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonView;
import br.edu.fatec.views.Views;

@Entity
@XmlRootElement(name = "Curso")
@XmlAccessorType(XmlAccessType.FIELD)
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @JsonView({ Views.Curso.class })
    public String nome;

    @JsonView({ Views.Curso.class })
    public String professor;

    @JsonView({ Views.Curso.class })
    public String duracao;

    @OneToMany(mappedBy = "curso")
    @JsonView({ Views.CursoCompleto.class })
    public List<Aluno> alunos;
}