package pe.uni.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pe.uni.excepciones.ValidacionException;

/**
 *
 * @author JHERENCIA
 */
public class Matricula {
    private int id_matricula;
    private static int contador_matricula = 0; // variable contador de matricula
                                               // Esto es para tener el ultimo valor del autocorrelativo
    private Alumno alumno;
    private Periodo periodo;
    private Curso curso;
    private Docente docente;
    private LocalDate fecha;
    private EstadoMatricula estado;
    private List<Nota> notas = new ArrayList<>();

    public Matricula(Alumno alumno, Periodo periodo, Curso curso, Docente docente) {
        if(alumno.codigo() == null || alumno.codigo().isBlank()) {
            throw new ValidacionException("El codigo del alumno es obligatorio");
        }
        if(periodo.codigo() == null || periodo.codigo().isBlank()) {
            throw new ValidacionException("El periodo de matricula es obligatorio");
        }
        // Entra en bruto los valores ya validados
        contador_matricula++; // Nueva matricula, => aumentar el contador
        this.id_matricula = contador_matricula; // asignar el valor del contador
        this.alumno = alumno;
        this.periodo = periodo;
        this.curso = curso;
        this.docente = docente;
        this.fecha = LocalDate.now();
        this.estado = EstadoMatricula.ACTIVA;
    }

    // Consultas (Query)
    public int idMatricula() {
        return id_matricula;
    }

    public String codigoAlumno() {
        return alumno.codigo();
    }

    public String NomApeAlumno() {
        return alumno.nombreCompleto();
    }

    public String codigoCurso() {
        return curso.codigo();
    }

    public String nombreCurso() { // nombre del curso para mostrar en listados
        return curso.nombre();
    }

    public String codigoDocente() {
        return docente.codigo();
    }

    public String codigoPeriodo() {
        return periodo.codigo();
    }

    public String descripcionPeriodo() {
        return periodo.descripcion();
    }

    public String estadoMatricula() {
        switch(estado) {
            case EstadoMatricula.ACTIVA:         return "ACTIVA";
            case EstadoMatricula.CANCELADA:      return "CANCELADA"; // corregido: antes retornaba ACTIVA
            case EstadoMatricula.ANULADA:        return "ANULADA";
            case EstadoMatricula.NO_MATRICULADO: return "NO MATRICULADO";
            case EstadoMatricula.RETIRADA:       return "RETIRADO";
        }
        return "";  // Aqui nunca se deberia llegar
    }

    // Retorna lista no modificable de notas para consulta externa
    public List<Nota> obtenerNotas() {
        return Collections.unmodifiableList(notas);
    }

    // Calcula el promedio vigesimal de las notas registradas
    public double promedioNotas() {
        if(notas.isEmpty()) return 0.0;
        return notas.stream()
                .mapToDouble(Nota::calificacion)
                .average()
                .orElse(0.0);
    }

    // Commands
    public void RetirarMatricula() {
        if(estado != EstadoMatricula.ACTIVA) {
            throw new ValidacionException("Solo se puede retirar una matricula activa");
        }
        this.estado = EstadoMatricula.RETIRADA;
    }

    public void agregarNota(Nota nota) {
        if(!estado.permiteCalificaciones()) { // Si no permite calificaciones, salir con error
            throw new ValidacionException("No se puede agregar notas a una matricula con estado " + estado);
        }
        notas.add(nota); // agregar la nota
    }

    public void AnularMatricula() {
        if(estado != EstadoMatricula.ACTIVA) {
            throw new ValidacionException("Solo se puede anular una matricula activa");
        }
        this.estado = EstadoMatricula.ANULADA;
    }
}
