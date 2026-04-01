package exceptions;

public class MatriculaDuplicadaException extends Exception {
    public MatriculaDuplicadaException(String mensaje) {
        super(mensaje);
    }
}