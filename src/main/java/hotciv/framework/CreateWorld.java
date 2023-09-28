package hotciv.framework;

public class CreateWorld {

    Game game;

    /**
     *
     * @param g
     */
    public CreateWorld(Game g) {
        game = g;

        if (g.getRules() == GameType.gammaCiv)
            gammaCivWorld();
        else
            defaultWorld();
    }

    private void defaultWorld() {
        // implement alphaCiv method of world Creation
    }

    private void gammaCivWorld() {
        // implement gammaCiv method of world Creation
        // StubGame1 file on Canvas implements method for creating the map
    }



}