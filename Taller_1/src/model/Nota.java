package model;

import exceptions.NotaInvalidaException;



public class Nota {

    private static final double PESO_CORTE1     = 0.30;
    private static final double PESO_CORTE2     = 0.30;
    private static final double PESO_CORTE3     = 0.40;
    private static final double NOTA_MINIMA     = 3.0;
    private static final double NOTA_MAX        = 5.0;
    private static final double NOTA_MIN        = 0.0;

    private double corte1;
    private double corte2;
    private double corte3;
    private String codigoEstudiante;
    private String codigoAsignatura;


    public Nota(String codigoEstudiante, String codigoAsignatura,
                double corte1, double corte2, double corte3) throws NotaInvalidaException {
        validarNota(corte1, "Corte 1");
        validarNota(corte2, "Corte 2");
        validarNota(corte3, "Corte 3");
        this.codigoEstudiante = codigoEstudiante;
        this.codigoAsignatura = codigoAsignatura;
        this.corte1 = corte1;
        this.corte2 = corte2;
        this.corte3 = corte3;
    }

    //sólo primer corte
    public Nota(String codigoEstudiante, String codigoAsignatura, double corte1) throws NotaInvalidaException {
        this(codigoEstudiante, codigoAsignatura, corte1, 0.0, 0.0);
    }

    private void validarNota(double nota, String corte) throws NotaInvalidaException {
        if (nota < NOTA_MIN || nota > NOTA_MAX) {
            throw new NotaInvalidaException(
                    corte + " inválida: " + nota + ". Debe estar entre 0.0 y 5.0");
        }
    }



    public double calcularFinal() {
        return (corte1 * PESO_CORTE1) + (corte2 * PESO_CORTE2) + (corte3 * PESO_CORTE3);
    }

    public boolean estaAprobado() {
        return calcularFinal() >= NOTA_MINIMA;
    }

    public String obtenerEstado() {
        return estaAprobado() ? "APROBADO" : "REPROBADO";
    }


    public void actualizarCorte(int numeroCorte, double nuevaNota) throws NotaInvalidaException {
        validarNota(nuevaNota, "Corte " + numeroCorte);
        switch (numeroCorte) {
            case 1 -> corte1 = nuevaNota;
            case 2 -> corte2 = nuevaNota;
            case 3 -> corte3 = nuevaNota;
            default -> throw new IllegalArgumentException("Número de corte inválido: " + numeroCorte);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Nota [%s - %s] C1:%.1f | C2:%.1f | C3:%.1f => Final: %.2f (%s)",
                codigoEstudiante, codigoAsignatura,
                corte1, corte2, corte3,
                calcularFinal(), obtenerEstado());
    }



    public double getCorte1()            { return corte1; }
    public double getCorte2()            { return corte2; }
    public double getCorte3()            { return corte3; }
    public String getCodigoEstudiante()  { return codigoEstudiante; }
    public String getCodigoAsignatura()  { return codigoAsignatura; }
}
