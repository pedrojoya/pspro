import java.util.function.BiFunction;

public class Calculadora {

    public Integer calcular(Integer valor1, Integer valor2, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(valor1, valor2);
    }

}
