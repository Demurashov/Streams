import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long num = persons.stream().filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Кол-во людей младше 18-ти:" + num);

        List<String> famList = persons.stream().filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());

        System.out.println("Призывники:");
        for (String people : famList) {
            System.out.println(people);
        }

        List<Person> persList = persons.stream().filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18 && ((person.getAge() <= 65 & person.getSex() == Sex.MAN) || (person.getAge() <= 60 & person.getSex() == Sex.WOMAN)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println("Работоспособные люди:" + persList);
        for (Person person : persList) {
            System.out.println(person);
        }

    }

}