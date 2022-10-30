package uet.oop.bomberman.entities.bomb;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Checking;
import uet.oop.bomberman.graphics.Sprite;

public class horizontalRight extends BombExplosion {

  private List<Image> explosionHorizontalRight = new ArrayList<>();

  public horizontalRight(double x, double y, Image img) {
    super(x, y, img);
    explosionHorizontalRight.add(Sprite.explosion_horizontal_right_last.getFxImage());
    explosionHorizontalRight.add(Sprite.explosion_horizontal_right_last1.getFxImage());
    explosionHorizontalRight.add(Sprite.explosion_horizontal_right_last2.getFxImage());
  }

  public Image horizontalRight() {

    if (img == explosionHorizontalRight.get(0)) {
      return explosionHorizontalRight.get(1);
    }
    if (img == explosionHorizontalRight.get(1)) {
      return explosionHorizontalRight.get(2);
    } else {
      return explosionHorizontalRight.get(0);
    }
  }

  @Override
  public void update() {
    if (!Checking.bomb.EndExploded) {
      img = null;
    } else {
      if (Checking.bombExplosionReal[1] != 1 && Checking.bombExplosionReal[1] != 2) {
        Checking.collisionBomb(this.x, this.y);
      }
      img = horizontalRight();
    }
  }


}
