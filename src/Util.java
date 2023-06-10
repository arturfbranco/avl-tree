package src;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Util {

    public static Person buildPerson(String cpf, String rg, String name, String birth, String city){
        LocalDate birthDate = buildLocalDate(birth);
        return new Person(cpf, rg, name, birthDate, city);
    }

    public static LocalDate buildLocalDate(String date){
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, dateFormatter);
    }
}
