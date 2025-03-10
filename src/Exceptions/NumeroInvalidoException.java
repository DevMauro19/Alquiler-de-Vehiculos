package Exceptions;

//Excepción cuando un número no cumple con los requisitos establecidos
public class NumeroInvalidoException extends Exception {
    public NumeroInvalidoException(String mensaje) {
        super(mensaje);
    }
}