package uet.oop.bomberman.entities.items;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class item extends Entity {
  public boolean transform = true;

  public item(double x, double y, Image img){
    super(x,y,img);
  }

  public void setTransform(boolean transform) {
    this.transform = transform;
  }

  public boolean isTransform() {
    return transform;
  }

  public abstract void update();
}
