package br.dev.ismaelsilva.gestao_vagas.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
            super("Usuário não encontrado.");
    }
}
