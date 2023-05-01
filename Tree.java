public class Tree {
    private Node root;
    private Logger logger;

    public Tree(){
        this.root = null;
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

    public void insert(int key){
        logger.log("Starting insertion process for key: " + key);
        if(this.root == null){
            logger.log("Tree root is null. Inserting " + key + " as root.");
            this.root = new Node(key);
        } else{
            Node currentNode = this.root;
            this.insert(currentNode, key);
        }
    }
    private void insert(Node root, int key){
        if(root.getKey() == key){
            logger.log("Key already exists in the tree. Aborting operation.");
        } else {
            logger.log("Comparing new key " + key + " with node key " + root.getKey() + ".");
            if(key < root.getKey()){
                if(root.getLeft() == null){
                    logger.log("Left node is null. Inserting " + key + ".");
                    Node newNode = new Node(key);
                    root.setLeft(newNode);
                } else{
                    logger.log("Left node is not null. Visiting left child...");
                    this.insert(root.getLeft(), key);
                }
            } else {
                if(root.getRight() == null){
                    logger.log("Right node is null. Inserting " + key + ".");
                    Node newNode = new Node(key);
                    root.setRight(newNode);
                } else {
                    logger.log("Right node is not null. Visiting right child...");
                    this.insert(root.getRight(), key);
                }
            }
        }
    }
    public int find(int key){
        logger.log("Looking for node with key " + key + ".");
        return this.find(this.root, key);
    }
    private int find (Node root, int key){
        if(root == null){
            System.out.println("Key not found. Returning -1 instead.");
            return -1;
        } else if(key == root.getKey()){
            System.out.println("Key found: " + root.getKey() + ".");
            return root.getKey();
        } else {
            logger.log("Comparing " + key + " with node key " + root.getKey() + ".");
            if(key < root.getKey()){
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

    public void remove(int key){
        logger.log("Starting removal process of node with key " + key + ".");
        this.remove(this.root, key);
    }
    private void remove(Node root, int key){
        if(root == null){
            System.out.println("Key not found.");
        } else if(root.getKey() == this.root.getKey() && key == root.getKey()){
            logger.log("Node with key " + key + " found in tree root. Starting removal of node...");
            this.processTreeRootRemoval();
        } else {
            Node parent = root;
            Node currentNode;
            ChildSide childSide;
            if(key < parent.getKey()){
                childSide = ChildSide.LEFT;
                currentNode = parent.getLeft();
            } else {
                childSide = ChildSide.RIGHT;
                currentNode = parent.getRight();
            }
            if(currentNode == null){
                System.out.println("Key not found.");
            } else {
                logger.log("Comparing key " + key + " with key node " + currentNode.getKey() + ".");
                if(key == currentNode.getKey()){
                    logger.log("Key found. Starting removal of node...");
                    this.processRemoval(parent, childSide);
                } else {
                    this.remove(currentNode, key);
                }
            }
        }
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
            int newNodeKey = leftSubTreeRoot.getKey();
            this.leafNodeRemoval(targetNode, ChildSide.LEFT);
            targetNode.setKey(newNodeKey);
        } else if(leftSubTreeRoot.getRight() == null) {
            logger.log("Left subtree root is far right node. Copying it to target node and removing it.");
            int newNodekey = leftSubTreeRoot.getKey();
            this.oneChildNodeRemoval(targetNode, ChildSide.LEFT);
            targetNode.setKey(newNodekey);
        } else {
            logger.log("Starting process of copying left subtree far right node...");
            Node leftSubTreeNode = targetNode.getLeft();
            int newKey = this.processCopyingOfFarRightNode(leftSubTreeNode);
            targetNode.setKey(newKey);
        }
    }

    private int processCopyingOfFarRightNode(Node parent){
        Node targetNode = parent.getRight();
        if(targetNode.getRight() == null){
            logger.log("Far right node found: " + targetNode.getKey() + ". Copying it to target node.");
            int newKey = targetNode.getKey();
            this.processRemoval(parent, ChildSide.RIGHT);
            return newKey;
        }
        return this.processCopyingOfFarRightNode(targetNode);
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
