import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game {
    public List<String> actors;
    public List<String> arrival;
    public List<String> departure;
    public static final int DEPARTURE = 0;
    public static final int ARRIVAL = 1;
    public int position = Game.DEPARTURE; // Departure 0, arrival 1
    public Game() {
        this.actors = List.of("C","L","U","N");
        this.arrival = new ArrayList<>();
        this.departure = new ArrayList<>(List.of("C","L","U"));
    }

    public void printState(){
        System.out.println("##############################################");
        System.out.println("En la salida: ");
        this.departure.forEach(item -> System.out.print(item + ", "));
        System.out.println("\nEn la llegadaa: ");
        this.arrival.forEach(item -> System.out.print(item + ", "));
        String state = (position==Game.DEPARTURE)?"Salida": "Llegada";
        System.out.println("\nPosicion actual: " + state);
    }

    public void makeMove(String elementToMove) {
        if (position == Game.DEPARTURE) {
            moveElement(this.departure, this.arrival, elementToMove);
            position = Game.ARRIVAL;
        } else {
            moveElement(this.arrival, this.departure, elementToMove);
            position = Game.DEPARTURE;
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

    public boolean isGameOver(){
        AtomicBoolean isGameOver = new AtomicBoolean(true);
        isGameOver.set(isGameOver.get() && departure.containsAll(actors));
        return isGameOver.get();
    }

    public boolean isElementValid(String newElement){
        return this.actors.contains(newElement);
    }
}
