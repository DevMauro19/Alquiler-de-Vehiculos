package Exceptions;

//Excepción cuando se pasa un parámetro nulo en la entrada de datos
public class ParametroNuloException extends Exception {
    public ParametroNuloException() {
        super("Todos los parámetros deben ser ingresados.");
    }
}
