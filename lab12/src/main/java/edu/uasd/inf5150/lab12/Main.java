package edu.uasd.inf5150.lab12;

 class Main {

    public static void main(String[] args) {

        Universidad universidad = new Universidad("UASD");

        //Aqui tenemos los ejemplos de profesores
        Profesor profesor1 = new Profesor("Juan Perez", "Programacion II");
        Profesor profesor2 = new Profesor("Rosa Gomez", "Teleproceso");
        Profesor profesor3 = new Profesor("Pedro Ruiz", "Bases de Datos I");

        // Luego cursos de ejemplos, en este caso cada uno con su profesor asignado
        Curso curso1 = new Curso("INF-5150", "Lenguaje de Programación II", profesor1);
        Curso curso2 = new Curso("INF-4050", "Teleproceso", profesor2);
        Curso curso3 = new Curso("INF-4200", "Bases de Datos I", profesor3);

        // Despues agregamos estos cursos a la universida
        universidad.agregarCurso(curso1);
        universidad.agregarCurso(curso2);
        universidad.agregarCurso(curso3);

        // Con esta funcion vermos la información organizada por consola
        universidad.listarCursos();

        System.out.println();

        // Aqui pruebamos el agregar un curso con un codigo YA CREADO (INF-5150)
        // para ver la excepcion que la universidad dectecta y evita los duplicados.
        try {
            Curso cursoDuplicado = new Curso("INF-5150", "Pedro Rivera", profesor2);
            universidad.agregarCurso(cursoDuplicado);
        } catch (IllegalArgumentException e) {
            System.out.println("No podemos agregar el curso ya que esta duplicado: " + e.getMessage());
        }
    }
}