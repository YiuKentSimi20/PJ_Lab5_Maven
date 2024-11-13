package org.Lab7.ex2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class InstrumentMuzical {
    private String producator;
    private int pret;

    public InstrumentMuzical() {}

    public InstrumentMuzical(String producator, int pret) {
        this.producator = producator;
        this.pret = pret;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return producator + "," + pret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstrumentMuzical that = (InstrumentMuzical) o;
        return pret == that.pret && Objects.equals(producator, that.producator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producator, pret);
    }
}
