package org.Lab5.ex3;

import java.util.List;
import java.util.Objects;

public class Mobilier {
    private String nume;
    private List<Placa> placi;

    public Mobilier() {}

    public Mobilier(String nume, List<Placa> placi) {
        this.nume = nume;
        this.placi = placi;
    }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }
    public List<Placa> getPlaci() { return placi; }
    public void setPlaci(List<Placa> placi) { this.placi = placi; }

    @Override
    public String toString() {
        return nume + "\nplaci:\n" + placi;
    }

    public int NrPlaciDePal() {
        int arie_totala = 0;
        int arie_pal = 2800 * 2070;
        for(Placa p : placi) {
            arie_totala += p.getLatime()*p.getLungime();
        }

        System.out.println(arie_totala);
        System.out.println(arie_pal);
        return arie_totala/arie_pal + 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mobilier mobilier = (Mobilier) o;
        return Objects.equals(nume, mobilier.nume) && Objects.equals(placi, mobilier.placi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, placi);
    }
}
