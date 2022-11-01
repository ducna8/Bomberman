package uet.oop.bomberman.entities.bomb;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Checking;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends BombExplosion {

  public List<Image> smallBomb = new ArrayList<>();
  public List<Image> biggerBomb = new ArrayList<>();
  public List<Image> explodedBomb = new ArrayList<>();

  public static int countBomb = 1;
  public static boolean exploded = false;

  public int timeDelay = 0;
  public int timeExploded = 0;
  public boolean EndExploded = false;


  public Bomb(double x, double y, Image img) {
    super(x, y, img);
    exploded = true;

    biggerBomb.add(Sprite.bomb.getFxImage());
    biggerBomb.add(Sprite.bomb_1.getFxImage());
    smallBomb.add(Sprite.bomb_1.getFxImage());
    smallBomb.add(Sprite.bomb_2.getFxImage());
    explodedBomb.add(Sprite.bomb_exploded.getFxImage());
    explodedBomb.add(Sprite.bomb_exploded1.getFxImage());
    explodedBomb.add(Sprite.bomb_exploded2.getFxImage());

  }

  public Image beforeExploded() {
    if (countBomb != 1) {
      if (biggerBomb.get(0) == img) {
        return biggerBomb.get(1);
      } else {
        return biggerBomb.get(0);
      }
    } else {
      if (smallBomb.get(0) == img) {
        return smallBomb.get(1);
      } else {
        return smallBomb.get(0);
      }
    }
  }

  public Image Exploded() {
    if (img == explodedBomb.get(0)) {
      return explodedBomb.get(1);
    }
    if (img == explodedBomb.get(1)) {
      return explodedBomb.get(2);
    } else {
      return explodedBomb.get(0);
    }
  }

  public Image loop() {
    if (timeDelay == 24) {
      EndExploded = true;
      if (timeExploded != 6) {
        Checking.collisionBomb(this.x, this.y);
        timeExploded += 1;
        return Exploded();
      } else {
        exploded = false;
        EndExploded = false;
        return null;
      }
    } else {
      timeDelay += 1;
      if (timeDelay % 2 == 0) {
        return beforeExploded();
      } else {
        return img;
      }
    }

  }

  @Override
  public void update() {
    img = loop();
  }
}
