import br.com.alura.tdd.modelo.Calculadora;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

    @Test
    public void shouldAddTwoPositiveNumbers() {
        Calculadora calc = new Calculadora();
        int sum = calc.sum(3, 7);

        Assertions.assertEquals(10, sum);
    }
}
