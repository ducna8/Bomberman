package uet.oop.bomberman.entities.bomb;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Checking;
import uet.oop.bomberman.graphics.Sprite;

public class verticalDown extends BombExplosion {

  private List<Image> explosionVerticalDown = new ArrayList<>();

  public verticalDown(double x, double y, Image img) {
    super(x, y, img);
    explosionVerticalDown.add(Sprite.explosion_vertical_down_last.getFxImage());
    explosionVerticalDown.add(Sprite.explosion_vertical_down_last1.getFxImage());
    explosionVerticalDown.add(Sprite.explosion_vertical_down_last2.getFxImage());
  }

  public Image verticalDown() {

    if (img == explosionVerticalDown.get(0)) {
      return explosionVerticalDown.get(1);
    }
    if (img == explosionVerticalDown.get(1)) {
      return explosionVerticalDown.get(2);
    } else {
      return explosionVerticalDown.get(0);
    }
  }

  @Override
  public void update() {
    if (!Checking.bomb.EndExploded) {
      img = null;
    } else {
      if (Checking.bombExplosionReal[3] != 1 && Checking.bombExplosionReal[3] != 2) {
        Checking.collisionBomb(this.x, this.y);
      }
      img = verticalDown();
    }
  }


}
