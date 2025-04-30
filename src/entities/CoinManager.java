package entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CoinManager {
	private List<Coin> coins;
    private Player player1, player2;

    public CoinManager(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.coins = new ArrayList<>();
    }
    
    public void addTestCoins() {
        int coinWidth = 15;
        int coinHeight = 15;
        int xSpacing = 160;
        int ySpacing = 120;
        int baseX = 100;
        int baseY = 180;

        Random rand = new Random();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                if ((i == 3 && j == 0) || (i == 1 && j == 1)) {
                    continue;
                }

                int randomOffsetX = rand.nextInt(41) - 20;
                int randomOffsetY = rand.nextInt(41) - 30;
                int x = baseX + i * xSpacing + randomOffsetX;
                int y = baseY + j * ySpacing + randomOffsetY;
                coins.add(new Coin(x, y, coinWidth, coinHeight));
            }
        }
    }
    
    public void update() {
        for (Coin coin : coins) {
            if (coin.isActive()) {
                coin.update();
                checkCollision(coin);
            }
        }
    }
    
    private void checkCollision(Coin coin) {
        if (coin.getHitbox().intersects(player1.getHitbox())) {
            coin.setActive(false);
        }
        if (coin.getHitbox().intersects(player2.getHitbox())) {
            coin.setActive(false);
        }
    }
    
    public void render(Graphics g) {
        for (Coin coin : coins) {
            coin.render(g);
        }
    }
}
