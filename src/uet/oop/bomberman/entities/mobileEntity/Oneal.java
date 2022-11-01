package uet.oop.bomberman.entities.mobileEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends MobileEntity {

  public List<Image> toLeft = new ArrayList<>();
  public List<Image> toRight = new ArrayList<>();
  public List<Image> toUp = new ArrayList<>();
  public List<Image> toDown = new ArrayList<>();
  public List<Image> toDead = new ArrayList<>();

  public double speed;
  public double loopAgain;

  public Oneal(int x, int y, Image img) {

    super(x, y, img);
    direction = "";
    loop = 0;
    loopAgain = 0;
    speed = 0;

    toLeft.add(Sprite.oneal_left1.getFxImage());
    toLeft.add(Sprite.oneal_left2.getFxImage());
    toLeft.add(Sprite.oneal_left3.getFxImage());

    toRight.add(Sprite.oneal_right1.getFxImage());
    toRight.add(Sprite.oneal_right2.getFxImage());
    toRight.add(Sprite.oneal_right3.getFxImage());

    toUp.add(Sprite.oneal_left1.getFxImage());
    toUp.add(Sprite.oneal_left2.getFxImage());
    toUp.add(Sprite.oneal_left3.getFxImage());

    toDown.add(Sprite.oneal_right1.getFxImage());
    toDown.add(Sprite.oneal_right2.getFxImage());
    toDown.add(Sprite.oneal_right3.getFxImage());

    toDead.add(Sprite.oneal_dead.getFxImage());
  }

  public String RandomMoving() {
    List<String> RandomMoving = CreateMap.movable(x, y);
    Random random = new Random();
    int move = random.nextInt();
    return RandomMoving.get(move);
  }

  public Image OnealToLeft() {
    if (CreateMap.CollisionMove(x, y, 0.2, 1, "left") && x > 0) {
      x = (double) Math.round((x - speed) * 10) / 10;
    }
    if (img == toLeft.get(0)) {
      return toLeft.get(1);
    } else if (img == toLeft.get(1)) {
      return toLeft.get(2);
    } else if (img == toLeft.get(2)) {
      return toLeft.get(0);
    } else {
      return toLeft.get(0);
    }
  }

  public Image OnealToRight() {
    if (CreateMap.CollisionMove(x, y, 0.2, 1, "right") && x < BombermanGame.WIDTH) {
      x = (double) Math.round((x + speed) * 10) / 10;
    }
    if (img == toRight.get(0)) {
      return toRight.get(1);
    } else if (img == toRight.get(1)) {
      return toRight.get(2);
    } else if (img == toRight.get(2)) {
      return toRight.get(0);
    } else {
      return toRight.get(0);
    }
  }

  public Image OnealToUp() {
    if (CreateMap.CollisionMove(x, y, 0.2, 1, "up") && y > 0) {
      x = (double) Math.round((y - speed) * 10) / 10;
    }
    if (img == toUp.get(0)) {
      return toUp.get(1);
    } else if (img == toUp.get(1)) {
      return toUp.get(2);
    } else if (img == toUp.get(2)) {
      return toUp.get(0);
    } else {
      return toUp.get(0);
    }
  }

  public Image OnealToDown() {
    if (CreateMap.CollisionMove(x, y, 0.2, 1, "down") && y < BombermanGame.HEIGHT) {
      x = (double) Math.round((y + speed) * 10) / 10;
    }
    if (img == toDown.get(0)) {
      return toDown.get(1);
    } else if (img == toDown.get(1)) {
      return toDown.get(2);
    } else if (img == toDown.get(2)) {
      return toDown.get(0);
    } else {
      return toDown.get(0);
    }

  }

  public Image OnealToDead() {
    return toDead.get(0);
  }

  public void loop() {
    if (loopAgain == loop) {
      loop = 0;
    }

    if (loop == 0) {
      direction = RandomMoving();
      loopAgain = 10;
      speed = 0.2;
    }
    if (CreateMap.bomberBaby.getX() != x && CreateMap.bomberBaby.getY() == y
        && CreateMap.checkHorizontal(x, y) && loop == 0) {
      speed = 0.3;
      loop = 4;
      if (x > CreateMap.bomberBaby.getX()) {
        direction = "left";
      } else {
        direction = "right";
      }
    } else if (CreateMap.bomberBaby.getX() == x && CreateMap.bomberBaby.getY() != y
        && CreateMap.checkVertical(x, y) && loop == 0) {
      speed = 0.3;
      loop = 4;
      if (y > CreateMap.bomberBaby.getY()) {
        direction = "up";
      } else {
        direction = "down";
      }
    }
    loop += 1;
    if (direction.equals("left")) {
      img = OnealToLeft();
    }
    if (direction.equals("right")) {
      img = OnealToRight();
    }
    if (direction.equals("up")) {
      img = OnealToUp();
    }
    if (direction.equals("down")) {
      img = OnealToDown();
    }
  }

  @Override
  public void update() {
    if (dead) {
      countDead += 1;
      if (countDead <= 12) {
        img = OnealToDead();
      } else {
        img = null;
      }
    } else {
      loop();
    }
  }
}
