package log;

import models.Student;

public class logStudent {
    private static int maxClass = 60;

    private static double average (Student student) {
        return (student.getP1() + student.getP2() + student.getP3()) / 3;
    }

    private static double percentageAbsences (Student student) {
        return (maxClass > 0) ? ((student.getAbsence() * 100) / maxClass) : 0; 
    }

    public static void printStudent(Student student) {
        System.out.printf("=============================================\n");
        System.out.printf("Matricula: %d\n", student.getRegistration());
        System.out.printf("Aluno: %s\n", student.getName());
        System.out.printf("Faltas: %d\n", student.getAbsence());
        System.out.printf("Perc Faltas: %.2f%%\n", percentageAbsences(student));
        System.out.printf("P1: %.2f\n", student.getP1());
        System.out.printf("P2: %.2f\n", student.getP2());
        System.out.printf("P3: %.2f\n", student.getP3());
        System.out.printf("Media: %.2f\n", average(student));
        System.out.printf("Media Arr: %.2f\n", student.average());
        System.out.printf("Situacao: %s\n", student.situation(60));
        System.out.printf("Minimo NAF: %.2f\n", student.minNaf(maxClass));
        System.out.printf("NAF: %.2f\n", student.naf(maxClass));
        System.out.printf("=============================================\n");
    }    
}
