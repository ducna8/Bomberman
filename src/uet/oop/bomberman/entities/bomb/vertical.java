package uet.oop.bomberman.entities.bomb;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

public class vertical extends BombExplosion {

  private List<Image> explosionVertical = new ArrayList<>();

  public vertical(double x, double y, Image img) {
    super(x, y, img);
    explosionVertical.add(Sprite.explosion_vertical.getFxImage());
    explosionVertical.add(Sprite.explosion_vertical1.getFxImage());
    explosionVertical.add(Sprite.explosion_vertical2.getFxImage());
  }

  public Image vertical() {

    if (img == explosionVertical.get(0)) {
      return explosionVertical.get(1);
    }
    if (img == explosionVertical.get(1)) {
      return explosionVertical.get(2);
    } else {
      return explosionVertical.get(0);
    }
  }

  @Override
  public void update() {
    if (!CreateMap.bomb.EndExploded) {
      img = null;
    } else {
      if (this.y < CreateMap.bomb.getY() && CreateMap.bombExplosionReal[2] != 1) {
        CreateMap.collisionBomb(this.x, this.y);
      } else if (this.y > CreateMap.bomb.getY() && CreateMap.bombExplosionReal[3] != 1) {
        CreateMap.collisionBomb(this.x, this.y);
      }
      img = vertical();
    }
  }


}
