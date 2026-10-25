package edu.uasd.inf5150;

//Excepcion personalizada que se lanza cuando la edad de una Persona esta fuera del rango permitido
//al heredar de Exception es una excepcion verificada, por eso Java obliga a capturarla o declararla
public class EdadInvalidaException extends Exception {

    //variable que guarda la edad invalida que causo la excepcion
    private final int edad;

    //Declare el constructor para aceptar la edad invalida y crear un mensaje explicativo
    public EdadInvalidaException(int edad) {
        super("La edad debe estar entre " + Persona.EDAD_MINIMA + " y " + Persona.EDAD_MAXIMA
                + " años, pero se introdujo: " + edad);
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }
}