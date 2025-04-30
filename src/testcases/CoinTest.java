package testcases;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Rectangle;

import org.junit.jupiter.api.Test;

import entities.Coin;

class CoinTest {

	@Test
	public void testInitHitbox() {
	    Coin coin = new Coin(100, 150, 15, 15);
	    Rectangle hitbox = coin.getHitbox();
	    assertEquals(100, hitbox.x);
	    assertEquals(150, hitbox.y);
	    assertEquals(15, hitbox.width);
	    assertEquals(15, hitbox.height);
	}
	
	@Test
	public void testActiveFlag() {
	    Coin coin = new Coin(0, 0, 10, 10);
	    assertTrue(coin.isActive());
	    coin.setActive(false);
	    assertFalse(coin.isActive());
	}
}
