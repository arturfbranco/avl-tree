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
        repo.save(buildPerson("84759238", "74938592", "Maria", "05/06/1992", "São Paulo"));
        repo.save(buildPerson("58392047", "19385729", "João", "18/09/1985", "Rio de Janeiro"));
        repo.save(buildPerson("73625194", "59284375", "Ana", "25/03/1978", "Belo Horizonte"));
        repo.save(buildPerson("19283746", "83948273", "Pedro", "30/11/1990", "Brasília"));
        repo.save(buildPerson("47589374", "28734958", "Lucas", "10/08/1988", "Salvador"));
        repo.save(buildPerson("98765432", "12345678", "Carolina", "07/04/1995", "Curitiba"));
//        repo.save(buildPerson("67492837", "39284756", "Fernanda", "21/02/1983", "Recife"));
//        repo.save(buildPerson("34892058", "98765432", "Rafael", "15/07/1991", "Fortaleza"));
//        repo.save(buildPerson("58927364", "39485729", "Larissa", "22/10/1987", "Manaus"));
//        repo.getCpfTree().printTreeState();
//        repo.save(buildPerson("83746592", "47589374", "Matheus", "02/03/1993", "Belém")); // todo rotações erradas aqui
//        repo.save(buildPerson("23948576", "98765432", "Isabela", "28/05/1989", "Porto Alegre"));
//        repo.save(buildPerson("38475692", "58927364", "Gustavo", "16/09/1996", "Natal"));
//        repo.save(buildPerson("92837465", "83746592", "Amanda", "09/11/1984", "Vitória"));
//        repo.save(buildPerson("47589374", "23948576", "Juliana", "04/01/1998", "Maceió"));
//        repo.save(buildPerson("98374652", "38475692", "Ricardo", "23/06/1982", "Florianópolis"));
//        repo.save(buildPerson("29837465", "92837465", "Camila", "14/12/1994", "Porto Velho"));
//        repo.save(buildPerson("39485729", "98374652", "Thiago", "01/02/1986", "João Pessoa"));
//        repo.save(buildPerson("92837465", "29837465", "Marcela", "26/10/1997", "Cuiabá"));
//        repo.save(buildPerson("19283746", "39485729", "Daniel", "19/03/1981", "Teresina"));
//        repo.getCpfTree().printTreeState();

        repo.printAllTrees();
        List<Person> inRange = repo.findByPrefix("m");
        inRange.forEach(person -> System.out.println(person.toString()));

//        List<Person> inRange = repo.findByBirthDateRange("01/01/1981", "01/12/1985");
    }
}
