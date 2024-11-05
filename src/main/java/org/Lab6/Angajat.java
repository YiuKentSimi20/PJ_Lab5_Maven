package org.Lab6;

import java.time.LocalDate;

public class Angajat {
    private String nume;
    private String post;
    private LocalDate data_angajarii;
    private float salar;

    public Angajat() {}

    public Angajat(String nume, String post, LocalDate data_angajarii, float salar) {
        this.nume = nume;
        this.post = post;
        this.data_angajarii = data_angajarii;
        this.salar = salar;
    }

    public String getNume() {
        return nume;
    }

    public String getPost() {
        return post;
    }

    public LocalDate getData_angajarii() {
        return data_angajarii;
    }

    public float getSalar() {
        return salar;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public void setData_angajarii(LocalDate data_angajarii) {
        this.data_angajarii = data_angajarii;
    }

    public void setSalar(float salar) {
        this.salar = salar;
    }

    public int comparareSalar(Angajat ang) {
        if (this.salar <= ang.getSalar()) {
            return 1;
        }

        return -1;
    }

    public String numeToUpperCase(){
        return nume.toUpperCase();
    }

    public void angajat_Ion_found(){
        System.out.println("Firma are cel putin un Ion angajat");
    }

    public static void angajat_Ion_not_found(){
        System.out.println("Firma nu are niciun Ion angajat");
    }

    @Override
    public String toString() {
        return nume + "," + post + "," + data_angajarii + "," + salar;
    }
}
