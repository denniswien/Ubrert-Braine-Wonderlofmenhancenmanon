package bots;
import pirates.*;

/**
 * This is an example for a bot.
 */
public class MyBot implements PirateBot {
    private History history;

    /**
     * Makes the bot run a single turn
     *
     * @param game - the current game state.
     * 
     */
    @Override
    public void doTurn(PirateGame game) {
        if(firstTurn()) {
            initFirstTurn();
        }

        Strategy strategy = decideStrategy().doTurn(history);
    }

    private boolean firstTurn() {
        return game.getTurnNumber() == 0;
    }

    private void initFirstTurn() {
        history = new History();
        //TODO(implement): (for heuristic strategy) add measurements 2017-02-22 20:07:12 (amit)
    }

    private Strategy decideStrategy(History history) {}
}

