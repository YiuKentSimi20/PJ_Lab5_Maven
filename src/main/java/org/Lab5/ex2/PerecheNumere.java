package org.Lab5.ex2;

import java.math.*;

/**
 * Clasa ce reprezinta o pereche de numere
 * @author ILIA Alexandru
 */
public class PerecheNumere {
    public int a;
    public int b;

    /**
     * Constructor cu 2 paramatrii
     * @param a
     * @param b
     */
    public PerecheNumere(int a, int b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Constructor fara parametrii
     */
    public PerecheNumere() {}

    public void setA(int a) { this.a = a; }
    public void setB(int b) { this.b = b; }
    public int getA() { return a; }
    public int getB() { return b; }

    @Override
    public String toString() {
        return "[" + a + ", " + b + "]";
    }

    /**
     * Metoda recursiva ce va returna o valoare booleana care indică dacă cele două numere care
     * formează perechea sunt numere consecutive în șirul lui Fibonacci
     * @param x
     * @param y
     * @return
     */
    public boolean consecutiveFibonacci(int x, int y) {
        if((a == x && b == y) || (b == x && a == y)) {
            return true;
        }

        if(a < y || b < y) { return false; }

        return this.consecutiveFibonacci(y, x+y);
    }


    /**
     * Metoda care returneaza cel mai mic multiplu comun al perechii de numere
     * @return
     */
    public int cmmmc() {
        int x = a, y = b;
        while(x != y) {
            if(x > y)
                x-=y;
            else
                y-=x;
        }
        int cmmdc = x;

        return a*b/cmmdc;
    }

    /**
     * Metoda care va returna un boolean care arata daca cele 2 numere au suma cifrelor egala
     * @return
     */
    public boolean sumaCifEgala(){
        int x = a, y = b;
        int sumx = 0, sumy = 0;

        while(x != 0){
            sumx += x%10;
            x/=10;
        }

        while(y != 0) {
            sumy += y%10;
            y/=10;
        }


        return sumx == sumy;
    }

    /**
     * Metoda care va returna un boolean care indica daca cele 2 numere au acelasi numar de cifre pare
     * @return
     */
    public boolean acelasiNrCifPare() {
        int x = a, y = b;
        int nrx = 0, nry = 0;

        while(x != 0){
            if(x%2 == 0)
                nrx++;

            x=x/10;
        }

        while(y != 0) {
            if(y%2 == 0)
                nry++;
            y=y/10;
        }

        return nrx == nry;
    }

}
