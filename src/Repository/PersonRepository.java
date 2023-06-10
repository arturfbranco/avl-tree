package src.Repository;

import src.Key.Key;
import src.Key.LocalDateKey;
import src.Key.StringKey;
import src.Logger;
import src.Person;
import src.Tree.Node;
import src.Tree.Tree;
import src.Util;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonRepository {

    private final Tree cpfTree;
    private final Tree nameTree;
    private final Tree birthDateTree;

    private final Logger logger = new Logger();

    public PersonRepository() {
        this.cpfTree = new Tree();
        this.nameTree = new Tree();
        this.birthDateTree = new Tree();
    }

    public void printAllTrees(){
        this.cpfTree.printTreeState();
        this.nameTree.printTreeState();
        this.birthDateTree.printTreeState();
    }



    public Person findByCpf(String cpf){
        String treatedCpf = cpf.replace(".", "").replace("-", "");
        Node retrievedNode = this.cpfTree.find(new StringKey(treatedCpf));
        if(retrievedNode != null){
            Person person = retrievedNode.getValues().get(0);
            logger.log("Found person for CPF " + cpf + ": " + person.toString());
            return person;
        }
        logger.log("CPF + " + cpf + " not found.");
        return null;
    }

    public List<Person> findByBirthDateRange(String start, String finish){
        LocalDate startDate = Util.buildLocalDate(start);
        LocalDate finishDate = Util.buildLocalDate(finish);
        List<Node> nodesFound = this.birthDateTree.findInRange(new LocalDateKey(startDate), new LocalDateKey(finishDate));
        List<Person> allPersonsFound = new ArrayList<>();
        for(Node node : nodesFound){
            allPersonsFound.addAll(node.getValues());
        }
        return allPersonsFound;
    }

    public void save(Person person){
        person.setName(person.getName().toUpperCase());
        try {
            this.saveByCpf(person);
            this.saveByName(person);
            this.saveByBirthDate(person);

        } catch (Exception e) {
            logger.log("Error while saving new person. Maybe the person already exists?");
        }

    }

    private void saveByCpf(Person person) throws Exception {
        Key key = new StringKey(person.getCpf().replace(".", "").replace("-", ""));
        Node retrievedPerson = this.cpfTree.find(key);
        if(retrievedPerson != null){
            throw new Exception();
        }
        Node savedNode = this.cpfTree.insert(key);
        if (savedNode == null){
            throw new Exception();
        }
        savedNode.getValues().add(person);
    }

    private void saveByName(Person person) throws Exception {
        Key key = new StringKey(person.getName());
        Node retrievedNode = this.nameTree.find(key);
        if(retrievedNode == null){
            retrievedNode = this.nameTree.insert(key);
            if(retrievedNode == null){
                throw new Exception();
            }
        }
        retrievedNode.getValues().add(person);
    }

    private void saveByBirthDate(Person person) throws Exception {
        Key key = new LocalDateKey(person.getBirth());
        Node retrivedNode = this.birthDateTree.find(key);
        if(retrivedNode == null){
            retrivedNode = this.birthDateTree.insert(key);
            if(retrivedNode == null){
                throw new Exception();
            }
        }
        retrivedNode.getValues().add(person);
    }
}
