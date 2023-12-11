package br.dev.ismaelsilva.gestao_vagas.exception;

public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists(){
        super("Usuário já existe");
    }
}
