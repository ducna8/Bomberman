package uet.oop.bomberman.entities;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends Entity {

  int dx, dy;
  private double speed = 0.5;
  private boolean up, down, left, right;
  public List<Image> toLeft = new ArrayList<>();
  public List<Image> toRight = new ArrayList<>();
  public List<Image> toUp = new ArrayList<>();
  public List<Image> toDown = new ArrayList<>();
  public List<Image> dead = new ArrayList<>();


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

    toLeft.add(Sprite.player_left.getFxImage());
    toLeft.add(Sprite.player_left_1.getFxImage());
    toLeft.add(Sprite.player_left_2.getFxImage());

    toRight.add(Sprite.player_right.getFxImage());
    toRight.add(Sprite.player_right_1.getFxImage());
    toRight.add(Sprite.player_right_2.getFxImage());

    toUp.add(Sprite.player_up.getFxImage());
    toUp.add(Sprite.player_up_1.getFxImage());
    toUp.add(Sprite.player_up_2.getFxImage());

    toDown.add(Sprite.player_down.getFxImage());
    toDown.add(Sprite.player_down_1.getFxImage());
    toDown.add(Sprite.player_down_2.getFxImage());

    dead.add(Sprite.player_dead1.getFxImage());
    dead.add(Sprite.player_dead2.getFxImage());
    dead.add(Sprite.player_dead3.getFxImage());
  }


  @Override
  public void keyPressed(KeyEvent e) {
    int button = e.getKeyCode();
    if (button == KeyEvent.VK_LEFT) {
      left = true;
    }
    if (button == KeyEvent.VK_RIGHT) {
      right = true;
    }
    if (button == KeyEvent.VK_UP) {
      up = true;
    }
    if (button == KeyEvent.VK_DOWN) {
      down = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int button = e.getKeyCode();
    if (button == KeyEvent.VK_LEFT) {
      left = false;
    }
    if (button == KeyEvent.VK_RIGHT) {
      right = false;
    }
    if (button == KeyEvent.VK_UP) {
      up = false;
    }
    if (button == KeyEvent.VK_DOWN) {
      down = false;
    }
  }

}
