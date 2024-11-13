package org.Lab7.ex1;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class MainApp {
    public static Map<Integer, Carte>  citire() {
        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<Integer, Carte> carti_map = mapper.readValue(new File("src/main/resources/carti.json"), new TypeReference<Map<Integer, Carte>>(){});
            return carti_map;
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void scriere(Map<Integer, Carte> carti) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File fis = new File("src/main/resources/carti.json");
            mapper.writeValue(fis, carti);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void afisare(Map<Integer, Carte> carti_map) {
        var entryset = carti_map.entrySet();

        var it = entryset.iterator();

        while (it.hasNext()) {
            var m = it.next();
            System.out.println(m.getKey() + " : " + m.getValue());
        }
        System.out.println();
    }

    public static void stergere(Map<Integer, Carte> carti_map, int id) {
        var entryset = carti_map.entrySet();
        var it = entryset.iterator();
        while (it.hasNext()) {
            var m = it.next();
            if(m.getKey() == id) {
                it.remove();
            }
        }
    }
    public static void main(String[] args) {
        Map<Integer, Carte> carti_map = new HashMap<Integer, Carte>();
        carti_map = citire();

        //1. Afisarea cartilor
        afisare(carti_map);

        //2. Adaugarea unei carti
        System.out.println("--- Introducerea unei carti ---");

        Scanner scanner = new Scanner(System.in);
        boolean sw = false;
        do {
            try {
                int id;
                String titlul;
                String autorul;
                int anul;

                System.out.print("id: ");
                id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("titlul: ");
                titlul = scanner.nextLine();
                System.out.print("autorul: ");
                autorul = scanner.nextLine();
                System.out.print("anul: ");
                anul = scanner.nextInt();
                scanner.nextLine();
                carti_map.putIfAbsent(id, new Carte(titlul, autorul, anul));

                sw = true;
            } catch(InputMismatchException e) {
                System.out.println("Intrare invalida");
                scanner.nextLine();
            }
        } while (!sw);


        afisare(carti_map);

        //2. Stergerea unei inregistrari

        System.out.println("--- Stergerea unei inregistrari ---");
        sw = false;

        do {
            try {
                int id;
                System.out.print("id: ");
                id = scanner.nextInt();
                scanner.nextLine();

                stergere(carti_map, id);

                sw = true;
            }
            catch(InputMismatchException e) {
                System.out.println("Intrare invalida");
                scanner.nextLine();
            }
        } while(!sw);

        afisare(carti_map);


        //4. Salvarea modificarilor
        scriere(carti_map);
        System.out.println("Modificarile au fost salvate in fisier");
        scanner.nextLine();

        //5. Crearea unui Set<Carte>

       Set<Carte> carti_set =
               carti_map.values()
                       .stream()
                       .filter((a)->a.autorul().equals("Yuval Noah Harari"))
                       .collect(Collectors.toSet());

       System.out.println("\n--- Setul de carti scrise de autorul Yuval Noah Harari ---");
       carti_set.forEach(System.out::println);


       //6. Afisarea ordonata dupa titlu din colectia Set
        System.out.println("\n--- Afisarea ordonata dupa titlu din colectia Set ---");
        carti_set
                .stream()
                .sorted((a, b)-> a.titlul().compareTo(b.titlul()))
                .forEach(System.out::println);

        //7. Afisarea celor mai vechi carti
        System.out.println("\n--- Afisarea celor mai vechi carti ---");
        Optional<Carte> carte_veche = carti_set
                .stream()
                .min(Comparator.comparing(Carte::anul));

        if(carte_veche.isPresent()) {
            carti_set
                    .stream()
                    .filter((a) -> a.anul() == carte_veche.get().anul())
                    .forEach(System.out::println);
        }
        else {
            System.out.println("Cartea nu exista");
        }


    }
}
