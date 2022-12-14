package uet.oop.bomberman.entities.immobileEntity;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends immobileEntity {

  private final List<Image> explodedBrick = new ArrayList<>();
  private int explodeCount = 1;

  public Brick(int x, int y, Image img) {
    super(x, y, img);
    explodedBrick.add(Sprite.brick_exploded.getFxImage());
    explodedBrick.add(Sprite.brick_exploded1.getFxImage());
    explodedBrick.add(Sprite.brick_exploded2.getFxImage());
  }

  public Image explodingBrick() {
    if (img == explodedBrick.get(0)) {
      return explodedBrick.get(1);
    }
    if (img == explodedBrick.get(1)) {
      return explodedBrick.get(2);
    }
    if (img == explodedBrick.get(2)) {
      return null;
    }
    if (img == null) {
      return null;
    } else {
      return explodedBrick.get(0);
    }
  }

  @Override
  public void update() {
    if (difference) {
      for (int i = 0; i < CreateMap.renderImmobileEntities.size(); i++) {
        if (CreateMap.renderImmobileEntities.get(i) == this) {
          int runtime = 0;
          if (runtime == 60) {
            if (img != null) {
              explodeCount++;
            } else if (explodeCount % 3 == 0) {
              img = explodingBrick();
              explodeCount++;
            }
          } else {
            explodeCount += 1;
          }
        }
      }
    }
  }
  
}
