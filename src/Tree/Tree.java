package src.Tree;

import src.Key.Key;
import src.Logger;
import src.Person;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tree {
    private Node root;
    private final TreeBalancer treeBalancer;
    private final Logger logger;
    public Tree(){
        this.root = null;
        this.treeBalancer = new TreeBalancer();
        this.logger = new Logger();
    }

    public void printTreeState(){
        logger.log("Printing tree current state.");
        this.customPrintPreOrder(this.root, "");
    }

    private void customPrintPreOrder(Node root, String tabs){
        if(root != null){
            System.out.println(tabs + root.getKey());
            this.customPrintPreOrder(root.getLeft(), tabs + "    ");
            this.customPrintPreOrder(root.getRight(), tabs + "    ");
        }
    }

    public Node insert(Key key){
        logger.log("Starting insertion process for key: " + key);
         return this.insert(null, this.root, key);
    }
    private Node insert(Node parent, Node root, Key key){
        Node savedNode = null;
        if(this.root == null){
            logger.log("Tree root is null. Inserting " + key + " as root.");
            this.root = new Node(key);
            savedNode = this.root;
        } else if(root.getKey().equal(key)){
            logger.log("Key already exists in the tree. Aborting operation.");
        } else {
            logger.log("Comparing new key " + key + " with node key " + root.getKey() + ".");
            if(key.lesserThan(root.getKey())){
                if(root.getLeft() == null){
                    logger.log("Left node is null. Inserting " + key + ".");
                    Node newNode = new Node(key);
                    root.setLeft(newNode);
                    savedNode = newNode;
                } else{
                    logger.log("Left node is not null. Visiting left child...");
                    savedNode = this.insert(root, root.getLeft(), key);
                }
            } else {
                if(root.getRight() == null){
                    logger.log("Right node is null. Inserting " + key + ".");
                    Node newNode = new Node(key);
                    root.setRight(newNode);
                    savedNode = newNode;
                } else {
                    logger.log("Right node is not null. Visiting right child...");
                    savedNode = this.insert(root, root.getRight(), key);
                }
            }
            if(savedNode != null){
                root.updateHeight();
                Node newRoot = treeBalancer.balance(parent, root);
                if(parent == null && newRoot != null){
                    this.root = newRoot;
                }
            }

        }
        return savedNode;
    }

    public List<Node> findInRange(Key startKey, Key finishKey){
        logger.log("Start: " + startKey + " Finish: " + finishKey);
        List<Node> foundNodes = new ArrayList<>();
        this.findInRangeRecursive(foundNodes, startKey, finishKey, this.root);
        return foundNodes;
    }

    private void findInRangeRecursive(List<Node> foundNodes, Key startKey, Key finishKey, Node root){
        if(root == null){
            return;
        }
        boolean isStartEqualRoot = startKey.equal(root.getKey());
        boolean isFinishEqualRoot = finishKey.equal(root.getKey());
        boolean inRange = startKey.lesserThan(root.getKey()) && finishKey.greaterThan(root.getKey()) || isStartEqualRoot || isFinishEqualRoot;
        logger.log("Evaluating node: " + root.getKey());
        if(inRange){
            logger.log("Value " + root.getKey().toString() + " in in range of given values. Going to both children and adding root to result.");
            this.findInRangeRecursive(foundNodes, startKey, finishKey, root.getLeft());
            foundNodes.add(root);
            this.findInRangeRecursive(foundNodes, startKey, finishKey, root.getRight());
        } else if(startKey.greaterThan(root.getKey())){
            logger.log("Value " + root.getKey().toString() + " is lesser than start key. Visiting only right node.");
            this.findInRangeRecursive(foundNodes, startKey, finishKey, root.getRight());
        } else if(finishKey.lesserThan(root.getKey())){
            logger.log("Value " + root.getKey().toString() + " is greater than finish key. Visiting only left node.");
            this.findInRangeRecursive(foundNodes, startKey, finishKey, root.getLeft());
        }
    }
    public Node find(Key key){
        logger.log("Looking for node with key " + key + ".");
        return this.find(this.root, key);
    }
    private Node find (Node root, Key key){
        if(root == null){
            System.out.println("Key not found. Returning null instead.");
            return null;
        } else if(key.equal(root.getKey())){
            logger.log("Key found: " + root.getKey() + ".");
            return root;
        } else {
            logger.log("Comparing " + key + " with node key " + root.getKey() + ".");
            if(key.lesserThan(root.getKey())){
                logger.log("Visiting node left child.");
                return this.find(root.getLeft(), key);
            } else {
                logger.log("Visiting node right child.");
                return this.find(root.getRight(), key);
            }
        }

    }

    public void print(PrintOrder printOrder){
        switch (printOrder){
            case PRE_ORDER:
                System.out.println("Tree keys in Pre-order:");
                this.printAllPreOrder(this.root);
                break;
            case IN_ORDER:
                System.out.println("Tree keys in In-Order:");
                this.printAllInOrder(this.root);
                break;
            case POST_ORDER:
                System.out.println("Tree keys in Post-Order:");
                this.printAllPostOrder(this.root);
                break;
            case ALL:
                System.out.println("\nTree keys in Pre-order:");
                this.printAllPreOrder(this.root);
                System.out.println("\nTree keys in In-Order:");
                this.printAllInOrder(this.root);
                System.out.println("\nTree keys in Post-Order:");
                this.printAllPostOrder(this.root);
                break;
            default:
                System.out.println("Invalid PrintOrder value.");
        }
        System.out.print("\n");
    }

    private void printAllPreOrder(Node root){
        if(root != null){
            System.out.print(root.getKey() + " ");
            this.printAllPreOrder(root.getLeft());
            this.printAllPreOrder(root.getRight());
        }
    }
    private void printAllInOrder(Node root){
        if(root != null){
            this.printAllInOrder(root.getLeft());
            System.out.print(root.getKey() + " ");
            this.printAllInOrder(root.getRight());
        }
    }

    private void printAllPostOrder(Node root){
        if(root != null){
            this.printAllPostOrder(root.getLeft());
            this.printAllPostOrder(root.getRight());
            System.out.print(root.getKey() + " ");
        }
    }

    public void remove(Key key){
        logger.log("Starting removal process of node with key " + key + ".");
        this.remove(this.root, key);
    }
    private boolean remove(Node root, Key key){
        boolean isSuccessful;
        if(root == null){
            logger.log("Key not found.");
            isSuccessful = false;
        } else if(root.getKey() == this.root.getKey() && key == root.getKey()){
            logger.log("Node with key " + key + " found in tree root. Starting removal of node...");
            this.processTreeRootRemoval();
            isSuccessful = true;
        } else {
            Node parent = root;
            Node currentNode;
            ChildSide childSide;
            if(key.lesserThan(parent.getKey())){
                childSide = ChildSide.LEFT;
                currentNode = parent.getLeft();
            } else {
                childSide = ChildSide.RIGHT;
                currentNode = parent.getRight();
            }
            if(currentNode == null){
                System.out.println("Key not found.");
                isSuccessful = false;
            } else {
                logger.log("Comparing key " + key + " with key node " + currentNode.getKey() + ".");
                if(key.equal(currentNode.getKey())){
                    logger.log("Key found. Starting removal of node...");
                    this.processRemoval(parent, childSide);
                    isSuccessful = true;
                } else {
                    isSuccessful = this.remove(currentNode, key);
                }
            }
            if(isSuccessful){
                parent.updateHeight();
                this.treeBalancer.balance(parent, currentNode);
            }
        }
        return isSuccessful;
    }

    private void processTreeRootRemoval(){
        if(this.root.getLeft() == null && this.root.getRight() == null){
            logger.log("Root has no children. Setting it to null.");
            this.root = null;
        } else if(this.root.getLeft() != null && this.root.getRight() != null){
            logger.log("Root has 2 children. Starting Copy removal process...");
            this.processCopyRemoval(this.root);
        } else {
            if(this.root.getLeft() != null){
                logger.log("Setting left child " + this.root.getLeft().getKey() + " as tree root.");
                this.root = this.root.getLeft();
            } else {
                logger.log("Setting right child " + this.root.getRight().getKey() + " as tree root.");
                this.root = this.root.getRight();
            }
        }
    }

    private void processRemoval(Node parent, ChildSide childSide){
        Node targetNode;
        if(childSide.equals(ChildSide.LEFT)){
            targetNode = parent.getLeft();
        } else {
            targetNode = parent.getRight();
        }
        logger.log("Removing target node " + targetNode.getKey() + "...");
        if(targetNode.getLeft() == null && targetNode.getRight() == null){
            logger.log("Target node is leaf. Starting leaf removal process...");
            this.leafNodeRemoval(parent, childSide);
        } else if(targetNode.getLeft() != null && targetNode.getRight() != null){
            logger.log("Node has two children. Starting two children node removal process...");
            this.twoChildrenNodeRemoval(parent, childSide);
        } else {
            logger.log("Target node has one child. Starting one child node removal process...");
            this.oneChildNodeRemoval(parent, childSide);
        }
    }

    private void twoChildrenNodeRemoval(Node parent, ChildSide childSide){
        Node targetNode;
        if(childSide.equals(ChildSide.LEFT)){
            targetNode = parent.getLeft();
        } else {
            targetNode = parent.getRight();
        }
        this.processCopyRemoval(targetNode);
    }

    private void processCopyRemoval(Node targetNode){
        logger.log("Starting copy removal process for target node " + targetNode.getKey() + "...");
        Node leftSubTreeRoot = targetNode.getLeft();
        if(leftSubTreeRoot.getLeft() == null && leftSubTreeRoot.getRight() == null){
            logger.log("Left subtree has only one node. Copying key to target node and removing it.");
            Key newNodeKey = leftSubTreeRoot.getKey();
            List<Person> values = leftSubTreeRoot.getValues();
            this.leafNodeRemoval(targetNode, ChildSide.LEFT);
            targetNode.setKey(newNodeKey);
            targetNode.setValues(values);
        } else if(leftSubTreeRoot.getRight() == null) {
            logger.log("Left subtree root is far right node. Copying it to target node and removing it.");
            Key newNodekey = leftSubTreeRoot.getKey();
            List<Person> values = leftSubTreeRoot.getValues();
            this.oneChildNodeRemoval(targetNode, ChildSide.LEFT);
            targetNode.setKey(newNodekey);
            targetNode.setValues(values);
        } else {
            logger.log("Starting process of copying left subtree far right node...");
            Node leftSubTreeNode = targetNode.getLeft();
            Key newKey = this.processCopyingOfFarRightNode(leftSubTreeNode);
            List<Person> values = leftSubTreeRoot.getValues();
            leftSubTreeNode.updateHeight();
            this.treeBalancer.balance(targetNode, leftSubTreeNode);
            targetNode.setKey(newKey);
            targetNode.setValues(values);
        }
    }

    private Key processCopyingOfFarRightNode(Node parent){
        Node targetNode = parent.getRight();
        if(targetNode.getRight() == null){
            logger.log("Far right node found: " + targetNode.getKey() + ". Copying it to target node.");
            Key newKey = targetNode.getKey();
            this.processRemoval(parent, ChildSide.RIGHT);
            return newKey;
        }
        Key key = this.processCopyingOfFarRightNode(targetNode);
        targetNode.updateHeight();
        this.treeBalancer.balance(parent, targetNode);
        return key;
    }

    private void oneChildNodeRemoval(Node parent, ChildSide childSide){
        Node targetNode;
        if(childSide.equals(ChildSide.LEFT)){
            targetNode = parent.getLeft();
        } else {
            targetNode = parent.getRight();
        }
        Node nodeChild;
        if(targetNode.getLeft() != null){
            nodeChild = targetNode.getLeft();
        } else {
            nodeChild = targetNode.getRight();
        }
        if(childSide.equals(ChildSide.LEFT)){
            logger.log("Setting left child of parent node " + parent.getKey() + " to target node's child " + nodeChild.getKey() + ".");
            parent.setLeft(nodeChild);
        } else {
            logger.log("Setting right child of parent node " + parent.getKey() + " to target node's child " + nodeChild.getKey() + ".");
            parent.setRight(nodeChild);
        }
    }
    private void leafNodeRemoval(Node parent, ChildSide childSide){
        if(childSide.equals(ChildSide.LEFT)){
            logger.log("Setting left child of node " + parent.getKey() + " to null.");
            parent.setLeft(null);
        } else {
            logger.log("Setting right child of node " + parent.getKey() + " to null.");
            parent.setRight(null);
        }
    }

}
