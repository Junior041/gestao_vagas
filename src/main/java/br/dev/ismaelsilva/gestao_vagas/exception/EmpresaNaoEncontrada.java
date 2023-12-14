package br.dev.ismaelsilva.gestao_vagas.exception;

public class EmpresaNaoEncontrada extends RuntimeException{
    public EmpresaNaoEncontrada() {
        super("Empresa não encontrada");
    }
}
