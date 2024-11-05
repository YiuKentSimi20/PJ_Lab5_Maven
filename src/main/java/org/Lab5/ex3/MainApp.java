package org.Lab5.ex3;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static java.lang.System.exit;

public class MainApp {
    static void scriere(List<Mobilier> lista) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            File file = new File("src/main/resources/mobilier.json");
            mapper.writeValue(file, lista);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    static List<Mobilier> citire() {
        try {
            File file = new File("src/main/resources/mobilier.json");
            ObjectMapper mapper = new ObjectMapper();
            List<Mobilier> lista = mapper.readValue(file, new TypeReference<List<Mobilier>>() {});
            return lista;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<Mobilier> lista = citire();
        for(Mobilier m : lista){
            System.out.println(m);
        }

        scriere(lista);

        Scanner scanner = new Scanner(System.in);
        int opt;
        int ok;

        while(true){
            System.out.println("0. Iesire");
            System.out.println("1. Afisare");
            System.out.println("2. Afisarea placilor unui anumit mobilier");
            System.out.println("3. Nr de coli de pal cu demensiunea de 2800 x 2070 mm necesare pt realizarea unui anumit corp");
            System.out.print("Option:");
            opt = scanner.nextInt();
            scanner.nextLine();
            String nume_cautat;

            switch(opt){
                case 0:
                    exit(0);

                    break;
                    case 1:
                        for(Mobilier m : lista){
                            System.out.println(m);
                        }
                        System.out.println();
                        break;
                        case 2:
                            ok = 0;
                            System.out.print("Introduceti mobilierul cautat: ");
                            nume_cautat = scanner.nextLine();
                            for(Mobilier m : lista){
                                if(m.getNume().equals(nume_cautat)){
                                    System.out.println(m);
                                    ok = 1;
                                }
                            }
                            if(ok == 0){
                                System.out.println("Obiectul nu a fost gasit\n");
                            }
                            break;
                            case 3:
                                ok = 0;
                                System.out.print("Introduceti mobilierul cautat: ");
                                nume_cautat = scanner.nextLine();
                                for(Mobilier m : lista){
                                    if(m.getNume().equals(nume_cautat)){
                                        System.out.println("Pentru " + m.getNume() + " va fi nevoie de " +
                                                m.NrPlaciDePal() + " placi de pal\n");
                                        ok = 1;
                                    }
                                }
                                if(ok == 0){
                                    System.out.println("Obiectul nu a fost gasit\n");
                                }
                                break;
                                default:
                                    System.out.println("Invalid option");
            }
        }

    }
}
