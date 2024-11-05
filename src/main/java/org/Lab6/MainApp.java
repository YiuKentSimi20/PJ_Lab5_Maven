package org.Lab6;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.StringReader;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {
    static void scriere(List<Angajat> lista) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            File file = new File("src/main/resources/angajati.json");
            mapper.writeValue(file, lista);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    static List<Angajat> citire() {
        try {
            File file = new File("src/main/resources/angajati.json");
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            List<Angajat> lista = mapper.readValue(file, new TypeReference<List<Angajat>>() {});
            return lista;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void main(String[] args) {

        List<Angajat> angajati = new ArrayList<>();
        angajati = citire();


        //1 ---Afisarea listei de angajati cu referinte la metode---
        Optional<List<Angajat>> opt = Optional.ofNullable(angajati);
        if(opt.isPresent()) {
            System.out.println("--- Afisarea cu referinte la metode ---");
            opt.get().forEach(System.out::println);
        }
        else {
            System.out.println("Lista goala");
        }

        //2 ---Afisarea angajatilor cu salar mai mare de 2500---
        System.out.println("\n--- Angajati cu salar > 2500 ---");
        angajati
            .stream()
            .filter((a)->a.getSalar()>2500)
            .forEach(System.out::println);

        //3 ---Afisarea angajatilor in functii de conducere din luna aprilie a anului trecut---
        System.out.println("\n--- Angajati in functii de conducere(din aprilie anul trecut) ---");
        List<Angajat> angajati_sefi =
                angajati
                        .stream()
                        .filter((a)->{
                            boolean are_fuctie_de_conducere = a.getPost().equalsIgnoreCase("sef")
                                    ||a.getPost().equalsIgnoreCase("director");

                            boolean luna_este_aprilie_anul_trecut = a.getData_angajarii().getMonth()
                                                        .toString().compareToIgnoreCase("April")==0
                                                        && a.getData_angajarii().getYear() == (LocalDate.now().getYear()-1);

                            return are_fuctie_de_conducere && luna_este_aprilie_anul_trecut;
                        })
                        .collect(Collectors.toList());

        angajati_sefi.forEach(System.out::println);

        //4 ---Angajati care nu au fuctii de conducere in ordine descrescatoare dupa salariu---
        System.out.println("\n--- Angajati care nu au fuctii de conducere in ordine descrescatoare dupa salariu ---");

        angajati
                .stream()
                .filter((a)->
                        !(a.getPost().equalsIgnoreCase("sef")
                                ||a.getPost().equalsIgnoreCase("director")))
                .sorted((a, b)-> a.comparareSalar(b))
                .forEach(System.out::println);

        //5 ---Lista de stringuri cu numele angajatilor cu majuscule---
        System.out.println("\n--- Lista de stringuri cu numele angajatilor cu majuscule ---");

        List<String> nume_angajati = angajati
                .stream()
                .map(Angajat::numeToUpperCase)
                .collect(Collectors.toList());

        nume_angajati.forEach(System.out::println);

        //6 ---Lista salariilor mai mici de 3000---
        System.out.println("\n--- Lista salariilor mai mici de 3000 ---");
        angajati
                .stream()
                .filter((a)->a.getSalar()<3000)
                .map(Angajat::getSalar)
                .forEach(System.out::println);

        //7 ---Afisarea primului angajat---
        System.out.println("\n--- Primul angajat ---");

        angajati
                .stream()
                .min(Comparator.comparing(Angajat::getData_angajarii))
                .ifPresentOrElse(System.out::println, System.out::println);

        //8 ----------------------------------------
        System.out.println("\n--- Statistici salarii ---");

        DoubleSummaryStatistics statistics = angajati.stream()
                                                     .collect(Collectors.summarizingDouble(Angajat::getSalar));

        System.out.println("Media: " + statistics.getAverage());
        System.out.println("Minim: " + statistics.getMin());
        System.out.println("Maxim: " + statistics.getMax());

        //9 ----------------------------------------
        System.out.println("\n--- Exista cel putin un \"Ion\" ---");

        angajati
                .stream()
                .filter((a)->a.getNume().equalsIgnoreCase("Ion"))
                .findAny().ifPresentOrElse(Angajat::angajat_Ion_found, Angajat::angajat_Ion_not_found);


        //10 ----------------------------------------
        System.out.println("\n--- Numarul de persoane care s-au angajat in vara anului trecut ---");

        int nr_angajati_vara_trecuta = (int) angajati.stream()
                .filter((a)-> (a.getData_angajarii().getYear()==LocalDate.now().getYear()-1
                        && (a.getData_angajarii().getMonthValue() == 6
                            || a.getData_angajarii().getMonthValue() == 7
                            || a.getData_angajarii().getMonthValue() == 8)))
                .count();

        System.out.println(nr_angajati_vara_trecuta);
    }
}
