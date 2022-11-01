package uet.oop.bomberman.entities.mobileEntity;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class MobileEntity extends Entity {

  protected String direction = "";
  protected boolean dead = false;
  protected int countDead = 0;
  protected int loop;
  protected boolean holdButton = false;


  public MobileEntity(double x, double y, Image img) {
    super(x, y, img);
  }


  public String getDirection() {
    return direction;
  }

  public void setDirection(String direction) {
    this.direction = direction;
  }

  public boolean isDead() {
    return dead;
  }

  public void setDead(boolean dead) {
    this.dead = dead;
  }

  public int getCountDead() {
    return countDead;
  }

  public void setCountDead(int countDead) {
    this.countDead = countDead;
  }

  public boolean isHoldButton() {
    return holdButton;
  }

  public void setHoldButton(boolean holdButton) {
    this.holdButton = holdButton;
  }

  public int getLoop() {
    return loop;
  }

  public void setLoop(int loop) {
    this.loop = loop;
  }
  public abstract void update();
}
