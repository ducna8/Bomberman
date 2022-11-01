package uet.oop.bomberman.entities.bomb;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

public class verticalTop extends BombExplosion {

  private List<Image> explosionVerticalTop = new ArrayList<>();

  public verticalTop(double x, double y, Image img) {
    super(x, y, img);
    explosionVerticalTop.add(Sprite.explosion_vertical_top_last.getFxImage());
    explosionVerticalTop.add(Sprite.explosion_vertical_top_last1.getFxImage());
    explosionVerticalTop.add(Sprite.explosion_vertical_top_last2.getFxImage());
  }

  public Image verticalTop() {

    if (img == explosionVerticalTop.get(0)) {
      return explosionVerticalTop.get(1);
    }
    if (img == explosionVerticalTop.get(1)) {
      return explosionVerticalTop.get(2);
    } else {
      return explosionVerticalTop.get(0);
    }
  }

  @Override
  public void update() {
    if (!CreateMap.bomb.EndExploded) {
      img = null;
    } else {
      if (CreateMap.bombExplosionReal[2] != 1 && CreateMap.bombExplosionReal[2] != 2) {
        CreateMap.collisionBomb(this.x, this.y);
      }
      img = verticalTop();
    }
  }


}
