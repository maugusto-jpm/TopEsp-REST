package br.edu.fatec.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonView;
import br.edu.fatec.views.UsuarioView;

@Entity
@XmlRootElement(name = "Aluno")
@XmlAccessorType(XmlAccessType.FIELD)
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@JsonView({ UsuarioView.UsuarioCompleto.class, UsuarioView.CursoCompleto.class })
    public Long id;

    @JsonView({ UsuarioView.UsuarioCompleto.class, UsuarioView.CursoCompleto.class })
    public String nome;

    @JsonView({ UsuarioView.UsuarioCompleto.class, UsuarioView.CursoCompleto.class })
    public int idade;

    @ManyToOne
    @XmlTransient
    @JsonView({ UsuarioView.UsuarioCompleto.class })
    public Curso curso;
}