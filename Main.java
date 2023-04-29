public class Main {

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insert(20);
        tree.insert(2);
        tree.insert(30);
        tree.insert(3);
        tree.remove(20);
        tree.insert(4);
        tree.insert(5);
        tree.insert(7);
        tree.remove(4);
        tree.insert(6);
        tree.remove(30);
        tree.remove(3);
        tree.insert(-1);
        tree.insert(-10);
        tree.insert(1);
        tree.remove(2);
        tree.printTreeState();
    }
}
