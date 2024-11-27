package org.Lab8;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.System.exit;

public class MainApp {
    public static void afisare_persoane(Connection connection, String mesaj) throws SQLException {
        String sql = "select * from persoane";
        System.out.println("\n---" + mesaj + "---");
        Statement statement = connection.createStatement();

        try (ResultSet rs = statement.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("id=" + rs.getInt(1) + ", nume=" + rs.getString(2) + ",varsta="
                        + rs.getInt(3));

                afisare_excursii(connection, rs.getInt(1));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void afisare_excursii(Connection connection, int id_persoana) throws SQLException {
        String sql = "select * from excursii where id_persoana = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_persoana);

        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next())
                System.out.println("    id_persoana=" + rs.getInt(1) + ",id_excursie=" + rs.getInt(2) + ",destinatie="
                        + rs.getString(3) + ",anul=" + rs.getInt(4));
        } catch (SQLException
                e) {
            e.printStackTrace();
        }
    }
    public static void afisare_excursii_dupa_nume(Connection connection, String nume) throws SQLException {
        String sql = "select * from persoane where nume = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nume);

        try (ResultSet rs = statement.executeQuery()) {
            while (rs.next())
                afisare_excursii(connection, rs.getInt(1));
        } catch (SQLException
                e) {
            e.printStackTrace();
        }
    }

    public static void adaugare_persoana(Connection connection, String nume, int varsta) {
        String sql = "insert into persoane (nume, varsta) values (?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, nume);
            ps.setInt(2, varsta);
            int nr_randuri = ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de adaugare=" + nr_randuri);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static void adaugare_excursie(Connection connection, int id_persoana, String destinatia, int anul) {
        String sql = "insert into excursii(id_persoana, destinatia, anul) values (?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id_persoana);
            ps.setString(2, destinatia);
            ps.setInt(3, anul);
            int nr_randuri = ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de adaugare=" + nr_randuri);
        } catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static boolean verificare_id_persoana(Connection connection, int id_persoana) throws SQLException {
        String sql = "select count(*) from persoane where id_persoana = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id_persoana);
        ResultSet nr_randuri = statement.executeQuery();
        nr_randuri.next();
        return nr_randuri.getInt(1) != 0;
    }

