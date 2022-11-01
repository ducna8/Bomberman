package uet.oop.bomberman;

import java.io.IOException;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.Menu.Menu;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.BombExplosion;
import uet.oop.bomberman.entities.immobileEntity.Brick;
import uet.oop.bomberman.entities.immobileEntity.immobileEntity;
import uet.oop.bomberman.entities.items.item;
import uet.oop.bomberman.entities.mobileEntity.MobileEntity;
import uet.oop.bomberman.graphics.CreateMap;



public class BombermanGame extends Application {

  public static final int WIDTH = 35;
  public static final int HEIGHT = 20;
//  public static ImageView backgroundView;
  public static GraphicsContext gc;
  public static Stage screen;
  public static int key = 0;

  public static List<MobileEntity> mobileEntities;
  public static List<immobileEntity> immobileEntities;
  public static List<immobileEntity> renderImmobileEntities;
  public static List<BombExplosion> bombExplosions;
  public static List<item> item;




  public static void main(String[] args) {
    Application.launch(BombermanGame.class);
  }

  public static void removeRender() {
    if (mobileEntities != null) {
      if (mobileEntities.size() != 0) {
        for (int i =0; i < mobileEntities.size(); i++) {
          gc.clearRect(32 * mobileEntities.get(i).getX(), 32 * mobileEntities.get(i).getY(), 32,
              32);
        }
      }
    }

    if (renderImmobileEntities != null) {
      if (renderImmobileEntities.size() != 0) {
        for (immobileEntity renderImmobileEntity : renderImmobileEntities) {
          if (renderImmobileEntity instanceof Brick
              && renderImmobileEntity.isDifference(true)) {
            gc.clearRect(32 * renderImmobileEntity.getX(),
                32 * renderImmobileEntity.getY(), 32, 32);
          }
        }
      }
    }

    if (bombExplosions != null) {
      for (BombExplosion bombExplosion : bombExplosions) {
        gc.clearRect(32 * bombExplosion.getX(), 32 * bombExplosion.getY(), 32, 32);
      }
    }
    if (item != null) {
      for (uet.oop.bomberman.entities.items.item value : item) {
        if (value.isTransform()) {
          gc.clearRect(32 * value.getX(), 32 * value.getY(), 32, 32);
        }
      }
    }
  }

public abstract class AnimationTimerExt extends AnimationTimer {

  private long sleepNs = 0;

  long prevTime = 0;

  public AnimationTimerExt(long sleepMs) {
    this.sleepNs = sleepMs * 500000;
  }

  @Override
  public void handle(long now) {
    // some delay
    if ((now - prevTime) < sleepNs) {

      return;
    }
    prevTime = now;
    handle();
  }

  public abstract void handle();

}

    @Override
    public void start (Stage stage) throws IOException {
      screen = stage;
      new Menu();
      screen.setTitle("BomberMan nhom 86");
      screen.show();

      AnimationTimerExt timer = new AnimationTimerExt(100) {
        @Override
        public void handle() {
          if (key != 0) {
            removeRender();
            update();
            render();
            try {
              CreateMap.deleteEntity();
            } catch (IOException e) {
              e.printStackTrace();
            }
            if (CreateMap.bomberLive == 0) {
              CreateMap.createMap = null;
//              GameOver.go = new GameOver();
//              mediaPlayer.mediaBackgroundPlayer.stop();
            }
            screen.setTitle("Live: " + CreateMap.bomberLive);
          }
        }
      };
      timer.start();
    }

  public void update() {
    if(key !=0){
        immobileEntities = CreateMap.immobileEntities;
        renderImmobileEntities = CreateMap.renderImmobileEntities;
        mobileEntities = CreateMap.mobileEntities;
        bombExplosions = CreateMap.bombExplosions;
        item = CreateMap.item;
        immobileEntities.forEach(Entity::update);
        renderImmobileEntities.forEach(Entity::update);
        mobileEntities.forEach(Entity::update);
        bombExplosions.forEach(Entity::update);
        item.forEach(Entity::update);
    }
  }

  public void render() {
    if(key !=0){
      if(key == 1){
        mobileEntities.forEach(g -> g.render(gc));
        key +=1;
      }
      if(renderImmobileEntities != null){
        renderImmobileEntities.forEach(g->g.render(gc));
      }
      if(item != null){
        item.forEach(g -> g.render(gc));
        mobileEntities.forEach(g -> g.render(gc));
      }
      if(bombExplosions != null){
        bombExplosions.forEach(g -> g.render(gc));
      }
    }
  }
}
