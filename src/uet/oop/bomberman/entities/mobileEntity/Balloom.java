package uet.oop.bomberman.entities.mobileEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Checking;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends MobileEntity {

  public List<Image> toLeft = new ArrayList<>();
  public List<Image> toRight = new ArrayList<>();
  public List<Image> toUp = new ArrayList<>();
  public List<Image> toDown = new ArrayList<>();
  public List<Image> toDead = new ArrayList<>();


  public Balloom(int x, int y, Image img) {

    super(x, y, img);
    direction = "";
    loop = 0;

    toLeft.add(Sprite.balloom_left1.getFxImage());
    toLeft.add(Sprite.balloom_left2.getFxImage());
    toLeft.add(Sprite.balloom_left3.getFxImage());

    toRight.add(Sprite.balloom_right1.getFxImage());
    toRight.add(Sprite.balloom_right2.getFxImage());
    toRight.add(Sprite.balloom_right3.getFxImage());

    toUp.add(Sprite.balloom_left1.getFxImage());
    toUp.add(Sprite.balloom_left2.getFxImage());
    toUp.add(Sprite.balloom_left3.getFxImage());

    toDown.add(Sprite.balloom_right1.getFxImage());
    toDown.add(Sprite.balloom_right2.getFxImage());
    toDown.add(Sprite.balloom_right3.getFxImage());

    toDead.add(Sprite.balloom_dead.getFxImage());
  }

  public String RandomMoving() {
    List<String> RandomMoving = Checking.movable(x, y);
    Random random = new Random();
    int move = random.nextInt();
    return RandomMoving.get(move);
  }

  public Image balloomToLeft() {
    if (Checking.CollisionMove(x, y, 0.2, 1, "left") && x > 0) {
      x = (double) Math.round((x - 0.2) * 10) / 10;
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

  public Image balloomToRight() {
    if (Checking.CollisionMove(x, y, 0.2, 1, "right") && x < BombermanGame.WIDTH) {
      x = (double) Math.round((x + 0.2) * 10) / 10;
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

  public Image balloomToUp() {
    if (Checking.CollisionMove(x, y, 0.2, 1, "up") && y > 0) {
      x = (double) Math.round((y - 0.2) * 10) / 10;
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

  public Image balloomToDown() {
    if (Checking.CollisionMove(x, y, 0.2, 1, "down") && y < BombermanGame.HEIGHT) {
      x = (double) Math.round((y + 0.2) * 10) / 10;
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

  public Image balloomToDead() {
    return toDead.get(0);
  }

  public void loop() {
    if (loop == 10) {
      loop = 0;
    }

    if (loop == 0) {
      direction = RandomMoving();
      loop += 1;
    }

    if (direction.equals("left")) {
      img = balloomToLeft();
    }
    if (direction.equals("right")) {
      img = balloomToRight();
    }
    if (direction.equals("up")) {
      img = balloomToUp();
    }
    if (direction.equals("down")) {
      img = balloomToDown();
    }
  }

  @Override
  public void update() {
    if (dead) {
      countDead += 1;
      if (countDead <= 12) {
        img = balloomToDead();
      } else {
        img = null;
      }
    } else {
      loop();
    }
  }
}
