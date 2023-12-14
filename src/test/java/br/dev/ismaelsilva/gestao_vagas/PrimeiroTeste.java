package br.dev.ismaelsilva.gestao_vagas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrimeiroTeste {
    @Test
    public void deve_ser_possivel_calcular_dois_numeros(){
        var result = calculate(2,3);
        Assertions.assertEquals(5,result);
    }
    @Test
    public void validar_valor_incorreto(){
        var result = calculate(2,3);
        Assertions.assertNotEquals(result,4);
    }
    public static int calculate(int num1, int num2){
        return num1 + num2;
    }
}
