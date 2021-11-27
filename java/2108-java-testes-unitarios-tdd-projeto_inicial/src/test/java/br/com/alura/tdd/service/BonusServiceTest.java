package br.com.alura.tdd.service;

import br.com.alura.tdd.modelo.Funcionario;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BonusServiceTest {

    @Test
    void shouldBeZeroBonusForAnEmployeeWithSalaryGreaterThan10000() {
        BonusService service = new BonusService();
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularBonus(new Funcionario("Vinícius", LocalDate.now(), new BigDecimal("25000"))));

//        try {
//            service.calcularBonus(new Funcionario("Vinícius", LocalDate.now(), new BigDecimal("25000")));
//            fail("Did not throw the exception.");
//        } catch (Exception e) {
//            assertEquals("Employee with salary greater than BRL 10,000 cannot receive a bonus.", e.getMessage());
//        }
    }

    @Test
    void shouldBeAnBounsOfTenPercentOfTheSalary() {
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Vinícius", LocalDate.now(), new BigDecimal("2500")));

        assertEquals(new BigDecimal("250.00"), bonus);
    }

    @Test
    void shouldBeAnBounsOfTenPercentForSalaryOf10000() {
        BonusService service = new BonusService();
        BigDecimal bonus = service.calcularBonus(new Funcionario("Vinícius", LocalDate.now(), new BigDecimal("10000")));

        assertEquals(new BigDecimal("1000.00"), bonus);
    }
}