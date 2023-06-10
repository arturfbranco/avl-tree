package src;

import src.Repository.PersonRepository;
import src.Tree.Tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static src.Util.buildPerson;

public class Main {

    public static Tree tree;

    public static void main(String[] args) {

        test();

//        PersonRepository repository = new PersonRepository();
//        try (BufferedReader b = new BufferedReader(new FileReader(args[0]))) {
//            String linha;
//            while ((linha = b.readLine()) != null) {
//                String[] pessoaSplit = linha.split(";");
//                Person person = buildPerson(pessoaSplit[0], pessoaSplit[1], pessoaSplit[2], pessoaSplit[3], pessoaSplit[4]);
//                repository.save(person);
//            }
//        }
//        catch (Exception e) {
//            e.getStackTrace();
//        }



    }


    public static void test(){
        PersonRepository repo = new PersonRepository();
        repo.save(buildPerson("29384923", "93823984", "Artur", "12/12/1980", "POA"));
        repo.save(buildPerson("24323432", "93823984", "Pedro", "12/12/1981", "POA"));
        repo.save(buildPerson("456456", "93823984", "Artur", "12/12/1985", "POA"));
        repo.save(buildPerson("575684", "93823984", "Joao", "12/12/1990", "POA"));
        repo.save(buildPerson("2342342", "93823984", "Artur", "12/12/1982", "POA"));
        repo.save(buildPerson("1231232", "93823984", "Alex", "12/12/1990", "POA"));
        repo.save(buildPerson("4645747234", "93823984", "Paula", "12/12/1988", "POA"));
        repo.save(buildPerson("293484923", "93823984", "Paula", "12/02/1983", "POA"));
        repo.printAllTrees();

        List<Person> inRange = repo.findByBirthDateRange("01/01/1981", "01/12/1985");
    }
}
