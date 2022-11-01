package uet.oop.bomberman.entities.bomb;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

public class horizontal extends BombExplosion {

  private List<Image> explosionHorizontal = new ArrayList<>();

  public horizontal(double x, double y, Image img) {
    super(x, y, img);
    explosionHorizontal.add(Sprite.explosion_horizontal.getFxImage());
    explosionHorizontal.add(Sprite.explosion_horizontal1.getFxImage());
    explosionHorizontal.add(Sprite.explosion_horizontal2.getFxImage());
  }

  public Image horizontal() {

    if (img == explosionHorizontal.get(0)) {
      return explosionHorizontal.get(1);
    }
    if (img == explosionHorizontal.get(1)) {
      return explosionHorizontal.get(2);
    } else {
      return explosionHorizontal.get(0);
    }
  }

  @Override
  public void update() {
    if (!CreateMap.bomb.EndExploded) {
      img = null;
    } else {
      if (this.x > CreateMap.bomb.getX() && CreateMap.bombExplosionReal[0] != 1) {
        CreateMap.collisionBomb(this.x, this.y);
      } else if (this.x < CreateMap.bomb.getX() && CreateMap.bombExplosionReal[1] != 1) {
        CreateMap.collisionBomb(this.x, this.y);
      }
      img = horizontal();
    }
  }
}
