package org.Lab7.ex2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.sql.SQLOutput;
import java.util.*;

public class MainApp {
    public static void scriere(Set<InstrumentMuzical> set_instrumente) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            File fis = new File("src/main/resources/instrumente_muzicale.json");
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            mapper.writeValue(fis, set_instrumente);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Set<InstrumentMuzical> citire() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File fis = new File("src/main/resources/instrumente_muzicale.json");
            mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator());
            return mapper.readValue(fis, new TypeReference<HashSet<InstrumentMuzical>>() {});
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void afisare_set(Set<InstrumentMuzical> set_instrumente) {
        Optional<Set<InstrumentMuzical>> opt_instrumente = Optional.empty();
        opt_instrumente = Optional.ofNullable(set_instrumente);

        opt_instrumente.ifPresentOrElse(System.out::println, () -> {
            System.out.println("Valoare lipsa");
        });
    }

    public static void verificare_duplicate(Set<InstrumentMuzical> set_instrumente) {
        var it = set_instrumente.iterator();
        InstrumentMuzical instrument = it.next();
        Set<InstrumentMuzical> set_instrumente_aux = new HashSet<>(set_instrumente);

        set_instrumente.add(instrument);

        if(set_instrumente_aux.size() == set_instrumente.size()) {
            System.out.println("Colectia set NU permite duplicate");
        }
        else {
            System.out.println("Colectia set PERMITE duplicate");
        }

    }

    public static void main(String[] args) {
        Set<InstrumentMuzical> set_instrumente = new HashSet<InstrumentMuzical>();

        set_instrumente.add(new Chitara("Fender", 2500, TipChitara.valueOf("ELECTRICA"), 6));
        set_instrumente.add(new Chitara("Ibanez", 3000, TipChitara.valueOf("ELECTRICA"), 7));
        set_instrumente.add(new Chitara("Takamine", 1200, TipChitara.valueOf("ACUSTICA"), 6));
        set_instrumente.add(new SetTobe("Yamaha", 3000, TipTobe.valueOf("ACUSTICE"), 4,2));
        set_instrumente.add(new SetTobe("Ibanez", 6700, TipTobe.valueOf("ACUSTICE"), 6,3));
        set_instrumente.add(new SetTobe("Fender", 1000, TipTobe.valueOf("ELECTRONICE"), 6,4));

        scriere(set_instrumente);

        Set<InstrumentMuzical> set_instrumente_fis = new HashSet<InstrumentMuzical>() {};
        afisare_set(set_instrumente_fis);
        set_instrumente_fis = citire();

        System.out.println("--- Setul de intsrumente preluate din \"instrumeteMuzicale.json\" ---");

        set_instrumente_fis.forEach(System.out::println);

        //5. Se verifica daca colectia Set permite sau nu duplicate
        System.out.println("\n--- Se verifica daca colectia Set permite sau nu duplicate ---");
        verificare_duplicate(set_instrumente_fis);

        //6
        System.out.println("\n--- Se sterg toate instrumentele din set  al caror pret este mai mare de 3000 ---");
        set_instrumente_fis.removeIf((a) -> a.getPret()>3000);
        set_instrumente_fis.forEach(System.out::println);

        //7
        System.out.println("\n--- Afisarea datelor Chitarilor cu Stream API si operatorul instanceOf ---");
        set_instrumente_fis
                .stream()
                .filter((a) -> a instanceof Chitara)
                .forEach(System.out::println);

        //8
        System.out.println("\n--- Afisarea datelor Tobelor cu Stream API si metoda getClass() ---");
        set_instrumente_fis
                .stream()
                .filter((a) -> a.getClass() == SetTobe.class)
                .forEach(System.out::println);

        //9
        System.out.println("\n--- Afisarea chitarii cu cele mai multe corzi ---");
        Optional<InstrumentMuzical> opt_chitara_min_corzi =
                set_instrumente_fis
                        .stream()
                        .filter((a)->a instanceof Chitara)
                        .max(Comparator.comparing((a)->((Chitara)a).getNr_corzi()));
        opt_chitara_min_corzi.ifPresent(System.out::println);

        //10
        System.out.println("\n--- Afisarea datelor tobelor ordonate dupa nr de tobe din set ---");
        set_instrumente_fis
                .stream()
                .filter((a)->a instanceof SetTobe)
                .sorted((a, b) -> ((SetTobe) a).getNr_tobe()-((SetTobe) b).getNr_tobe())
                .forEach(System.out::println);



    }
}
