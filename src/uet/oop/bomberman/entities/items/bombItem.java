package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;

public class bombItem extends item {

  public bombItem(double x, double y, Image img) {
    super(x, y, img);
  }

  @Override
  public void update() {
    if (!transform) {
      img = null;
    }
  }
}
