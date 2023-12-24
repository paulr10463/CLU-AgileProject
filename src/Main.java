import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        String elementToMove;
        do{
            game.printState();
            System.out.println("Ingrese el elemento a transportar: ");
            elementToMove = scanner.next();
            try{ game.makeMove(elementToMove);}
            catch (Error e){
                System.out.println(e.getMessage());
            }
        }while(!game.isGameOver());
    }
}
