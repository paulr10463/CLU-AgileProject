import java.util.ArrayList;
import java.util.List;
public class Game {
    public List<String> actors;
    public List<String> arrival;
    public List<String> departure;
    public List<List<String>> wrongCombinations = List.of(List.of("C", "L"),List.of("C", "U"));
    public static final int DEPARTURE = 0;
    public static final int ARRIVAL = 1;
    public int position = Game.DEPARTURE; // Departure 0, arrival 1
    public boolean isGameOver;
    public Game() {
        this.actors = List.of("C","L","U","N");
        this.arrival = new ArrayList<>();
        this.departure = new ArrayList<>(List.of("C","L","U"));
        this.isGameOver = false;
    }
    public void printState(){
        System.out.println("##############################################");
        System.out.println("Roles Posibles: L:Lobo, C:Caperucita, U:Uvas, N:Nada (No se transporta nada)");
        System.out.printf("En la salida: %s%n", String.join(", ", departure));
        System.out.printf("En la llegada: %s%n", String.join(", ", arrival));
        String state = (position == Game.DEPARTURE) ? "Salida" : "Llegada";
        System.out.printf("Posicion actual: %s%n", state);
    }
    public void makeMove(String elementToMove) {
        List<String> sourceList = (position == Game.DEPARTURE) ? departure : arrival;
        List<String> destinationList = (position == Game.DEPARTURE) ? arrival : departure;
        moveElement(sourceList, destinationList, elementToMove);
        position = (position == Game.DEPARTURE) ? Game.ARRIVAL : Game.DEPARTURE;
        isGameOver = haveWrongCombinations(sourceList);
    }
    private void moveElement(List<String> sourceList, List<String> destinationList, String elementToMove) {
        if (!isElementValid(elementToMove)) {
            throw new Error("Ingrese un Rol válido");
        }
        if (elementToMove.equals("N")){
            return;
        }
        if (sourceList.remove(elementToMove)) {
            destinationList.add(elementToMove);
        } else {
            throw new Error("No se encuentra ese elemento");
        }
    }
    public boolean checkIfPlayerWon(){
        return arrival.contains(actors.get(0)) && arrival.contains(actors.get(1)) & arrival.contains(actors.get(2));
    }
    private boolean haveWrongCombinations(List<String> list) {
        return list.containsAll(wrongCombinations.get(0)) || list.containsAll(wrongCombinations.get(1));
    }
    public boolean isElementValid(String newElement){
        return this.actors.contains(newElement);
    }
}