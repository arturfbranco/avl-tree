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
import java.util.Locale;
import java.util.Scanner;

import static src.Util.buildPerson;

public class Main {

    public static Tree tree;

    public static void main(String[] args) {
        boolean shouldContinue = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o caminho do arquivo:");
        String filename = scanner.nextLine();
        PersonRepository repository = new PersonRepository();
        readCsvFile(filename, repository);

        while (shouldContinue){
            System.out.println("\n\nComandos:");
            System.out.println("C - buscar por CPF");
            System.out.println("N - buscar por parte de nome");
            System.out.println("D - buscar por intervalo de data");

            String command = scanner.nextLine();

            command = command.toUpperCase();

            List<Person> pessoas = new ArrayList<>();
            switch (command) {
                case "C":
                    System.out.println("Digite o CPF para busca:");
                    String cpf = scanner.nextLine();
                    Person personByCpf = repository.findByCpf(cpf);
                    if (personByCpf != null) {
                        pessoas.add(personByCpf);
                    }
                    break;
                case "N":
                    System.out.println("Digite a parte do nome para busca:");
                    String nome = scanner.nextLine();
                    List<Person> personsByName = repository.findByPrefix(nome);
                    if (personsByName != null && !personsByName.isEmpty()) {
                        pessoas.addAll(personsByName);
                    }
                    break;
                case "D":
                    System.out.println("Digite a data de início no formato dd/mm/YYYY:");
                    String dataInicial = scanner.nextLine();
                    System.out.println("Digite a data final no formato dd/mm/YYYY:");
                    String dataFinal = scanner.nextLine();
                    List<Person> personsByDate = repository.findByBirthDateRange(dataInicial, dataFinal);
                    if (personsByDate != null && !personsByDate.isEmpty()) {
                        pessoas.addAll(personsByDate);
                    }
                    break;
                default:
                    shouldContinue = false;
            }

            if (!pessoas.isEmpty()) {
                printPeople(pessoas);
                System.out.println("Digite qualquer coisa para continuar...");
                scanner.nextLine();
            }
            pessoas.clear();
        }
    }

    private static void printPeople(List<Person> people) {
        System.out.println("\n");
        for (Person person: people) {
            System.out.println(person.toString());
        }

    }

