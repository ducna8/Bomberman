package uet.oop.bomberman.entities.immobileEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class immobileEntity extends Entity {

  public boolean difference = true;

  public immobileEntity(double x, double y, Image img){
    super(x,y,img);
  }

  public boolean isDifference() {
    return difference;
  }

  public void setDifference(boolean difference) {
    this.difference = difference;
  }

  public abstract void update();
}
