import java.util.Scanner;

public class Main {

    public static Tree tree;

    public static void main(String[] args) {
        Cli cli = new Cli();
        boolean shouldContinue = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Árvore Binária de Busca - AVL");
        while (shouldContinue){
            System.out.println("\nEscolha uma das opções abaixo. Digite qualquer outra para finalizar.");
            System.out.println("i = Inserir; b = Buscar; r = Remover;");
            System.out.println("print = Imprimir em PRE_ORDER, IN_ORDER, POST_ORDER, ALL, STATE");
            String command = scanner.nextLine();
            shouldContinue = cli.command(command);
        }
    }

    public static void tests(){
        reset();

        addAndPrint(10);
        addAndPrint(8);
        addAndPrint(7);

        printSeparator();
        reset();

        addAndPrint(10);
        addAndPrint(12);
        addAndPrint(18);

        printSeparator();
        reset();

        addAndPrint(10);
        addAndPrint(12);
        addAndPrint(8);
        addAndPrint(7);
        addAndPrint(6);

        printSeparator();
        reset();

        addAndPrint(10);
        addAndPrint(8);
        addAndPrint(12);
        addAndPrint(13);
        addAndPrint(14);

        printSeparator();
        reset();

        addAndPrint(20);
        addAndPrint(10);
        addAndPrint(30);
        addAndPrint(5);
        addAndPrint(16);
        addAndPrint(25);
        addAndPrint(40);
        addAndPrint(7);
        addAndPrint(50);
        addAndPrint(3);
        addAndPrint(2);

        printSeparator();
        reset();

        addAndPrint(20);
        addAndPrint(15);
        addAndPrint(30);
        addAndPrint(10);
        addAndPrint(18);
        addAndPrint(25);
        addAndPrint(40);
        addAndPrint(5);
        addAndPrint(35);
        addAndPrint(50);
        addAndPrint(60);

        printSeparator();
        reset();

        addAndPrint(20);
        addAndPrint(15);
        addAndPrint(18);

        printSeparator();
        reset();

        addAndPrint(20);
        addAndPrint(25);
        addAndPrint(22);


        printSeparator();
        reset();

        addAndPrint(50);
        addAndPrint(40);
        addAndPrint(70);

        addAndPrint(30);
        addAndPrint(41);
        addAndPrint(60);
        addAndPrint(80);

        addAndPrint(20);
        addAndPrint(35);
        addAndPrint(42);
        addAndPrint(55);
        addAndPrint(65);
        addAndPrint(75);
        addAndPrint(90);

        addAndPrint(10);
        addAndPrint(52);
        addAndPrint(58);
        addAndPrint(100);

        addAndPrint(57);

        addAndPrint(43);

        addAndPrint(44);
        tree.remove(50);
        tree.printTreeState();
    }

    public static void reset(){
        tree = new Tree();
    }

    public static void printSeparator(){
        System.out.println("-------------------------------------");
    }

    public static void addAndPrint(int num){
        tree.insert(num);
        tree.printTreeState();
    }
}