    public static void readCsvFile(String filename, PersonRepository repository) {
        try (BufferedReader b = new BufferedReader(new FileReader(filename))) {
            String linha;
            while ((linha = b.readLine()) != null) {
                String[] pessoaSplit = linha.split(";");
                Person person = buildPerson(pessoaSplit[0], pessoaSplit[1], pessoaSplit[2], pessoaSplit[3], pessoaSplit[4]);
                repository.save(person);
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }


    public static void test(){
        PersonRepository repo = new PersonRepository();
        repo.save(buildPerson("20", "1", "Daniel", "19/03/1981", "Teresina"));
        repo.save(buildPerson("30", "2", "Lucas", "20/05/1990", "São Paulo"));
        repo.save(buildPerson("25", "3", "Mariana", "12/09/1985", "Rio de Janeiro"));
        repo.save(buildPerson("40", "4", "Juliana", "03/11/1978", "Porto Alegre"));
        repo.save(buildPerson("22", "5", "Fernando", "15/07/1999", "Salvador"));
        repo.save(buildPerson("35", "6", "Carolina", "08/02/1992", "Brasília"));
        repo.save(buildPerson("28", "7", "Pedro", "21/06/1995", "Belo Horizonte"));
        repo.save(buildPerson("33", "8", "Camila", "27/04/1988", "Curitiba"));
        repo.save(buildPerson("23", "9", "Gustavo", "10/10/1998", "Manaus"));
        repo.save(buildPerson("37", "10", "Isabela", "02/12/1991", "Recife"));
        repo.save(buildPerson("26", "11", "Rafael", "05/09/1997", "Fortaleza"));
        repo.save(buildPerson("45", "12", "Amanda", "14/03/1976", "Vitória"));
        repo.save(buildPerson("29", "13", "Larissa", "18/08/1994", "Porto Velho"));
        repo.save(buildPerson("31", "14", "Giovanni", "25/01/1993", "Florianópolis"));
        repo.save(buildPerson("21", "15", "Lívia", "07/07/2000", "Natal"));
        repo.save(buildPerson("27", "16", "Thiago", "09/11/1996", "João Pessoa"));
        repo.save(buildPerson("39", "17", "Manuela", "16/06/1979", "Aracaju"));
        repo.save(buildPerson("24", "18", "Vinicius", "28/12/1999", "Cuiabá"));
        repo.save(buildPerson("32", "19", "Gabriela", "23/04/1992", "Teresina"));
        repo.save(buildPerson("38", "20", "Rodrigo", "11/01/1991", "São Luís"));
        repo.save(buildPerson("34", "21", "Leticia", "06/03/1987", "Campo Grande"));
        repo.save(buildPerson("36", "22", "Alexandre", "22/10/1989", "Belém"));
        repo.save(buildPerson("42", "23", "Marcela", "17/09/1977", "Goiania"));
        repo.save(buildPerson("41", "24", "Ricardo", "24/02/1978", "Maceió"));
        repo.save(buildPerson("43", "25", "Patricia", "30/07/1975", "Porto Alegre"));
        repo.save(buildPerson("44", "26", "Fernanda", "13/06/1980", "Salvador"));
        repo.save(buildPerson("46", "27", "Roberto", "02/04/1974", "Brasília"));
        repo.save(buildPerson("47", "28", "Aline", "09/12/1983", "Belo Horizonte"));
        repo.save(buildPerson("48", "29", "Rafaela", "28/03/1986", "Curitiba"));
        repo.save(buildPerson("49", "30", "Marcelo", "19/10/1973", "Manaus"));
        repo.save(buildPerson("50", "31", "Simone", "14/09/1972", "Recife"));
        repo.save(buildPerson("51", "32", "Luciana", "25/07/1984", "Fortaleza"));
        repo.save(buildPerson("52", "33", "Paulo", "06/06/1982", "Vitória"));
        repo.save(buildPerson("53", "34", "Jessica", "11/05/1989", "Porto Velho"));
        repo.save(buildPerson("54", "35", "Bruno", "30/01/1976", "Florianópolis"));
        repo.save(buildPerson("55", "36", "Isadora", "09/08/1993", "Natal"));
        repo.save(buildPerson("56", "37", "Marcos", "18/07/1979", "João Pessoa"));
        repo.save(buildPerson("57", "38", "Renata", "01/11/1988", "Aracaju"));
        repo.save(buildPerson("58", "39", "Diego", "05/12/1997", "Cuiabá"));
        repo.save(buildPerson("59", "40", "Bianca", "23/09/1985", "São Luís"));
        repo.save(buildPerson("60", "41", "Raphael", "27/02/1994", "Campo Grande"));
        repo.save(buildPerson("61", "42", "Laura", "15/04/1991", "Belém"));
        repo.save(buildPerson("62", "43", "João", "10/03/1988", "Goiania"));
        repo.save(buildPerson("63", "44", "Sandra", "12/05/1983", "Maceió"));
        repo.save(buildPerson("64", "45", "Victor", "09/02/1996", "Porto Alegre"));
        repo.save(buildPerson("65", "46", "Amanda", "07/06/1990", "Salvador"));
        repo.save(buildPerson("66", "47", "Luiz", "23/09/1987", "Brasília"));
        repo.save(buildPerson("67", "48", "Gabriel", "21/12/1992", "Belo Horizonte"));
        repo.save(buildPerson("68", "49", "Carla", "13/08/1984", "Curitiba"));
        repo.save(buildPerson("69", "50", "Raul", "16/07/1995", "Manaus"));
        repo.save(buildPerson("70", "51", "Mariana", "18/11/1976", "Recife"));
        repo.save(buildPerson("71", "52", "Fábio", "24/01/1983", "Fortaleza"));
        repo.save(buildPerson("72", "53", "Alessandra", "20/07/1994", "Vitória"));
        repo.save(buildPerson("73", "54", "Eduardo", "28/09/1978", "Porto Velho"));
        repo.save(buildPerson("74", "55", "Tatiana", "03/06/1989", "Florianópolis"));
        repo.save(buildPerson("75", "56", "Hugo", "07/04/1982", "Natal"));
        repo.save(buildPerson("76", "57", "Daniela", "14/03/1991", "João Pessoa"));
        repo.save(buildPerson("77", "58", "Alex", "26/11/1980", "Aracaju"));
        repo.save(buildPerson("78", "59", "Sara", "10/10/1993", "Cuiabá"));
        repo.save(buildPerson("79", "60", "Matheus", "22/12/1987", "São Luís"));
        repo.save(buildPerson("80", "61", "Vivian", "19/09/1986", "Campo Grande"));
        repo.save(buildPerson("81", "62", "Robson", "11/05/1975", "Belém"));
        repo.save(buildPerson("82", "63", "Nathalia", "04/02/1992", "Goiania"));
        repo.save(buildPerson("83", "64", "Leandro", "08/08/1977", "Maceió"));
        repo.save(buildPerson("84", "65", "Júlia", "03/07/1986", "Porto Alegre"));
        repo.save(buildPerson("85", "66", "Renan", "14/06/1998", "Salvador"));
        repo.save(buildPerson("86", "67", "Lorena", "16/04/1991", "Brasília"));
        repo.save(buildPerson("87", "68", "Cristian", "09/11/1983", "Belo Horizonte"));
        repo.save(buildPerson("88", "69", "Marina", "12/09/1988", "Curitiba"));
        repo.save(buildPerson("89", "70", "Thiago", "26/03/1995", "Manaus"));
        repo.save(buildPerson("90", "71", "Ana", "22/10/1980", "Recife"));
        repo.save(buildPerson("91", "72", "Gustavo", "11/08/1993", "Fortaleza"));
        repo.save(buildPerson("92", "73", "Amanda", "06/07/1987", "Vitória"));
        repo.save(buildPerson("93", "74", "Ricardo", "03/06/1982", "Porto Velho"));
        repo.save(buildPerson("94", "75", "Carolina", "18/03/1996", "Florianópolis"));
        repo.save(buildPerson("95", "76", "Guilherme", "27/09/1990", "Natal"));
        repo.save(buildPerson("96", "77", "Fernanda", "25/01/1989", "João Pessoa"));
        repo.save(buildPerson("97", "78", "Diego", "29/07/1992", "Aracaju"));
        repo.save(buildPerson("98", "79", "Mariana", "07/12/1981", "Cuiabá"));
        repo.save(buildPerson("99", "80", "André", "15/02/1979", "São Luís"));
        repo.save(buildPerson("100", "81", "Bianca", "20/11/1986", "Campo Grande"));
        repo.save(buildPerson("101", "82", "Paulo", "24/10/1985", "Belém"));
        repo.save(buildPerson("102", "83", "Lara", "17/04/1977", "Goiania"));
        repo.save(buildPerson("103", "84", "Renato", "08/09/1984", "Maceió"));
        repo.save(buildPerson("104", "85", "Tatiane", "11/07/1993", "Porto Alegre"));
        repo.save(buildPerson("105", "86", "Vinicius", "09/06/1980", "Salvador"));
        repo.save(buildPerson("106", "87", "Gabriela", "28/03/1987", "Brasília"));
        repo.save(buildPerson("107", "88", "Lucas", "12/02/1989", "Belo Horizonte"));
        repo.save(buildPerson("108", "89", "Camila", "16/01/1996", "Curitiba"));
        repo.save(buildPerson("109", "90", "Rafael", "05/08/1975", "Manaus"));
        repo.save(buildPerson("110", "91", "Leticia", "19/07/1988", "Recife"));
        repo.save(buildPerson("111", "92", "Leonardo", "22/06/1983", "Fortaleza"));
        repo.save(buildPerson("112", "93", "Mariana", "13/05/1990", "Vitória"));
        repo.save(buildPerson("113", "94", "Henrique", "09/03/1981", "Porto Velho"));
        repo.save(buildPerson("114", "95", "Aline", "02/02/1994", "Florianópolis"));
        repo.save(buildPerson("115", "96", "Thiago", "10/09/1977", "Natal"));
        repo.save(buildPerson("116", "97", "Gabriela", "16/08/1986", "João Pessoa"));
        repo.save(buildPerson("117", "98", "Ricardo", "01/07/1983", "Aracaju"));
        repo.save(buildPerson("118", "99", "Amanda", "14/06/1995", "Cuiabá"));
        repo.save(buildPerson("119", "100", "Marcelo", "03/11/1976", "São Luís"));
        repo.save(buildPerson("120", "101", "Isabella", "28/09/1987", "Campo Grande"));
        repo.save(buildPerson("121", "102", "Gustavo", "05/08/1989", "Belém"));
        repo.save(buildPerson("122", "103", "Carolina", "19/04/1978", "Goiania"));
        repo.save(buildPerson("123", "104", "Diego", "22/02/1985", "Maceió"));
        repo.save(buildPerson("124", "105", "Larissa", "10/07/1994", "Porto Alegre"));
        repo.save(buildPerson("125", "106", "Bruno", "09/06/1981", "Salvador"));
        repo.save(buildPerson("126", "107", "Patricia", "26/03/1988", "Brasília"));
        repo.save(buildPerson("127", "108", "Rafael", "28/02/1989", "Belo Horizonte"));
        repo.save(buildPerson("128", "109", "Manuela", "01/12/1996", "Curitiba"));
        repo.save(buildPerson("129", "110", "Rodrigo", "06/09/1975", "Manaus"));
        repo.save(buildPerson("130", "111", "Leticia", "20/08/1988", "Recife"));
        repo.save(buildPerson("131", "112", "Marcelo", "25/07/1983", "Fortaleza"));
        repo.save(buildPerson("132", "113", "Fernanda", "13/05/1990", "Vitória"));
        repo.save(buildPerson("133", "114", "Vinicius", "09/04/1981", "Porto Velho"));
        repo.save(buildPerson("134", "115", "Gabriela", "02/03/1994", "Florianópolis"));
        repo.save(buildPerson("135", "116", "Thiago", "11/09/1977", "Natal"));
        repo.save(buildPerson("136", "117", "Juliana", "17/08/1986", "João Pessoa"));
        repo.save(buildPerson("137", "118", "Roberto", "02/07/1983", "Aracaju"));
        repo.save(buildPerson("138", "119", "Amanda", "15/06/1995", "Cuiabá"));
        repo.save(buildPerson("139", "120", "Marcelo", "04/11/1976", "São Luís"));
        repo.save(buildPerson("140", "121", "Isabella", "29/09/1987", "Campo Grande"));
        repo.save(buildPerson("141", "122", "Gustavo", "06/08/1989", "Belém"));
        repo.save(buildPerson("142", "123", "Carolina", "20/04/1978", "Goiania"));
        repo.save(buildPerson("143", "124", "Diego", "23/02/1985", "Maceió"));
        repo.save(buildPerson("144", "125", "Larissa", "11/07/1994", "Porto Alegre"));
        repo.save(buildPerson("145", "126", "Bruno", "10/06/1981", "Salvador"));
        repo.save(buildPerson("146", "127", "Patricia", "27/03/1988", "Brasília"));
        repo.save(buildPerson("147", "128", "Rafael", "01/03/1989", "Belo Horizonte"));
        repo.save(buildPerson("148", "129", "Manuela", "02/12/1996", "Curitiba"));
        repo.save(buildPerson("149", "130", "Rodrigo", "07/09/1975", "Manaus"));
        repo.save(buildPerson("150", "131", "Leticia", "21/08/1988", "Recife"));
        repo.save(buildPerson("151", "132", "Marcelo", "26/07/1983", "Fortaleza"));
        repo.save(buildPerson("152", "133", "Fernanda", "14/05/1990", "Vitória"));
        repo.save(buildPerson("153", "134", "Vinicius", "10/04/1981", "Porto Velho"));
        repo.save(buildPerson("154", "135", "Gabriela", "03/03/1994", "Florianópolis"));
        repo.save(buildPerson("155", "136", "Thiago", "12/09/1977", "Natal"));
        repo.save(buildPerson("156", "137", "Juliana", "18/08/1986", "João Pessoa"));
        repo.save(buildPerson("157", "138", "Roberto", "03/07/1983", "Aracaju"));
        repo.save(buildPerson("158", "139", "Amanda", "16/06/1995", "Cuiabá"));
        repo.save(buildPerson("159", "140", "Marcelo", "05/11/1976", "São Luís"));
        repo.save(buildPerson("160", "141", "Isabella", "30/09/1987", "Campo Grande"));
        repo.save(buildPerson("161", "142", "Gustavo", "07/08/1989", "Belém"));
        repo.save(buildPerson("162", "143", "Carolina", "21/04/1978", "Goiania"));
        repo.save(buildPerson("163", "144", "Diego", "24/02/1985", "Maceió"));
        repo.save(buildPerson("164", "145", "Larissa", "12/07/1994", "Porto Alegre"));
        repo.save(buildPerson("165", "146", "Bruno", "11/06/1981", "Salvador"));
        repo.save(buildPerson("166", "147", "Patricia", "28/03/1988", "Brasília"));
        repo.save(buildPerson("167", "148", "Rafael", "02/03/1989", "Belo Horizonte"));
        repo.save(buildPerson("168", "149", "Manuela", "03/12/1996", "Curitiba"));
        repo.save(buildPerson("169", "150", "Rodrigo", "08/09/1975", "Manaus"));
        repo.save(buildPerson("170", "151", "Leticia", "22/08/1988", "Recife"));
        repo.save(buildPerson("171", "152", "Marcelo", "27/07/1983", "Fortaleza"));
        repo.save(buildPerson("172", "153", "Fernanda", "15/05/1990", "Vitória"));
        repo.save(buildPerson("173", "154", "Vinicius", "11/04/1981", "Porto Velho"));
        repo.save(buildPerson("174", "155", "Gabriela", "04/03/1994", "Florianópolis"));
        repo.save(buildPerson("175", "156", "Thiago", "13/09/1977", "Natal"));
        repo.save(buildPerson("176", "157", "Juliana", "19/08/1986", "João Pessoa"));
        repo.save(buildPerson("177", "158", "Roberto", "04/07/1983", "Aracaju"));
        repo.save(buildPerson("178", "159", "Amanda", "17/06/1995", "Cuiabá"));
        repo.save(buildPerson("179", "160", "Marcelo", "06/11/1976", "São Luís"));
        repo.save(buildPerson("180", "161", "Isabella", "01/10/1987", "Campo Grande"));
        repo.save(buildPerson("181", "162", "Gustavo", "08/08/1989", "Belém"));
        repo.save(buildPerson("182", "163", "Carolina", "22/04/1978", "Goiania"));
        repo.save(buildPerson("183", "164", "Diego", "25/02/1985", "Maceió"));
        repo.save(buildPerson("184", "165", "Larissa", "13/07/1994", "Porto Alegre"));
        repo.save(buildPerson("185", "166", "Bruno", "12/06/1981", "Salvador"));
        repo.save(buildPerson("186", "167", "Patricia", "29/03/1988", "Brasília"));
        repo.save(buildPerson("187", "168", "Rafael", "03/03/1989", "Belo Horizonte"));
        repo.save(buildPerson("188", "169", "Manuela", "04/12/1996", "Curitiba"));
        repo.save(buildPerson("189", "170", "Rodrigo", "09/09/1975", "Manaus"));
        repo.save(buildPerson("190", "171", "Leticia", "23/08/1988", "Recife"));
        repo.save(buildPerson("191", "172", "Marcelo", "28/07/1983", "Fortaleza"));
        repo.save(buildPerson("192", "173", "Fernanda", "16/05/1990", "Vitória"));
        repo.save(buildPerson("193", "174", "Vinicius", "12/04/1981", "Porto Velho"));
        repo.save(buildPerson("194", "175", "Gabriela", "05/03/1994", "Florianópolis"));
        repo.save(buildPerson("195", "176", "Thiago", "14/09/1977", "Natal"));
        repo.save(buildPerson("196", "177", "Juliana", "20/08/1986", "João Pessoa"));
        repo.save(buildPerson("197", "178", "Roberto", "05/07/1983", "Aracaju"));
        repo.save(buildPerson("198", "179", "Amanda", "18/06/1995", "Cuiabá"));
        repo.save(buildPerson("199", "180", "Marcelo", "07/11/1976", "São Luís"));
        repo.save(buildPerson("200", "181", "Isabella", "02/10/1987", "Campo Grande"));
        repo.save(buildPerson("201", "182", "Gustavo", "09/08/1989", "Belém"));
        repo.save(buildPerson("202", "183", "Carolina", "23/04/1978", "Goiania"));
        repo.save(buildPerson("203", "184", "Diego", "26/02/1985", "Maceió"));
        repo.save(buildPerson("204", "185", "Larissa", "14/07/1994", "Porto Alegre"));
        repo.save(buildPerson("205", "186", "Bruno", "13/06/1981", "Salvador"));
        repo.save(buildPerson("206", "187", "Patricia", "30/03/1988", "Brasília"));
        repo.save(buildPerson("207", "188", "Rafael", "04/03/1989", "Belo Horizonte"));
        repo.save(buildPerson("208", "189", "Manuela", "05/12/1996", "Curitiba"));
        repo.save(buildPerson("209", "190", "Rodrigo", "10/09/1975", "Manaus"));
        repo.save(buildPerson("210", "191", "Leticia", "24/08/1988", "Recife"));
        repo.save(buildPerson("211", "192", "Marcelo", "29/07/1983", "Fortaleza"));
        repo.save(buildPerson("212", "193", "Fernanda", "17/05/1990", "Vitória"));
        repo.save(buildPerson("213", "194", "Vinicius", "13/04/1981", "Porto Velho"));
        repo.save(buildPerson("214", "195", "Gabriela", "06/03/1994", "Florianópolis"));
        repo.save(buildPerson("215", "196", "Thiago", "15/09/1977", "Natal"));
        repo.save(buildPerson("216", "197", "Juliana", "21/08/1986", "João Pessoa"));
        repo.save(buildPerson("217", "198", "Roberto", "06/07/1983", "Aracaju"));
        repo.save(buildPerson("218", "199", "Amanda", "19/06/1995", "Cuiabá"));
        repo.save(buildPerson("219", "200", "Marcelo", "08/11/1976", "São Luís"));
        repo.save(buildPerson("220", "201", "Isabella", "03/10/1987", "Campo Grande"));
        repo.save(buildPerson("221", "202", "Gustavo", "10/08/1989", "Belém"));
        repo.save(buildPerson("222", "203", "Carolina", "24/04/1978", "Goiania"));
        repo.save(buildPerson("223", "204", "Diego", "27/02/1985", "Maceió"));
        repo.save(buildPerson("224", "205", "Larissa", "15/07/1994", "Porto Alegre"));
        repo.save(buildPerson("225", "206", "Bruno", "14/06/1981", "Salvador"));
        repo.save(buildPerson("226", "207", "Patricia", "31/03/1988", "Brasília"));
        repo.save(buildPerson("227", "208", "Rafael", "05/03/1989", "Belo Horizonte"));
        repo.save(buildPerson("228", "209", "Manuela", "06/12/1996", "Curitiba"));
        repo.save(buildPerson("229", "210", "Rodrigo", "11/09/1975", "Manaus"));
        repo.save(buildPerson("230", "211", "Leticia", "25/08/1988", "Recife"));
        repo.save(buildPerson("231", "212", "Marcelo", "30/07/1983", "Fortaleza"));
        repo.save(buildPerson("232", "213", "Fernanda", "18/05/1990", "Vitória"));
        repo.save(buildPerson("233", "214", "Vinicius", "14/04/1981", "Porto Velho"));
        repo.save(buildPerson("234", "215", "Gabriela", "07/03/1994", "Florianópolis"));
        repo.save(buildPerson("235", "216", "Thiago", "16/09/1977", "Natal"));
        repo.save(buildPerson("236", "217", "Juliana", "22/08/1986", "João Pessoa"));
        repo.save(buildPerson("237", "218", "Roberto", "07/07/1983", "Aracaju"));
        repo.save(buildPerson("238", "219", "Amanda", "20/06/1995", "Cuiabá"));
        repo.save(buildPerson("239", "220", "Marcelo", "09/11/1976", "São Luís"));
        repo.save(buildPerson("240", "221", "Isabella", "04/10/1987", "Campo Grande"));
        repo.save(buildPerson("241", "222", "Gustavo", "11/08/1989", "Belém"));
        repo.save(buildPerson("242", "223", "Carolina", "25/04/1978", "Goiania"));
        repo.save(buildPerson("243", "224", "Diego", "28/02/1985", "Maceió"));
        repo.save(buildPerson("244", "225", "Larissa", "16/07/1994", "Porto Alegre"));
        repo.save(buildPerson("245", "226", "Bruno", "15/06/1981", "Salvador"));
        repo.save(buildPerson("246", "227", "Patricia", "01/04/1988", "Brasília"));
        repo.save(buildPerson("247", "228", "Rafael", "06/03/1989", "Belo Horizonte"));
        repo.save(buildPerson("248", "229", "Manuela", "07/12/1996", "Curitiba"));
        repo.save(buildPerson("249", "230", "Rodrigo", "12/09/1975", "Manaus"));
        repo.save(buildPerson("250", "231", "Leticia", "26/08/1988", "Recife"));
        repo.save(buildPerson("251", "232", "Marcelo", "31/07/1983", "Fortaleza"));
        repo.save(buildPerson("252", "233", "Fernanda", "19/05/1990", "Vitória"));
        repo.save(buildPerson("253", "234", "Vinicius", "15/04/1981", "Porto Velho"));
        repo.save(buildPerson("254", "235", "Gabriela", "08/03/1994", "Florianópolis"));
        repo.save(buildPerson("255", "236", "Thiago", "17/09/1977", "Natal"));
        repo.save(buildPerson("256", "237", "Juliana", "23/08/1986", "João Pessoa"));
        repo.save(buildPerson("257", "238", "Roberto", "08/07/1983", "Aracaju"));
        repo.save(buildPerson("258", "239", "Amanda", "21/06/1995", "Cuiabá"));
        repo.save(buildPerson("259", "240", "Marcelo", "10/11/1976", "São Luís"));
        repo.save(buildPerson("260", "241", "Isabella", "05/10/1987", "Campo Grande"));
        repo.save(buildPerson("261", "242", "Gustavo", "12/08/1989", "Belém"));
        repo.save(buildPerson("262", "243", "Carolina", "26/04/1978", "Goiania"));
        repo.save(buildPerson("263", "244", "Diego", "01/03/1985", "Maceió"));

        repo.printAllTrees();
        List<Person> inRange = repo.findByPrefix("a");
        System.out.println("Pessoas cujo nome começa com 'a':");
        inRange.forEach(person -> System.out.println(person.toString()));

        List<Person> inRange2 = repo.findByBirthDateRange("01/01/1981", "01/12/1985");
        System.out.println("Pessoas nascidas entre 1 de janeiro de 81 e 1 de dezembro de 85:");
        inRange2.forEach(person -> System.out.println(person.toString()));

        System.out.println("Buscando CPF 40: " + repo.findByCpf("40"));
    }
}
