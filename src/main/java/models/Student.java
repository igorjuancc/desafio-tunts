package models;

public class Student {
    private int registration;
    private String name;
    private int absence;
    private double p1;
    private double p2;
    private double p3;

    public Student(int registration, String name, int absence, double p1, double p2, double p3) {
        this.registration = registration;
        this.name = name;
        this.absence = absence;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public int getRegistration() {
        return registration;
    }

    public void setRegistration(int registration) {
        this.registration = registration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAbsence() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getP2() {
        return p2;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    public double getP3() {
        return p3;
    }

    public void setP3(double p3) {
        this.p3 = p3;
    }

    public double average() {
        return Math.round((this.p1 + this.p2 + this.p3) / 3);
    }

    // Naf with the minimum for approval
    public double naf(int maxClass) {
        double avg = this.average();
        if ((avg >= 70) || (avg < 50) || (((this.absence * 100) / maxClass) > 25)) {
            return 0;
        } else {
            return Math.round((avg + this.minNaf(maxClass)) / 2);
        }
    }

    // Minimum for approval
    public double minNaf(int maxClass) {
        double avg = this.average();
        if ((avg >= 70) || (avg < 50) || (((this.absence * 100) / maxClass) > 25)) {
            return 0;
        } else {
            return (100 - avg);
        }
    }

    public String situation(int maxClass) {
        if ((maxClass > 0) && (((this.absence * 100) / maxClass) > 25)) {
            return "Reprovado por Falta";
        } else if (this.average() < 50) {
            return "Reprovado por Nota";
        } else if (this.average() >= 70) {
            return "Aprovado";
        } else {
            return "Exame Final";
        }
    }
}
