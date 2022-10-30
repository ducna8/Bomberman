package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;

public class flameItem extends item {

  public flameItem(double x, double y, Image img) {
    super(x, y, img);
  }

  @Override
  public void update() {
    if (!transform) {
      img = null;
    }
  }
}
