package entities;

import java.util.ArrayList;
import java.util.List;

public class CoinManager {
	private List<Coin> coins;
    private Player player1, player2;

    public CoinManager(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.coins = new ArrayList<>();
    }
    
    private void addTestCoins() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                coins.add(new Coin(100 + i * 100, 100 + j * 100, 20, 20));
            }
        }
    }
}
