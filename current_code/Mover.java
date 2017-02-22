package bots;
import java.util.List;
import pirates.*;

class Mover {
    /**
     * Moves the aircraft towards the destination.
     *
     * @param aircraft - the aircraft to move
     * @param destination - the destination of the aircraft
     * @param game - the current game state
     */
    static void moveAircraft(Aircraft aircraft, MapObject destination, PirateGame game) {
        // Get sail options
        List<Location> sailOptions = game.getSailOptions(aircraft, destination);
        // Set sail!
        game.setSail(aircraft, sailOptions.get(0));
    }
}
