package uet.oop.bomberman.entities.bomb;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Checking;
import uet.oop.bomberman.graphics.Sprite;

public class horizontalLeft extends BombExplosion {

  private List<Image> explosionHorizontalLeft = new ArrayList<>();

  public horizontalLeft(double x, double y, Image img) {
    super(x, y, img);
    explosionHorizontalLeft.add(Sprite.explosion_horizontal_left_last.getFxImage());
    explosionHorizontalLeft.add(Sprite.explosion_horizontal_left_last1.getFxImage());
    explosionHorizontalLeft.add(Sprite.explosion_horizontal_left_last2.getFxImage());
  }

  public Image horizontalLeft() {

    if (img == explosionHorizontalLeft.get(0)) {
      return explosionHorizontalLeft.get(1);
    }
    if (img == explosionHorizontalLeft.get(1)) {
      return explosionHorizontalLeft.get(2);
    } else {
      return explosionHorizontalLeft.get(0);
    }
  }

  @Override
  public void update() {
    if (!Checking.bomb.EndExploded) {
      img = null;
    } else {
      if (Checking.bombExplosionReal[0] != 1 && Checking.bombExplosionReal[0] != 2) {
        Checking.collisionBomb(this.x, this.y);
      }
      img = horizontalLeft();
    }
  }


}
