package teste;

import static org.Lab8.MainApp.verificare_an_excursie;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestLab8 {

    @Nested
    class Test_verificare_an_excursie {
        @Test
        public void test1() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/lab8?useTimezone=true&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url, "root", "root");
            assertFalse(verificare_an_excursie(connection, 2, 2000));
        }

        @Test
        public void test2() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/lab8?useTimezone=true&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url, "root", "root");
            assertTrue(verificare_an_excursie(connection, 2, 2003));
        }

        @Test
        public void test3() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/lab8?useTimezone=true&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url, "root", "root");
            assertTrue(verificare_an_excursie(connection, 2, 2024));
        }

        @Test
        public void test4() throws SQLException {
            String url = "jdbc:mysql://localhost:3306/lab8?useTimezone=true&serverTimezone=UTC";
            Connection connection = DriverManager.getConnection(url, "root", "root");
            assertTrue(verificare_an_excursie(connection, 2, 2004));
        }

    }
}
