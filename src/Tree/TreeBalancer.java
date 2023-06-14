package src.Tree;

import src.Logger;

public class TreeBalancer {
    private final Logger logger;

    public TreeBalancer(){
        this.logger = new Logger();
    }
    public Node balance(Node parent, Node targetNode){
        Node newRoot = null;
        int balanceFactor = this.calculateBalanceFactor(targetNode);
        if(balanceFactor > 1 || balanceFactor < -1){
            logger.log("Node with key " + targetNode.getKey() + " has a balance factor of " + balanceFactor + ". Proceeding with rotations...");
            if(balanceFactor > 1){
                int leftSubtreeBalanceFactor = this.calculateBalanceFactor(targetNode.getLeft());
                if(leftSubtreeBalanceFactor >= 0){
                    logger.log("Proceeding with right rotation...");
                    newRoot = this.performRightRotation(parent, targetNode);
                } else {
                    logger.log("Proceeding with double right rotation...");
                    newRoot = this.performDoubleRightRotation(parent, targetNode);
                }
            } else {
                int rightSubtreeBalanceFactor = this.calculateBalanceFactor(targetNode.getRight());
                if(rightSubtreeBalanceFactor < 0){
                    logger.log("Proceeding with left rotation...");
                    newRoot = this.performLeftRotation(parent, targetNode);
                } else {
                    logger.log("Proceeding with double left rotation...");
                    newRoot = this.performDoubleLeftRotation(parent, targetNode);
                }
            }
        }
        return newRoot;
    }

    private Node performRightRotation(Node parent, Node targetNode){
        Node newRoot = targetNode.getLeft();
        targetNode.setLeft(newRoot.getRight());
        newRoot.setRight(targetNode);
        if(parent != null){
            this.updateParent(parent, targetNode, newRoot);
        }
        return newRoot;
    }

    private Node performLeftRotation(Node parent, Node targetNode){
        Node newRoot = targetNode.getRight();
        targetNode.setRight(newRoot.getLeft());
        newRoot.setLeft(targetNode);
        if(parent != null){
            this.updateParent(parent, targetNode, newRoot);
        }
        return newRoot;
    }

    private void updateParent(Node parent, Node currentNode, Node newNode) {
        if (parent.getLeft().getKey().equal(currentNode.getKey())) {
            parent.setLeft(newNode);
        } else if (parent.getRight().getKey().equal(currentNode.getKey())) {
            parent.setRight(newNode);
        }
    }

    private Node performDoubleRightRotation(Node parent, Node targetNode){
        Node leftChild = targetNode.getLeft();
        Node leftChildRightChild = leftChild.getRight();
        leftChild.setRight(leftChildRightChild.getLeft());
        leftChildRightChild.setLeft(leftChild);
        targetNode.setLeft(leftChildRightChild);
        return this.performRightRotation(parent, targetNode);
    }
    private Node performDoubleLeftRotation(Node parent, Node targetNode){
        Node rightChild = targetNode.getRight();
        Node rightChildLeftChild = rightChild.getLeft();
        rightChild.setLeft(rightChildLeftChild.getRight());
        rightChildLeftChild.setRight(rightChild);
        targetNode.setRight(rightChildLeftChild);
        return this.performLeftRotation(parent, targetNode);
    }

    private int calculateBalanceFactor(Node node){
        int leftSubtreeHeight = Node.getNodeHeight(node.getLeft());
        int rightSubtreeHeight = Node.getNodeHeight(node.getRight());
        return leftSubtreeHeight - rightSubtreeHeight;
    }
}
