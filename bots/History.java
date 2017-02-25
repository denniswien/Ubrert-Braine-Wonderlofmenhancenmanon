package bots;
import java.util.ArrayList;
import java.util.List;
import pirates.*;

class History {
    private List<List<Location>> enemysPiratesLocation;
    private List<List<Integer>> enemysPiratesHealth;
    public History(PirateGame game)
    {
        enemysPiratesLocation = new ArrayList<>();
        enemysPiratesHealth = new ArrayList<>();
    }
    void update(PirateGame game) 
    {
        enemysPiratesLocation.add(new ArrayList<>());
        enemysPiratesHealth.add(new ArrayList<>());
        List<Location> thisTurnEnemyPiratesLocations = enemysPiratesLocation.get(enemysPiratesLocation.size()-1);
        List<Integer> thisTurnEnemyPiratesHealth = enemysPiratesHealth.get(enemysPiratesLocation.size()-1);
        for (Pirate pirate : game.getEnemyLivingPirates()) {
            thisTurnEnemyPiratesLocations.add(pirate.location);
            thisTurnEnemyPiratesHealth.add(pirate.currentHealth);
            
        }
    }
    public List<Location> getEnemyPiratesLocatinByTurn(int turn)
    {
        return enemysPiratesLocation.get(turn);
    }
    public List<Integer> getEnemyPiratesHealthByTurn(int turn)
    {
        return enemysPiratesHealth.get(turn);
    }
}
