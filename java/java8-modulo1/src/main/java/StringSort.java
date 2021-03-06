import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class StringSort {
    public static void main(String[] args) {

        List<String> words = new ArrayList<String>();
        words.add("alura online");
        words.add("editora casa do codigo");
        words.add("caelum");

//        words.sort((s1, s2) -> Integer.compare(s1.length(), s2.length()));

        words.sort(Comparator.comparing(s -> s.length()));
        words.sort(Comparator.comparing(String::length));

        System.out.println(words);

        words.forEach(System.out::println);

        new Thread(() -> System.out.println("Executando um Runnable")).start();
    }
}
