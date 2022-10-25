package uet.oop.bomberman.entities.mobileEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class MobileEntity extends Entity {

  protected String direction = "";
  protected int speed = 0;

  protected boolean dead = false;

  protected int count = 0;

  protected boolean holdButton = false;

  public MobileEntity(int x, int y, Image img) {
    super(x, y, img);
  }

  public MobileEntity() {
  }

  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public boolean isDead() {
    return dead;
  }

  public void setDead(boolean life) {
    this.dead = dead;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public boolean isHoldButton() {
    return holdButton;
  }

  public void setHoldButton(boolean holdButton) {
    this.holdButton = holdButton;
  }

  @Override
  public void update() {
  }
}
