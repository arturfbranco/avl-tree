public class Tree {
    private Node root;

    public void printTreeState(){
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
        if(this.root == null){
            this.root = new Node(key);
            System.out.println("Tree is empty. Inserting key as root node.");
        } else{
            Node currentNode = this.root;
            this.insert(currentNode, key);
        }
    }
    private void insert(Node root, int key){
        if(root.getKey() == key){
            System.out.println("Key already exists in tree. Aborting operation.");
        } else {
            if(key < root.getKey()){
                if(root.getLeft() == null){
                    Node newNode = new Node(key);
                    root.setLeft(newNode);
                    System.out.println("Key inserted with success.");
                } else{
                    this.insert(root.getLeft(), key);
                }
            } else {
                if(root.getRight() == null){
                    Node newNode = new Node(key);
                    root.setRight(newNode);
                    System.out.println("Key inserted with success.");
                } else {
                    this.insert(root.getRight(), key);
                }
            }
        }
    }
    public int find(int key){
        if(this.root == null){
            System.out.println("Element not found.");
            return -1;
        } else {
            Node root = this.root;
            return this.find(root, key);
        }
    }
    private int find (Node root, int key){
        if(root == null){
            System.out.println("Key not found.");
            return -1;
        } else if(key == root.getKey()){
            System.out.println("Key found: " + root.getKey());
            return root.getKey();
        } else {
            if(key < root.getKey()){
                return this.find(root.getLeft(), key);
            } else {
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
        this.remove(this.root, key);
    }
    private void remove(Node root, int key){
        if(root == null){
            System.out.println("Key not found");
        } else if(root.getKey() == this.root.getKey() && key == root.getKey()){
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
                if(key == currentNode.getKey()){
                    this.processRemoval(parent, childSide);
                } else {
                    this.remove(currentNode, key);
                }
            }
        }
    }

    private void processTreeRootRemoval(){
        if(this.root.getLeft() == null && this.root.getRight() == null){
            this.root = null;
        } else if(this.root.getLeft() != null && this.root.getRight() != null){
            this.processCopyRemoval(this.root);
        } else {
            if(this.root.getLeft() != null){
                this.root = this.root.getLeft();
            } else {
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
        if(targetNode.getLeft() == null && targetNode.getRight() == null){
            this.leafNodeRemoval(parent, childSide);
        } else if(targetNode.getLeft() != null && targetNode.getRight() != null){
            this.twoChildrenNodeRemoval(parent, childSide);
        } else {
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
        Node leftSubTreeRoot = targetNode.getLeft();
        if(leftSubTreeRoot.getLeft() == null && leftSubTreeRoot.getRight() == null){
            int newNodeKey = leftSubTreeRoot.getKey();
            this.leafNodeRemoval(targetNode, ChildSide.LEFT);
            targetNode.setKey(newNodeKey);
        } else if(leftSubTreeRoot.getRight() == null) {
            int newNodeKey = leftSubTreeRoot.getKey();
            this.oneChildNodeRemoval(targetNode, ChildSide.LEFT);
        } else {
            Node leftSubTreeNode = targetNode.getLeft();
            int newKey = this.processCopyingOfFarRightNode(leftSubTreeNode);
            targetNode.setKey(newKey);
        }
    }

    private int processCopyingOfFarRightNode(Node parent){
        Node targetNode = parent.getRight();
        if(targetNode.getRight() == null){
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
            parent.setLeft(nodeChild);
        } else {
            parent.setRight(nodeChild);
        }
    }
    private void leafNodeRemoval(Node parent, ChildSide childSide){
        if(childSide.equals(ChildSide.LEFT)){
            parent.setLeft(null);
        } else {
            parent.setRight(null);
        }
    }
}
