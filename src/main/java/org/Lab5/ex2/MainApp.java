package org.Lab5.ex2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainApp {

    /**
     * Metoda statica petnru scierea listei in fisierul "perechi_numere.json"
     * @param lista
     */
    static void scriere(List<PerecheNumere> lista) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/perechi_numere.json");
            mapper.writeValue(file, lista);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metoda statica pentru preluarea datelor din fisierul "perechi_numere.json" intr-o lista
     * @return
     */
    static List<PerecheNumere> citire() {
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/perechi_numere.json");
            List<PerecheNumere> lista = mapper.readValue(file, new TypeReference<List<PerecheNumere>>(){});
            return lista;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        List<PerecheNumere> lista = citire();
        System.out.println(lista);

        for (PerecheNumere p : lista)
            System.out.println(p + " "+ p.consecutiveFibonacci(0,1));

        scriere(lista);

    }
}
