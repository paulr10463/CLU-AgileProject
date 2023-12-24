import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
        System.out.println("En la salida: ");
        this.departure.forEach(item -> System.out.print(item + ", "));
        System.out.println("\nEn la llegadaa: ");
        this.arrival.forEach(item -> System.out.print(item + ", "));
        String state = (position==Game.DEPARTURE)?"Salida": "Llegada";
        System.out.println("\nPosicion actual: " + state);
    }

    public void makeMove(String elementToMove) {
        if(checkIfPlayerWon()){
            isGameOver = true;
            throw new Error("GANASTE!!!!!!!!!!");
        }
        if (position == Game.DEPARTURE) {
            moveElement(this.departure, this.arrival, elementToMove);
            position = Game.ARRIVAL;
            isGameOver = haveWrongCombinations(departure);
        } else {
            moveElement(this.arrival, this.departure, elementToMove);
            position = Game.DEPARTURE;
            isGameOver = haveWrongCombinations(arrival);
        }
    }

    private void moveElement(List<String> sourceList, List<String> destinationList, String elementToMove) {
        if (!isElementValid(elementToMove)) {
            throw new Error("Ingrese un Rol válido");
        }
        if (elementToMove.equals("N")){
            return;
        }
        int elementFoundIndex = sourceList.indexOf(elementToMove);
        if (elementFoundIndex != -1) {
            sourceList.remove(elementFoundIndex);
            destinationList.add(elementToMove);
        } else {
            throw new Error("No se encuentra ese elemento");
        }
    }

    public boolean checkIfPlayerWon(){
        return departure.containsAll(actors);
    }

    private boolean haveWrongCombinations(List<String> list) {
        return list.containsAll(wrongCombinations.get(0)) || list.containsAll(wrongCombinations.get(1));
    }

    public boolean isElementValid(String newElement){
        return this.actors.contains(newElement);
    }
}
