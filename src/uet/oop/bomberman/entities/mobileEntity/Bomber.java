package uet.oop.bomberman.entities.mobileEntity;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Checking;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends MobileEntity {

  public List<Image> toLeft = new ArrayList<>();
  public List<Image> toRight = new ArrayList<>();
  public List<Image> toUp = new ArrayList<>();
  public List<Image> toDown = new ArrayList<>();
  public List<Image> toDead = new ArrayList<>();
  private boolean up, down, left, right;
  private double distance = 0.5;

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

    toDead.add(Sprite.player_dead1.getFxImage());
    toDead.add(Sprite.player_dead2.getFxImage());
    toDead.add(Sprite.player_dead3.getFxImage());
  }

  public String getDirection() {
    return direction;
  }

  @Override
  public void setDirection(String direction) {
    super.setDirection(direction);
  }

  @Override
  public boolean isHoldButton() {
    return super.isHoldButton();
  }

  @Override
  public void setHoldButton(boolean hold) {
    super.setHoldButton(holdButton);
  }

  public void distance(double distance) {
    this.distance = distance;
    this.x = (int) x;
    this.y = (int) y;
  }

  public Image BomberToUp() {
    if (img == toUp.get(0)) {
      return toUp.get(1);
    } else if (img == toUp.get(1)) {
      return toUp.get(2);
    } else if (img == toUp.get(2)) {
      return toUp.get(0);
    }
    return toUp.get(0);
  }

  public Image BomberToDown() {
    if (img == toDown.get(0)) {
      return toDown.get(1);
    } else if (img == toDown.get(1)) {
      return toDown.get(2);
    } else if (img == toDown.get(2)) {
      return toDown.get(0);
    }
    return toDown.get(0);
  }

  public Image BomberToLeft() {
    if (img == toLeft.get(0)) {
      return toLeft.get(1);
    } else if (img == toLeft.get(1)) {
      return toLeft.get(2);
    } else if (img == toLeft.get(2)) {
      return toLeft.get(0);
    }
    return toLeft.get(0);
  }

  public Image BomberToRight() {
    if (img == toRight.get(0)) {
      return toRight.get(1);
    } else if (img == toRight.get(1)) {
      return toRight.get(2);
    } else if (img == toRight.get(2)) {
      return toRight.get(0);
    }
    return toRight.get(0);
  }

  public Image BomberToDead() {
    if (img == toDead.get(0)) {
      return toDead.get(1);
    } else if (img == toDead.get(1)) {
      return toDead.get(2);
    } else if (img == toDead.get(2)) {
      return toDead.get(0);
    }
    return toDead.get(0);
  }

  public void update() {
    if (Checking.collisionEnemy(x, y)) {
      setDead(true);
    }
    if (dead = true) {
      img = BomberToDead();
    } else {
      if (direction == "up" && holdButton == true) {
        img = BomberToUp();
      }
      if (direction == "down" && holdButton == true) {
        img = BomberToDown();
      }
      if (direction == "left" && holdButton == true) {
        img = BomberToLeft();
      }
      if (direction == "right" && holdButton == true) {
        img = BomberToRight();
      }
    }
  }


}
