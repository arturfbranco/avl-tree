package src.Tree;

import src.Key.Key;
import src.Logger;
import src.Person;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Key key;

    private List<Person> values;
    private Node left;
    private Node right;
    private int height;
    private final Logger logger;

    public Node(Key key){
        this.key = key;
        this.values = new ArrayList<>();
        this.left = null;
        this.right = null;
        this.height = 1;
        this.logger = new Logger();
    }

    public static int getNodeHeight(Node node){
        if(node == null){
            return 0;
        }
        return node.getHeight();
    }

    public void updateHeight(){
        int leftSubtreeHeight = Node.getNodeHeight(this.getLeft());
        int rightSubtreeHeight = Node.getNodeHeight(this.getRight());
        int height = Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1;
        logger.log("Updating height of node with key " + this.key + " from " + this.height + " to " + height + ".");
        this.setHeight(height);
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Person> getValues() {
        return values;
    }

    public void setValues(List<Person> values) {
        this.values = values;
    }
}
