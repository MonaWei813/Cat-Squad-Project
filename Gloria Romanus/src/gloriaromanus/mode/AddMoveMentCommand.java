package unsw.gloriaromanus.mode;

import java.io.IOException;

public class AddMoveMentCommand implements Command {
    private Player player;
    private Province from;
    private Province to;
    private int turn;
    private Unit unit;

    public AddMoveMentCommand(Player player, Province from, Province to, int turn, Unit unit) {
        this.player = player;
        this.from = from;
        this.to = to;
        this.turn = turn;
        this.unit = unit;
    }

    public void execute() throws IOException {
        Movement temp = new Movement(player, from, to, turn, unit);
        temp.getMovement();
    }
}
