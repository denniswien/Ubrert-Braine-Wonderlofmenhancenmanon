package bots;

import java.util.*;
import pirates.*;
/*import java.lang.Math
import java.lang.reflect
import java.lang.class
import java.math
*/

class DefendStrategy implements Strategy {

    
    //TODO: mabye change myCities.get(0) if there's more than one city
    @Override
    public void doTurn(PirateGame game, History history) {
        game.debug("Activated: DefendStrategy");
        List<Pirate> myLivingPirates = game.getMyLivingPirates();
        List<Drone> enemyLivingDrones = game.getEnemyLivingDrones();
        List<City> enemyCities = game.getEnemyCities();
        int numMyLivingPirates = myLivingPirates.size();
        game.debug(numMyLivingPirates);
        for (int i = 0; i < numMyLivingPirates; i++) {
            if (Attacker.tryAttack(myLivingPirates.get(i), game)) 
                myLivingPirates.remove(i);
        }        /*Mover.moveAircraftToClosestToAnotherMapObject(
                                myLivingPirates.get(i),
                                enemyLivingDrones,
                                enemyCities.get(0),
                                game);*/
        numMyLivingPirates = myLivingPirates.size();
        for(int i = 0; i < numMyLivingPirates; i++)
        {
                Drone closest = (Drone)getColosest(enemyCities.get(0), enemyLivingDrones);
                enemyLivingDrones.remove(closest);
                if(closest == null)
                {
                    //for(Pirate pirate:myLivingPirates)
                    //    Mover.moveAircraft(pirate,enemyCities.get(0),game);
                    return;
                }
                Pirate pirate = (Pirate)getColosest(closest, myLivingPirates);
                myLivingPirates.remove(pirate);  
                Mover.moveAircraft(pirate,closest,game);
        }
        
    }
    public <T extends MapObject> MapObject getColosest(MapObject mapObject,List<T> list)
    {
        if(mapObject == null)//no target
            return null;
        
        MapObject closest = null;
        for(T t:list)
        {
            if(closest == null || closest.distance(mapObject) > t.distance(mapObject))
                closest = t;
        }
        return closest;
    }
}
