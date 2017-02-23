package bots;
import pirates.*;

/**
 * Agada bot :)
 */
public class MyBot implements PirateBot {
    private History history;
    private Strategy strategy;

    @Override
    public void doTurn(PirateGame game) {
        if(firstTurn()) initFirstTurn();
        history.update(game);
        strategy.doTurn();
    }

    private boolean firstTurn() {
        return game.getTurnNumber() == 0;
    }

    private void initFirstTurn() {
        strategy = decideStrategy();
    }

    private Strategy decideStrategy(History history) {
        if (game.getEnemyCities().size() == 0){
            return new AttackStrategy();
        }
        if (game.getMyCities().size() == 0){
            return new DefendStrategy();
        }
        if (game.getMyCities().size() > && game.getEnemyCities().size() > 0){
            return new SpecialStrategy();
        }
    }
}
