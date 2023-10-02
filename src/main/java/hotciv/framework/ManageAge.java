package hotciv.framework;



public class ManageAge {
    private Game game;

    public ManageAge(Game g) {
        if (g.getRules() == GameType.betaCiv)
            increaseAgeBetaCiv(g);
        else
            increaseAgeDefault(g);
    }

    private void increaseAgeBetaCiv(Game g) {
        int current = g.getAge();

        // Refer to textbook for description of aging algorithm

        if (current < -100) {
            current += 100;
        }
        else if (current == -100) {
            current = -1;
        }
        else if (current == -1) {
            current = 1;
        }
        else if (current == 1) {
            current = 50;
        }
        else if (current < 1750) {
            current +=50;
        }
        else if (current < 1900) {
            current +=25;
        }
        else if (current < 1970) {
            current +=5;
        }
        else {
            current += 1;
        }
        g.setAge(current);
    }

    private void increaseAgeDefault(Game g) {
        int current = g.getAge();
        current += 100;
        g.setAge(current);
    }
}
