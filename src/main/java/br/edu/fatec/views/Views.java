package br.edu.fatec.views;

public class Views {
    public interface Curso {}
    public interface Aluno {}
    public interface AlunoCompleto extends Aluno, Curso {}
    public interface AlunoPesquisa extends Aluno {}
    public interface CursoCompleto extends Curso, Aluno {}
}