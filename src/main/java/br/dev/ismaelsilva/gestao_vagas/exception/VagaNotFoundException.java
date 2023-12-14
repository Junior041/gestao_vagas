package br.dev.ismaelsilva.gestao_vagas.exception;

public class VagaNotFoundException extends RuntimeException {
    public VagaNotFoundException() {
        super("Vaga não encontrada.");
    }
}
