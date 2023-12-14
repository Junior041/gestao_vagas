package br.dev.ismaelsilva.gestao_vagas.exception;

public class VagaAlreadyAplicada extends RuntimeException{
    public VagaAlreadyAplicada() {
        super("Essa vaga ja foi aplicada");
    }
}
