package br.edu.fatec.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import com.fasterxml.jackson.annotation.JsonView;
import br.edu.fatec.views.Views;

@Entity
@XmlRootElement(name = "Aluno")
@XmlAccessorType(XmlAccessType.FIELD)
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView({ Views.AlunoPesquisa.class })
    public Long id;

    @JsonView({ Views.Aluno.class })
    public String nome;

    @JsonView({ Views.Aluno.class })
    public int idade;

    @ManyToOne
    @JoinColumn(name = "fk_curso", nullable = false)
    @XmlTransient
    @JsonView({ Views.AlunoCompleto.class })
    public Curso curso;
}