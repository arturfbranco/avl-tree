import java.util.Objects;

public class Cli {
    private final Tree tree;

    public Cli(){
        this.tree = new Tree();
    }

    public boolean command(String command){
        try {
            String[] args = command.split(" ");
            String op = args[0];
            String value = args[1];
            switch (op){
                case "i":
                    this.tree.insert(Integer.parseInt(value));
                    tree.printTreeState();
                    return true;
                case "b":
                    this.tree.find(Integer.parseInt(value));
                    tree.printTreeState();
                    return true;
                case "r":
                    this.tree.remove(Integer.parseInt(value));
                    tree.printTreeState();
                    return true;
                case "print":
                    if(value.equals("STATE")){
                        this.tree.printTreeState();
                    } else {
                        this.tree.print(PrintOrder.valueOf(value));
                    }
                    return true;
                default:
                    System.out.println("Quiting application.");
                    return false;
            }
        }catch (Exception e){
            System.out.println("Arguments missing.");
            return true;
        }

    }
}
