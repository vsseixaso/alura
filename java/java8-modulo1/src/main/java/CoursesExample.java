import java.util.*;
import java.util.stream.Collectors;

class Course {
    private String name;
    private int students;

    public Course(String name, int students) {
        this.name = name;
        this.students = students;
   }

    public String getName() {
        return name;
    }

    public int getStudents() {
        return students;
    }
}

public class CoursesExample {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<Course>();
        courses.add(new Course("Python", 45));
        courses.add(new Course("JavaScript", 150));
        courses.add(new Course("Java 8", 113));
        courses.add(new Course("C", 55));

        courses.sort(Comparator.comparing(Course::getStudents));

        OptionalDouble average = courses.stream()
                .filter(c -> c.getStudents() >= 100)
                .mapToInt(Course::getStudents)
                .average();

        courses.stream()
            .filter(c -> c.getStudents() >= 100)
            .findAny()
            .ifPresent(c -> System.out.println(c.getName()));

//        Course course = optionalCourse.orElse(null);

        courses.stream() // courses.parallelStream()
                .filter(c -> c.getStudents() >= 100)
                .collect(Collectors.toMap(
                        c -> c.getName(),
                        c -> c.getStudents()))
                .forEach((name, students) -> System.out.println(name + " tem " + students + " alunos"));

    }
}
