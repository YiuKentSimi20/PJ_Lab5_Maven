package teste;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.ex2.PerecheNumere;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


/**
 * Clasa pentru testarea clasei PerecheNumere
 * @author ILIA Alexandru
 */
public class TestePerechiNumere {


    /**
     * Clasa de teste pentru metoda "consecutiveFibonacci"
     */
    @Nested
    class Teste_consecutiveFibonacci {
        @Test
        void test1_consecutiveFibonacci() {
            PerecheNumere p = new PerecheNumere();
            assertFalse(p.consecutiveFibonacci(0, 1));
        }

        @Test
        void test2_consecutiveFibonacci() {
            PerecheNumere p = new PerecheNumere(1, 2);
            assertTrue(p.consecutiveFibonacci(0, 1) );
        }

        @Test
        void test3_consecutiveFibonacci() {
            PerecheNumere p = new PerecheNumere(3, 44);
            assertFalse(p.consecutiveFibonacci(0, 1) );
        }
    }

    /**
     * Clasa de teste pentru metoda "cmmmc"
     */
    @Nested
    class Teste_cmmmc {
        @Test
        void test1_cmmmc() {
            PerecheNumere p = new PerecheNumere(1, 2);
            assertEquals(2, p.cmmmc());
        }

        @Test
        void test2_cmmmc() {
            PerecheNumere p = new PerecheNumere(11, 13);
            assertEquals(143, p.cmmmc());
        }

        @Test
        void test3_cmmmc() {
            PerecheNumere p = new PerecheNumere(10, 3);
            assertFalse(p.cmmmc() != 30);
        }
    }

    /**
     * Clasa de teste pentru metoda "sumaCifEgala"
     */
    @Nested
    class Teste_sumaCifEgala {
        @Test
        void test1_sumaCifEgala() {
            PerecheNumere p = new PerecheNumere(23, 122);
            assertTrue(p.sumaCifEgala());
        }

        @Test
        void test2_sumaCifEgala() {
            PerecheNumere p = new PerecheNumere(1234, 12);
            assertFalse(p.sumaCifEgala());
        }

        @Test
        void test3_sumaCifEgala() {
            PerecheNumere p = new PerecheNumere(900, 9);
            assertTrue(p.sumaCifEgala());
        }
    }

    /**
     * Clasa de teste pentru metoda "acelasiNrCifPare"
     */
    @Nested
    class Teste_acelasiNrCifPare {

        @Test
        void test1_acelasiNrCifPare() {
            PerecheNumere p = new PerecheNumere(23, 122);
            assertFalse(p.acelasiNrCifPare());
        }

        @Test
        void test2_acelasiNrCifPare() {
            PerecheNumere p = new PerecheNumere(5002, 98663);
            assertTrue(p.acelasiNrCifPare());
        }

        @Test
        void test3_acelasiNrCifPare() {
            PerecheNumere p = new PerecheNumere(9735, 1339);
            assertTrue(p.acelasiNrCifPare());
        }
    }

}
