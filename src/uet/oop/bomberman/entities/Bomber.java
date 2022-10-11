package uet.oop.bomberman.entities;

import java.awt.event.KeyEvent;
import javafx.scene.image.Image;

public class Bomber extends Entity {

  int dx, dy;
  private double speed = 0.5;
  private boolean up, down, left, right;

  public void update() {
    if (down) {
      dx += speed;
    }

    if (right) {
      dx -= speed;
    }
    if (left) {
      dy += speed;
    }
    if (up) {
      dy -= speed;
    }
  }

  public Bomber(int x, int y, Image img) {
    super(x, y, img);
  }

  public void keyTyped(KeyEvent e) {

  }

  // Bấm
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      right = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      left = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      down = true;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      up = true;
    }
  }
// Nhả

  public void KeyRealeased(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
      right = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
      left = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
      down = false;
    }
    if (e.getKeyCode() == KeyEvent.VK_UP) {
      up = false;
    }
  }
}
