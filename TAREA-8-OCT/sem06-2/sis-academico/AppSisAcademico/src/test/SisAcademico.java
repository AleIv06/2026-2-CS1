package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import pe.uni.dominio.Alumno;
import pe.uni.dominio.Curso;
import pe.uni.dominio.Docente;
import pe.uni.dominio.Matricula;
import pe.uni.dominio.Nota;
import pe.uni.dominio.Periodo;
import pe.uni.excepciones.ValidacionException;

/**
 * Aplicacion de consola para el Sistema Academico.
 * Contiene el menu principal y la logica de cada opcion.
 *
 * @author JHERENCIA
 */
public class SisAcademico {

    // Colecciones en memoria que actuan como almacenes de datos
    private final List<Alumno>    alumnos    = new ArrayList<>();
    private final List<Docente>   docentes   = new ArrayList<>();
    private final List<Curso>     cursos     = new ArrayList<>();
    private final List<Periodo>   periodos   = new ArrayList<>();
    private final List<Matricula> matriculas = new ArrayList<>();

    private final Scanner scanner = new Scanner(System.in);

    // =========================================================================
    // Punto de entrada
    // =========================================================================

    public static void main(String[] args) {
        new SisAcademico().ejecutar();
    }

    /** Bucle principal: muestra el menu y despacha la opcion elegida. */
    private void ejecutar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 0);
        System.out.println("Hasta luego!");
    }

    // =========================================================================
    // Menu
    // =========================================================================

    private void mostrarMenu() {
        System.out.println("\n=== SISTEMA ACADEMICO ===");
        System.out.println("1.  Registrar alumno");
        System.out.println("2.  Registrar docente");
        System.out.println("3.  Registrar curso");
        System.out.println("4.  Registrar periodo");
        System.out.println("5.  Matricular alumno");
        System.out.println("6.  Registrar nota");
        System.out.println("7.  Listar alumnos");
        System.out.println("8.  Listar docentes");
        System.out.println("9.  Listar cursos");
        System.out.println("10. Listar matriculas por periodo");
        System.out.println("11. Ver historial de un alumno");
        System.out.println("12. Ver promedio de una matricula");
        System.out.println("0.  Salir");
    }

    /**
     * Despacha el comando segun la opcion elegida.
     * Captura ValidacionException en un solo lugar para no repetir try-catch.
     */
    private void procesarOpcion(int opcion) {
        try {
            switch (opcion) {
                case 1  -> registrarAlumno();
                case 2  -> registrarDocente();
                case 3  -> registrarCurso();
                case 4  -> registrarPeriodo();
                case 5  -> matricularAlumno();
                case 6  -> registrarNota();
                case 7  -> listarAlumnos();
                case 8  -> listarDocentes();
                case 9  -> listarCursos();
                case 10 -> listarMatriculasPorPeriodo();
                case 11 -> verHistorialAlumno();
                case 12 -> verPromedioMatricula();
                case 0  -> {} // salir, no hace nada aqui
                default -> System.out.println("[ERROR] Opcion invalida. Elija entre 0 y 12.");
            }
        } catch (ValidacionException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
    }

    // =========================================================================
    // Opciones de registro (comandos)
    // =========================================================================

    private void registrarAlumno() {
        System.out.println("-- Registrar alumno --");
        String codigo    = leer("Codigo");
        verificarDisponibilidad(
                alumnos.stream().anyMatch(a -> a.codigo().equals(codigo)),
                "Ya existe un alumno con el codigo: " + codigo);
        String nombres   = leer("Nombres");
        String apellidos = leer("Apellidos");
        int    edad      = leerEntero("Edad");
        String carrera   = leer("Carrera");
        alumnos.add(new Alumno(codigo, nombres, apellidos, edad, carrera));
        System.out.println("[OK] Alumno registrado.");
    }

    private void registrarDocente() {
        System.out.println("-- Registrar docente --");
        String codigo       = leer("Codigo");
        verificarDisponibilidad(
                docentes.stream().anyMatch(d -> d.codigo().equals(codigo)),
                "Ya existe un docente con el codigo: " + codigo);
        String nombres      = leer("Nombres");
        String apellidos    = leer("Apellidos");
        String especialidad = leer("Especialidad");
        docentes.add(new Docente(codigo, nombres, apellidos, especialidad));
        System.out.println("[OK] Docente registrado.");
    }

    private void registrarCurso() {
        System.out.println("-- Registrar curso --");
        String codigo    = leer("Codigo");
        verificarDisponibilidad(
                cursos.stream().anyMatch(c -> c.codigo().equals(codigo)),
                "Ya existe un curso con el codigo: " + codigo);
        String  nombre   = leer("Nombre");
        int     creditos = leerEntero("Creditos");
        boolean activo   = leerBooleano("Activo");
        cursos.add(new Curso(codigo, nombre, creditos, activo));
        System.out.println("[OK] Curso registrado.");
    }

    private void registrarPeriodo() {
        System.out.println("-- Registrar periodo --");
        String codigo      = leer("Codigo");
        verificarDisponibilidad(
                periodos.stream().anyMatch(p -> p.codigo().equals(codigo)),
                "Ya existe un periodo con el codigo: " + codigo);
        String descripcion = leer("Descripcion");
        periodos.add(new Periodo(codigo, descripcion));
        System.out.println("[OK] Periodo registrado.");
    }

    private void matricularAlumno() {
        System.out.println("-- Matricular alumno --");
        Alumno  alumno  = buscarAlumno(leer("Codigo del alumno"));
        Periodo periodo = buscarPeriodo(leer("Codigo del periodo"));
        Curso   curso   = buscarCurso(leer("Codigo del curso"));
        Docente docente = buscarDocente(leer("Codigo del docente"));
        Matricula m = new Matricula(alumno, periodo, curso, docente);
        matriculas.add(m);
        System.out.println("[OK] Alumno matriculado. ID de matricula: " + m.idMatricula());
    }

    private void registrarNota() {
        System.out.println("-- Registrar nota --");
        int       id  = leerEntero("ID de matricula");
        Matricula m   = buscarMatricula(id);
        double    cal = leerDecimal("Calificacion (0-20)");
        // Se reconstruyen los objetos desde las listas para crear la Nota
        Alumno  alumno  = buscarAlumno(m.codigoAlumno());
        Curso   curso   = buscarCurso(m.codigoCurso());
        Periodo periodo = buscarPeriodo(m.codigoPeriodo());
        m.agregarNota(new Nota(alumno, curso, periodo, cal));
        System.out.println("[OK] Nota registrada.");
    }

    // =========================================================================
    // Opciones de consulta (queries)
    // =========================================================================

    private void listarAlumnos() {
        System.out.println("-- Lista de alumnos --");
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }
        System.out.printf("%-12s %-30s %-4s %-20s%n", "CODIGO", "NOMBRE", "EDAD", "CARRERA");
        System.out.println("-".repeat(70));
        alumnos.forEach(a -> System.out.printf("%-12s %-30s %-4d %-20s%n",
                a.codigo(), a.nombreCompleto(), a.edad(), a.carrera()));
    }

    private void listarDocentes() {
        System.out.println("-- Lista de docentes --");
        if (docentes.isEmpty()) {
            System.out.println("No hay docentes registrados.");
            return;
        }
        System.out.printf("%-8s %-30s %-20s%n", "CODIGO", "NOMBRE", "ESPECIALIDAD");
        System.out.println("-".repeat(60));
        docentes.forEach(d -> System.out.printf("%-8s %-30s %-20s%n",
                d.codigo(), d.nombreCompleto(), d.especialidad()));
    }

    private void listarCursos() {
        System.out.println("-- Lista de cursos --");
        if (cursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
            return;
        }
        System.out.printf("%-8s %-35s %-9s %-8s%n", "CODIGO", "NOMBRE", "CREDITOS", "ESTADO");
        System.out.println("-".repeat(62));
        cursos.forEach(c -> System.out.printf("%-8s %-35s %-9d %-8s%n",
                c.codigo(), c.nombre(), c.creditos(),
                c.estado() ? "ACTIVO" : "INACTIVO"));
    }

    private void listarMatriculasPorPeriodo() {
        System.out.println("-- Matriculas por periodo --");
        String codigo = leer("Codigo del periodo");
        List<Matricula> resultado = matriculas.stream()
                .filter(m -> m.codigoPeriodo().equals(codigo))
                .toList();
        if (resultado.isEmpty()) {
            System.out.println("Sin matriculas para el periodo: " + codigo);
            return;
        }
        System.out.printf("%-4s %-12s %-25s %-10s %-12s%n",
                "ID", "ALUMNO", "NOMBRE", "CURSO", "ESTADO");
        System.out.println("-".repeat(65));
        resultado.forEach(m -> System.out.printf("%-4d %-12s %-25s %-10s %-12s%n",
                m.idMatricula(), m.codigoAlumno(), m.NomApeAlumno(),
                m.codigoCurso(), m.estadoMatricula()));
    }

    private void verHistorialAlumno() {
        System.out.println("-- Historial del alumno --");
        String codigo = leer("Codigo del alumno");
        List<Matricula> historial = matriculas.stream()
                .filter(m -> m.codigoAlumno().equals(codigo))
                .toList();
        if (historial.isEmpty()) {
            System.out.println("El alumno no tiene matriculas registradas.");
            return;
        }
        System.out.printf("%-4s %-10s %-35s %-12s %-6s%n",
                "ID", "PERIODO", "CURSO", "ESTADO", "PROM.");
        System.out.println("-".repeat(69));
        historial.forEach(m -> System.out.printf("%-4d %-10s %-35s %-12s %-6.2f%n",
                m.idMatricula(), m.codigoPeriodo(), m.nombreCurso(),
                m.estadoMatricula(), m.promedioNotas()));
    }

    private void verPromedioMatricula() {
        System.out.println("-- Promedio de matricula --");
        int       id      = leerEntero("ID de matricula");
        Matricula m       = buscarMatricula(id);
        double    promedio = m.promedioNotas();
        String    resultado = m.obtenerNotas().isEmpty() ? "SIN NOTAS"
                            : promedio >= 10             ? "APROBADO"
                            :                             "DESAPROBADO";
        System.out.println("Matricula : " + m.idMatricula());
        System.out.println("Alumno    : " + m.NomApeAlumno());
        System.out.println("Curso     : " + m.nombreCurso());
        System.out.println("Periodo   : " + m.codigoPeriodo());
        System.out.println("Notas     : " + m.obtenerNotas().size());
        System.out.printf( "Promedio  : %.2f%n", promedio);
        System.out.println("Resultado : " + resultado);
    }

    // =========================================================================
    // Utilidades de entrada
    // =========================================================================

    /** Lee una linea de texto desde la consola. */
    private String leer(String etiqueta) {
        System.out.print(etiqueta + ": ");
        return scanner.nextLine().trim();
    }

    /** Lee un numero entero; lanza ValidacionException si no es valido. */
    private int leerEntero(String etiqueta) {
        System.out.print(etiqueta + ": ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new ValidacionException("Se esperaba un numero entero en: " + etiqueta);
        }
    }

    /** Lee un numero decimal; lanza ValidacionException si no es valido. */
    private double leerDecimal(String etiqueta) {
        System.out.print(etiqueta + ": ");
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new ValidacionException("Se esperaba un numero decimal en: " + etiqueta);
        }
    }

    /** Lee s/n y retorna true si el usuario escribe 's'. */
    private boolean leerBooleano(String etiqueta) {
        return leer(etiqueta + " (s/n)").equalsIgnoreCase("s");
    }

    /** Lee la opcion del menu; retorna -1 si la entrada no es un numero. */
    private int leerOpcion() {
        System.out.print("Opcion: ");
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // opcion invalida, procesarOpcion mostrara el mensaje
        }
    }

    // =========================================================================
    // Utilidades de busqueda
    // =========================================================================

    /** Lanza ValidacionException si ya existe (para evitar duplicados). */
    private void verificarDisponibilidad(boolean existe, String mensaje) {
        if (existe) throw new ValidacionException(mensaje);
    }

    private Alumno buscarAlumno(String codigo) {
        return alumnos.stream()
                .filter(a -> a.codigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new ValidacionException(
                        "No existe alumno con codigo: " + codigo));
    }

    private Docente buscarDocente(String codigo) {
        return docentes.stream()
                .filter(d -> d.codigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new ValidacionException(
                        "No existe docente con codigo: " + codigo));
    }

    private Curso buscarCurso(String codigo) {
        return cursos.stream()
                .filter(c -> c.codigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new ValidacionException(
                        "No existe curso con codigo: " + codigo));
    }

    private Periodo buscarPeriodo(String codigo) {
        return periodos.stream()
                .filter(p -> p.codigo().equals(codigo))
                .findFirst()
                .orElseThrow(() -> new ValidacionException(
                        "No existe periodo con codigo: " + codigo));
    }

    private Matricula buscarMatricula(int id) {
        return matriculas.stream()
                .filter(m -> m.idMatricula() == id)
                .findFirst()
                .orElseThrow(() -> new ValidacionException(
                        "No existe matricula con ID: " + id));
    }
}
