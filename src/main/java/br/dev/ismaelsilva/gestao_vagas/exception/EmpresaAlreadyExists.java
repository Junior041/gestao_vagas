package br.dev.ismaelsilva.gestao_vagas.exception;

public class EmpresaAlreadyExists extends RuntimeException {
    public EmpresaAlreadyExists(){
        super("Empresa já cadastrada.");
    }
}
