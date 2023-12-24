import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        String elementToMove;
        while(!game.isGameOver){
            if(game.checkIfPlayerWon()){
                System.out.println("############## GANASTE!!!!!!!!!!  #####################");
                break;
            }
            game.printState();
            System.out.print("Ingrese el elemento a transportar: ");
            elementToMove = scanner.next();
            try{
                game.makeMove(elementToMove);
            }
            catch (Error e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("############## GAME OVER #####################");
    }
}