    public static void afisare_persoane_destinatie(Connection connection, String destinatie) throws SQLException {
        String sql_nr_destinatii = "select count(*) from excursii where destinatia = ? and id_persoana = ?";
        String sql_persoane = "select * from persoane";
        Statement statement = connection.createStatement();
        try (ResultSet rs_persoane = statement.executeQuery(sql_persoane)) {
            while(rs_persoane.next()) {
                PreparedStatement ps = connection.prepareStatement(sql_nr_destinatii);
                ps.setString(1, destinatie);
                ps.setInt(2, rs_persoane.getInt(1));
                ResultSet rs_nr_destinatii = ps.executeQuery();
                rs_nr_destinatii.next();
                if(rs_nr_destinatii.getInt(1) != 0) {
                    System.out.println("id=" + rs_persoane.getInt(1) + ", nume=" + rs_persoane.getString(2) + ",varsta="
                            + rs_persoane.getInt(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void afisare_persoane_an(Connection connection, int anul) throws SQLException {
        String sql_nr_destinatii = "select count(*) from excursii where anul = ? and id_persoana = ?";
        String sql_persoane = "select * from persoane";
        Statement statement = connection.createStatement();
        try (ResultSet rs_persoane = statement.executeQuery(sql_persoane)) {
            while(rs_persoane.next()) {
                PreparedStatement ps = connection.prepareStatement(sql_nr_destinatii);
                ps.setInt(1, anul);
                ps.setInt(2, rs_persoane.getInt(1));
                ResultSet rs_nr_destinatii = ps.executeQuery();
                rs_nr_destinatii.next();
                if(rs_nr_destinatii.getInt(1) != 0) {
                    System.out.println("id=" + rs_persoane.getInt(1) + ", nume=" + rs_persoane.getString(2) + ",varsta="
                            + rs_persoane.getInt(3));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void stergere_persoana(Connection connection,int id){
        String sql="delete from persoane where id_persoana=?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int nr_randuri=ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de stergere="+nr_randuri);
        }
        catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static void stergere_excursie(Connection connection,int id){
        String sql="delete from excursii where id_excursie=?";
        try(PreparedStatement ps=connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            int nr_randuri=ps.executeUpdate();
            System.out.println("\nNumar randuri afectate de stergere="+nr_randuri);
        }
        catch (SQLException e) {
            System.out.println(sql);
            e.printStackTrace();
        }
    }

    public static boolean verificare_an_excursie(Connection connection, int id_persoana, int anul) throws SQLException {
        String sql = "select varsta from persoane where id_persoana = ?";
        PreparedStatement ps_persoana = connection.prepareStatement(sql);
        ps_persoana.setInt(1, id_persoana);
        ResultSet rs_persoana = ps_persoana.executeQuery();
        rs_persoana.next();

        int an_nastere_persoana = LocalDate.now().getYear() - rs_persoana.getInt(1);

        return anul >= an_nastere_persoana;
    }


    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/lab8?useTimezone=true&serverTimezone=UTC";

        Connection connection = DriverManager.getConnection(url, "root", "root");

        final int max_varsta = 100;
        String nume;
        String destinatie;
        int anul;
        int id_persoana;
        int id_excursie;
        boolean sw = false;

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Adaugare persoana");
            System.out.println("2. Adaugare excursie");
            System.out.println("3. Afisarea persoanelor si a excursiilor pt fiecare persoana");
            System.out.println("4. Afisarea excursiilor unei persoane dupa nume citit de la tastatura");
            System.out.println("5. Afisarea persoanelor care au vizitat o anumita destinatie");
            System.out.println("6. Afisarea pers care au fost in excursie intr-un an introdus");
            System.out.println("7. Stergerea unei excursii");
            System.out.println("8. Stergerea unei persoane(si a excursiilor pt fiecare persoana)");
            System.out.println("(0 - Iesire)");
            System.out.print("Optiune: ");


            switch (Integer.parseInt(scanner.nextLine())) {
                case 0:
                    exit(0);
                    break;
                case 1: //Adaugare persoana
                    System.out.print("nume: ");
                    nume = scanner.nextLine();
                    sw = false;
                    do {
                        try {
                            System.out.print("varsta: ");
                            int varsta = Integer.parseInt(scanner.nextLine());

                            if(varsta <= 0 || varsta > max_varsta)
                                throw new ExceptieVarsta("Varsta nu poate fi negativa sau mai mare decat " + max_varsta);

                            adaugare_persoana(connection, nume, varsta);
                            sw = true;
                        } catch(NumberFormatException e) {
                            System.out.println("Intrare invalida");
                        } catch (ExceptieVarsta e){
                            System.out.println(e.getMessage());
                        }
                    } while(!sw);


                    break;
                case 2: //Adaugare excursie
                    System.out.print("id_persoana: ");
                    id_persoana = Integer.parseInt(scanner.nextLine());
                    if(verificare_id_persoana(connection, id_persoana)) {
                        System.out.print("destinatia: ");
                        String destinatia = scanner.nextLine();
                        sw = false;
                        do {
                            try {
                                System.out.print("anul: ");
                                anul = Integer.parseInt(scanner.nextLine());

                                if(anul > LocalDate.now().getYear())
                                    throw new ExceptieAnExcursie("Anul nu poate fi mai mare decat anul curent");

                                if(!verificare_an_excursie(connection, id_persoana, anul))
                                    throw new ExceptieAnExcursie("Anul nu poate fi mai mic decat anul de nastere al presoanei");

                                adaugare_excursie(connection, id_persoana, destinatia, anul);
                                sw = true;
                            } catch(NumberFormatException e) {
                                System.out.println("Intrare invalida");
                            } catch (ExceptieAnExcursie e) {
                                System.out.println(e.getMessage());
                            }
                        } while(!sw);


                    }
                    else System.out.println("Nu exista persoana in tabel");

                    break;
                case 3: //Afisare persoane + excursii
                    afisare_persoane(connection, "Persoane");

                    break;
                case 4:
                    System.out.print("nume: ");
                    nume = scanner.nextLine();
                    System.out.println("\n--- Excursii ---");
                    afisare_excursii_dupa_nume(connection, nume);

                    break;
                case 5:
                    System.out.print("destinatie: ");
                    destinatie = scanner.nextLine();
                    System.out.println("\n--- Persoane care au fost in excursie in " + destinatie + " ---");
                    afisare_persoane_destinatie(connection, destinatie);

                    break;
                case 6:
                    System.out.print("anul: ");
                    anul = Integer.parseInt(scanner.nextLine());
                    afisare_persoane_an(connection, anul);

                    break;
                case 7:
                    System.out.print("id_excursie: ");
                    id_excursie = Integer.parseInt(scanner.nextLine());
                    stergere_excursie(connection, id_excursie);
                    break;
                case 8:
                    System.out.print("id_persoana: ");
                    id_persoana = Integer.parseInt(scanner.nextLine());
                    stergere_persoana(connection, id_persoana);
                    break;
                default:
                    System.out.println("Optiuna nu se afla in meniu");
                    break;
            }
            System.out.println();
        }
    }
}
