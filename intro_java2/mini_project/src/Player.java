import java.util.Scanner;

public class Player {
    private int points;
    private int bankroll;
    private boolean passed;

    public Player() {
        this.points = 0;
        this.bankroll = 0;
        this.passed = false;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int newPoints) {
        this.points = newPoints;
    }

    public void setBankroll(int bankroll) {
        this.bankroll = bankroll;
    }

    public int getBankroll() {
        return this.bankroll;
    }

    // Player is not given the choice to hit again if player has 21
    public boolean hitting() {
        return (!this.passed && !busto() && !has21());
    }

    public void passes() {
        this.passed = true;
    };

    // does need to be public because we check if the player automatically loses before the dealer players
    public boolean busto() {
        return this.points > 21;
    }

    public boolean has21() {
        return this.points == 21;
    }

    public void loses(int bet) {
        this.bankroll -= bet;
    }

    public void wins(int bet) {
        this.bankroll += bet;
    }

    public void reset() {
        this.points = 0;
        this.passed = false;
    }

    public boolean broke() {
        return this.bankroll <= 0;
    }
}
