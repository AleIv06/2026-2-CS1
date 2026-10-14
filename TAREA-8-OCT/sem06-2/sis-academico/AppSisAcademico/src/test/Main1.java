package test;
import pe.uni.dominio.Alumno;
import pe.uni.dominio.Curso;
import pe.uni.dominio.Docente;
import pe.uni.dominio.Matricula;
import pe.uni.dominio.Periodo;

/**
 *
 * @author JHERENCIA
 */
public class Main1 {
    public static void main(String[] args) {
        Alumno alu1 = new Alumno("202500100","Abel","Abad Quispe", 15, "Ing. Sistemas");
        Docente doc1 = new Docente("1111", "Leandro", "Arias", "Matemáticas");
        Curso cur1 = new Curso("SW101", "Introduccion a la Computación", 4, true);
        Periodo per1 = new Periodo("2025-3", "Ciclo de verano 2025-3, llevados en el verano del 2026");
        Matricula mat1 = new Matricula(alu1, per1, cur1, doc1);
        System.out.println("Datos del alumno:");
        System.out.println("Nombres completos: " + alu1.nombreCompleto());
        System.out.println("Edad             : " + alu1.edad());
        
        System.out.println("Datos del docente:");
        System.out.println("Codigo           : " + doc1.codigo());
        System.out.println("Nombres completos: " + doc1.nombreCompleto());
        
        System.out.println("Datos del curso:");
        System.out.println("Codigo           : " + cur1.codigo());
        System.out.println("Nombres completos: " + cur1.nombre());
        
        System.out.println("Datos del periodo:");
        System.out.println("Codigo           : " + per1.codigo());
        System.out.println("Nombres completos: " + per1.descripcion());
        
        System.out.println("Datos de la matricula:");
        System.out.println("Id_Matricula       : " + mat1.idMatricula());
        System.out.println("Codigo Periodo     : " + mat1.codigoPeriodo()); 
        System.out.println("Descricpion Periodo: " + mat1.descripcionPeriodo()); 
        System.out.println("Nomb y apell alumno: " + mat1.NomApeAlumno());
        System.out.println("Codigo del curso   : " + mat1.codigoCurso());
        System.out.println("Codigo del docente : " + mat1.codigoDocente());
        System.out.println("Estado matricula   : " + mat1.estadoMatricula());
        
    }
}
