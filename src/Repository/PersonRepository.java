package src.Repository;

import src.Key.Key;
import src.Key.LocalDateKey;
import src.Key.StringKey;
import src.Person;
import src.Tree.Node;
import src.Tree.Tree;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class PersonRepository {

    private final Tree cpfTree;
    private final Tree nameTree;
    private final Tree birthDateTree;

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

    public void save(Person person){
        person.setName(person.getName().toUpperCase());
        try {
            this.saveByCpf(person);
            this.saveByName(person);
            this.saveByBirthDate(person);

        } catch (Exception e) {
            throw new RuntimeException(e);
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
