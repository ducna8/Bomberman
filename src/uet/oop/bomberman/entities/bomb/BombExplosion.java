package uet.oop.bomberman.entities.bomb;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class BombExplosion extends Entity {

  private List<Image> explosionHorizontal = new ArrayList<>();
  private List<Image> explosionHorizontalLeft = new ArrayList<>();
  private List<Image> explosionHorizontalRight = new ArrayList<>();

  private List<Image> explosionVertical = new ArrayList<>();
  private List<Image> explosionVerticalTop = new ArrayList<>();
  private List<Image> explosionVerticalDown = new ArrayList<>();

  public BombExplosion(double x, double y, Image img){
    super(x,y,img);
  }

  public abstract void update();
}
