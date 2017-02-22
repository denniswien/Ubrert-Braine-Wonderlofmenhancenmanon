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
    
    
    
     /**
     * Moves the aircraft towards the destination.
     *
     * @param aircraft - the aircraft to move
     * @param targets - a list of target thet we want to go to the closer of them
     * @param game - the current game state
     *
     *return - if aircraft moved
     *
     */
    static <T extends MapObject> boolean moveAircraftToCloser(Aircraft aircraft, List<T> targets, PirateGame game)
    {   
        //first closer location is null location
        Location closer = null;
        for(T target:targets)
        {
            if(closer==null || aircraft.distance(closer)>aircraft.distance(target))
                closer = target.getLocation();
        }
        
        if(closer==null)//no targets
            return false;
        
        moveAircraft(aircraft,closer,game);
        return true;
   }
}
