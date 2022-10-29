package uet.oop.bomberman.entities.mobileEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom {

  package uet.oop.bomberman.entities.moblieEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

  public class Balloom extends MobileEntity {

    public List<Image> toLeft = new ArrayList<>();
    public List<Image> toRight = new ArrayList<>();
    public List<Image> toUp = new ArrayList<>();
    public List<Image> toDown = new ArrayList<>();
    public List<Image> toDead = new ArrayList<>();

    public Balloom(int x, int y, Image img){
      super(x,y,img);
      toLeft.add(Sprite.balloom_left1.getFxImage());
      toLeft.add(Sprite.balloom_left2.getFxImage());
      toLeft.add(Sprite.balloom_left3.getFxImage());

      toRight.add(Sprite.balloom_right1.getFxImage());
      toRight.add(Sprite.balloom_right2.getFxImage());
      toRight.add(Sprite.balloom_right3.getFxImage());

      toUp.add(Sprite.balloom_right1.getFxImage());
      toUp.add(Sprite.balloom_right2.getFxImage());
      toUp.add(Sprite.balloom_right3.getFxImage());

      toDown.add(Sprite.balloom_left1.getFxImage());
      toDown.add(Sprite.balloom_left2.getFxImage());
      toDown.add(Sprite.balloom_left3.getFxImage());

      toDead.add(Sprite.balloom_dead.getFxImage());
      direction = "";
      loop = 0;
    }

    public String RandomMoving(){
      List<String> RandomMoving = Map.movable(x,y);
      Random random = new Random();
      int move = random.nextInt();
      return RandomMoving.get(move);
    }
  }

}
