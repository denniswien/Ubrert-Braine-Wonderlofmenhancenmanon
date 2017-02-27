package bots;

import pirates.*;
import java.util.*;

class AttackStrategy implements Strategy
{
    private static int[] attackersID = {};
    private static Location center;
    private static boolean readyToAttack = false;
    private static boolean de = false;
    public AttackStrategy()
    {
        
    }

    public void doTurn(PirateGame game,History history)
    {
        
        center = new Location(game.getRowCount()/2, game.getColCount()/2);
        
        
        
        isReadyToAttack(game);
        
        if(!de)
        {
        for (int i : attackersID) {
            if(game.getMyDroneById(i) != null)
                Mover.moveAircraft(game.getMyDroneById(i), game.getMyCities().get(0), game);
        }
        
        
        for(Drone drone : game.getMyLivingDrones())
        {
            boolean b = true;
            for (int i : attackersID) {
                if(drone.id==i)
                    b=false;
            }
            game.debug(b);
            if(b)
                Mover.moveAircraft(drone, center, game);
        }
        
        if(!game.getNeutralIslands().isEmpty())
            for (Pirate p : game.getMyLivingPirates()) {
                if(!Attacker.tryAttack(p, game))
                    Mover.moveAircraft(p, game.getNeutralIslands().get(0), game);
            }
        else if(!readyToAttack)
            for (Pirate p : game.getMyLivingPirates()) {
                if(!Attacker.tryAttack(p, game))
                    Mover.moveAircraft(p, center, game);
            }
        else
            for (int i = 0 ; i< game.getMyLivingPirates().size() ; i++) {
                if(!Attacker.tryAttack(game.getMyLivingPirates().get(i), game))
                    Mover.moveAircraft(game.getMyLivingPirates().get(i), game.getMyCities().get(0), game);
            }
        if(game.GetMyDecoy()!=null)
            Mover.moveAircraft(game.GetMyDecoy(), game.getMyCities().get(0), game);
        }
        de = false;
    }
    
    
    
    
    private static void isReadyToAttack(PirateGame game)
    {
        if(readyToAttack==true)
            readyToAttack = OneAlive(attackersID,game);
        else if(game.getMyLivingDrones().size() == game.getMaxDronesCount())
        {
            boolean b = true;
            for (Drone drone : game.getMyLivingDrones()) {
                if(drone.distance(center)!=0)
                    b = false;
            }
            readyToAttack = b;
            if (b)
            {
                if(game.getMyself().turnsToDecoyReload==0)
                {
                    game.decoy(game.getMyLivingPirates().get(0));
                    de = true;
                    
                }
                attackersID = new int[game.getMyLivingDrones().size()];
                for (int i = 0 ; i < game.getMyLivingDrones().size() ; i++) {
                    attackersID[i] = game.getMyLivingDrones().get(i).id;
                }
            }
        
        }
    }
    private static boolean OneAlive(int[] i,PirateGame game)
    {
        for (int j : i)
           if(game.getMyDroneById(j) !=null)
               return true;
        return false;
    
    }
    
    
    
    
    
    
    
    public static void firstInit(PirateGame game)
    {
        center = new Location(game.getRowCount()/2, game.getColCount()/2);
    }
    
    
    
    
    
    
    public static void moveListOfDroneByID(MapObject mapObject,int[] Ids,PirateGame game)
    {
        for (int id : Ids) {
            Mover.moveAircraft(game.getMyDroneById(id), mapObject, game);
        }
    }
    
    
    
    
    
    public static boolean isInArray(Aircraft[] array,Aircraft a)
    {
        for (Aircraft aircraft : array) {
            if(aircraft == a)
                return true;
        }
        return false;
    }
    
}


