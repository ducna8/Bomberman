package uet.oop.bomberman.graphics;

import static uet.oop.bomberman.entities.Checking.Collision;
import static uet.oop.bomberman.entities.Checking.bomb;
import static uet.oop.bomberman.entities.Checking.bombExplosionReal;
import static uet.oop.bomberman.entities.Checking.bomberBaby;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Checking;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.BombExplosion;
import uet.oop.bomberman.entities.bomb.horizontal;
import uet.oop.bomberman.entities.bomb.horizontalLeft;
import uet.oop.bomberman.entities.bomb.horizontalRight;
import uet.oop.bomberman.entities.bomb.vertical;
import uet.oop.bomberman.entities.bomb.verticalDown;
import uet.oop.bomberman.entities.bomb.verticalTop;
import uet.oop.bomberman.entities.immobileEntity.Brick;
import uet.oop.bomberman.entities.immobileEntity.Portal;
import uet.oop.bomberman.entities.immobileEntity.Wall;
import uet.oop.bomberman.entities.immobileEntity.immobileEntity;
import uet.oop.bomberman.entities.items.bombItem;
import uet.oop.bomberman.entities.items.flameItem;
import uet.oop.bomberman.entities.items.item;
import uet.oop.bomberman.entities.items.speedItem;
import uet.oop.bomberman.entities.mobileEntity.Balloom;
import uet.oop.bomberman.entities.mobileEntity.Bomber;
import uet.oop.bomberman.entities.mobileEntity.MobileEntity;
import uet.oop.bomberman.entities.mobileEntity.Oneal;

public class CreateMap {
  public static CreateMap createMap;
  public static int level;
  public static int column;
  public static int row;

//  public static List<MobileEntity> mobileEntities;
  public static List<immobileEntity> immobileEntities;
  public static List<immobileEntity> renderImmobileEntities;
  public static List<BombExplosion> bombExplosions;
  public static List<BombExplosion> explosion1;
  public static List<BombExplosion> explosion2;
  public static List<BombExplosion> explosion3;
  public static List<item> item;
  public static ArrayList<String> maps;

  public static boolean addUp;
  public static boolean speedUp;
  public static boolean bombUp;
  private Canvas canvas;
  private GraphicsContext gc;



  public ArrayList readMap(String path) throws IOException {
    Scanner scanner = new Scanner(Paths.get(path), "UTF-8");
    level = scanner.nextInt();
    column = scanner.nextInt();
    row = scanner.nextInt();
    scanner.nextLine();
    ArrayList<String> list = new ArrayList<>();
    for (int i = 0; i < 13; i++) {
      String lineData = scanner.nextLine();
      list.add(lineData);
    }
    scanner.close();
    return list;
  }

  public CreateMap(int level) throws IOException{

    String path = "res/levels/Level1.txt";

    immobileEntities = new ArrayList<>();
    renderImmobileEntities = new ArrayList<>();
    Checking.mobileEntities = new ArrayList<>();
    bombExplosions = new ArrayList<>();
    item = new ArrayList<>();
    maps = new ArrayList<>();
    Checking.bombExplosionReal = new int[4];
    addUp = true;
    speedUp = false;
    bombUp = false;
    Checking.bomberLive = 2;
    Bomb.countBomb = 1;
    Bomb.exploded = false;

    maps = readMap(path);
    // Tao Canvas
    canvas = new Canvas(Sprite.SCALED_SIZE * BombermanGame.WIDTH, Sprite.SCALED_SIZE * BombermanGame.HEIGHT);
    gc = canvas.getGraphicsContext2D();

    // Tao root container
    Group root = new Group();
    root.getChildren().addAll(canvas);

    // Tao scene
    Scene scene = new Scene(root);

    BombermanGame.screen.setScene(scene);
    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        if(event.getCode() == KeyCode.UP){
          Checking.bomberBaby.setHoldButton(true);
          Checking.bomberBaby.setDirection("up");
        }
        else if(event.getCode() == KeyCode.DOWN){
          Checking.bomberBaby.setHoldButton(true);
          Checking.bomberBaby.setDirection("down");
        }
        else if(event.getCode() == KeyCode.RIGHT){
          Checking.bomberBaby.setHoldButton(true);
          Checking.bomberBaby.setDirection("right");
        }
        else if(event.getCode() == KeyCode.LEFT){
          Checking.bomberBaby.setHoldButton(true);
          Checking.bomberBaby.setDirection("left");
        }
      }
    });

    scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        if(event.getCode() == KeyCode.UP){
          Checking.bomberBaby.setHoldButton(false);
        }
        else if(event.getCode() == KeyCode.DOWN){
          Checking.bomberBaby.setHoldButton(false);
        }
        else if(event.getCode() == KeyCode.RIGHT){
          Checking.bomberBaby.setHoldButton(false);
        }
        else if(event.getCode() == KeyCode.LEFT){
          Checking.bomberBaby.setHoldButton(false);
        }
        else if(event.getCode() == KeyCode.SPACE){
          CreateMap.startBomb();
        }
      }
    });

    for(int i =0; i< maps.size();i++){
      for(int j =0; j < maps.get(1).length(); j++){
        if(maps.get(i).charAt(j) == 'p'){
          Checking.bomberBaby = new Bomber(j,i,Sprite.player_right.getFxImage());
          Checking.mobileEntities.add(Checking.bomberBaby);
        }
        else if(maps.get(i).charAt(j) == '#'){
          immobileEntity im = new Wall(j,i,Sprite.wall.getFxImage());
          immobileEntities.add(im);
        }
        else if(maps.get(i).charAt(j) == '*'){
          immobileEntity im = new Brick(j,i,Sprite.brick.getFxImage());
          immobileEntities.add(im);
        }
        else if(maps.get(i).charAt(j) == 'x'){
          immobileEntity im = new Portal(j,i,Sprite.brick.getFxImage());
          immobileEntities.add(im);
        }
        else if(maps.get(i).charAt(j) == '1'){
          MobileEntity m = new Balloom(j,i,Sprite.balloom_left1.getFxImage());
          Checking.mobileEntities.add(m);
        }
        else if(maps.get(i).charAt(j) == '2'){
          MobileEntity m = new Oneal(j,i,Sprite.oneal_left1.getFxImage());
          Checking.mobileEntities.add(m);
        }
      }
    }
  }

  public static int[][] loadMap(){
    int[][] load = new int[maps.size()][maps.get(1).length()];
    int i =0;
    while (i < immobileEntities.size()) {
      load[(int) immobileEntities.get(i).getY()][(int) immobileEntities.get(i).getX()]=1;
      i++;
    }
    return load;
  }

  public static void newItem(double x, double y){
    Random random = new Random();
    int place = random.nextInt();
    if(place == 8){
      if(!bombUp){
        item.add(new bombItem(x,y,Sprite.powerup_bombs.getFxImage()));
        bombUp = true;
      }
    }

    if(place == 11){
      if(!speedUp){
        item.add(new speedItem(x,y,Sprite.powerup_speed.getFxImage()));
        speedUp = true;
      }
    }

    if(place == 4){
        item.add(new flameItem(x,y,Sprite.powerup_flames.getFxImage()));
    }
  }

  public static void deleteEntity() throws  IOException{
    if(renderImmobileEntities != null){
      int i =0;
      while (i< renderImmobileEntities.size()) {
        if(renderImmobileEntities.get(i).getImg() == null){
          newItem(renderImmobileEntities.get(i).getX(),renderImmobileEntities.get(i).getY());
          immobileEntities.remove(renderImmobileEntities.get(i));
          renderImmobileEntities.remove(renderImmobileEntities.get(i));
        }
        i++;
      }
    }

    {
      int i =0;
      while (i < Checking.mobileEntities.size()) {
        if(Checking.mobileEntities.get(i).getImg() == null){
          if(Checking.mobileEntities.get(i) == Checking.bomberBaby){
            Checking.mobileEntities.remove(i);
            Checking.bomberLive = Checking.bomberLive -1;
            if(Checking.bomberLive > 0){
              Checking.bomberBaby = new Bomber(1,1,Sprite.player_right.getFxImage());
              Bomb.countBomb = 1;
              speedUp = false;
              bombUp = false;
              Checking.mobileEntities.add(Checking.bomberBaby);
            }
          }
          else {
            newItem(Checking.mobileEntities.get(i).getX(),Checking.mobileEntities.get(i).getY());
            Checking.mobileEntities.remove(i);
            BombermanGame.key =0;
            // WinBackground;
          }
        }
        i++;
      }
    }

    int i =0;
    while (i < item.size()) {
      if(item.get(i).getImg() == null){
        item.remove(i);
      }
      i++;
    }
  }

  public static void startBomb() {
    if (!Bomb.exploded) {
      renderImmobileEntities = new ArrayList<>();
      bombExplosions.remove(bomb);
      if (explosion1 != null)
        for (BombExplosion bombExplosion : explosion1) {
          bombExplosions.remove(bombExplosion);
        }
      if (explosion3 != null)
        for (BombExplosion bombExplosion : explosion3) {
          bombExplosions.remove(bombExplosion);
        }
      if (explosion2 != null) {
        for (BombExplosion bombExplosion : explosion2) {
          bombExplosions.remove(bombExplosion);
        }
      }
      explosion1 = new ArrayList<>();
      explosion3 = new ArrayList<>();
      if (Bomb.countBomb == 2) explosion2 = new ArrayList<>();

      bomb = new Bomb(bomberBaby.getX(), bomberBaby.getY(), Sprite.bomb.getFxImage());
      //1left
      explosion1.add(new horizontal(bomb.getX() + 1, bomb.getY(), Sprite.explosion_horizontal.getFxImage()));
      //1right
      explosion1.add(new horizontal(bomb.getX() - 1, bomb.getY(), Sprite.explosion_horizontal.getFxImage()));
      //1top
      explosion1.add(new vertical(bomb.getX(), bomb.getY() - 1, Sprite.explosion_vertical.getFxImage()));
      //1down
      explosion1.add(new vertical(bomb.getX(), bomb.getY() + 1, Sprite.explosion_vertical.getFxImage()));

      if (Bomb.countBomb == 1) {
        //left
        explosion3.add(new horizontalLeft(bomb.getX() + 2, bomb.getY(), Sprite.explosion_horizontal_left_last.getFxImage()));
        //right
        explosion3.add(new horizontalRight(bomb.getX() - 2, bomb.getY(), Sprite.explosion_horizontal_right_last.getFxImage()));
        //top
        explosion3.add(new verticalTop(bomb.getX(), bomb.getY() - 2, Sprite.explosion_vertical_top_last.getFxImage()));
        //down
        explosion3.add(new verticalDown(bomb.getX(), bomb.getY() + 2, Sprite.explosion_vertical_down_last.getFxImage()));
      }
      if (Bomb.countBomb == 2) {
        //1left
        explosion2.add(new horizontal(bomb.getX() + 2, bomb.getY(), Sprite.explosion_horizontal.getFxImage()));
        //1right
        explosion2.add(new horizontal(bomb.getX() - 2, bomb.getY(), Sprite.explosion_horizontal.getFxImage()));
        //1top
        explosion2.add(new vertical(bomb.getX(), bomb.getY() - 2, Sprite.explosion_vertical.getFxImage()));
        //1down
        explosion2.add(new vertical(bomb.getX(), bomb.getY() + 2, Sprite.explosion_vertical.getFxImage()));

        //left
        explosion3.add(new horizontalLeft(bomb.getX() + 3, bomb.getY(), Sprite.explosion_horizontal_left_last.getFxImage()));
        //right
        explosion3.add(new horizontalLeft(bomb.getX() - 3, bomb.getY(), Sprite.explosion_horizontal_right_last.getFxImage()));
        //top
        explosion3.add(new verticalTop(bomb.getX(), bomb.getY() - 3, Sprite.explosion_vertical_top_last.getFxImage()));
        //down
        explosion3.add(new verticalDown(bomb.getX(), bomb.getY() + 3, Sprite.explosion_vertical_down_last.getFxImage()));

      }

      bombExplosions.add(bomb);

      for (BombExplosion explosion : explosion1) {
        bombExplosions.add(explosion);
      }
      for (BombExplosion bombExplosion : explosion3) {
        bombExplosions.add(bombExplosion);
      }
      if (Bomb.countBomb == 2) {
        for (int i = 0; i < explosion1.size(); i++) {
          bombExplosions.add(explosion2.get(i));
        }
      }

      for (int i = 0; i < 4; i++) {
        bombExplosionReal[i] = 0;
      }

      for (uet.oop.bomberman.entities.immobileEntity.immobileEntity immobileEntity : immobileEntities) {
        for (int j = 0; j < explosion1.size(); j++) {
          if (Collision(explosion1.get(j).getX(), explosion1.get(j).getY(), immobileEntity.getX(),
              immobileEntity.getY())) {
            renderImmobileEntities.add(immobileEntity);
            if (bombExplosionReal[j] == 0) {
              immobileEntity.isDifference(true);
              bombExplosionReal[j] = 1;
            }

          }
        }
      }

      if (Bomb.countBomb == 2) {
        for (int i = 0; i < immobileEntities.size(); i++) {
          {
            for (int j = 0; j < explosion2.size(); j++) {
              if (Collision(explosion2.get(j).getX(), explosion2.get(j).getY(),
                  immobileEntities.get(i).getX(), immobileEntities.get(i).getY())) {
                for (immobileEntity renderImmobileEntity : renderImmobileEntities) {
                  if (immobileEntities.get(i)
                      == renderImmobileEntity)//xem co bi xac dinh trung doi tuong da co
                    addUp = false;
                }
                if (addUp) {
                  renderImmobileEntities.add(immobileEntities.get(i));
                  if (bombExplosionReal[j] != 0) immobileEntities.get(i).setDifference(false);
                  if (bombExplosionReal[j] == 0) {
                    immobileEntities.get(i).setDifference(true);
                    bombExplosionReal[j] = 2;
                  }
                }
              }
              addUp = true;
            }
          }
        }
      }
      addUp = true;
      for (int i = 0; i < immobileEntities.size(); i++) {
        {
          for (int j = 0; j < explosion3.size(); j++) {
            if (Collision(explosion3.get(j).getX(), explosion3.get(j).getY(),
                immobileEntities.get(i).getX(), immobileEntities.get(i).getY())) {
              for (immobileEntity renderImmobileEntity : renderImmobileEntities) {
                if (immobileEntities.get(i)
                    == renderImmobileEntity)//xem co bi xac dinh trung doi tuong da co
                {
                  addUp = false;
                  break;
                }
              }
              if (addUp) {
                renderImmobileEntities.add(immobileEntities.get(i));
                if (bombExplosionReal[j] != 0) immobileEntities.get(i).setDifference(false);
                if (bombExplosionReal[j] == 0) {
                  bombExplosionReal[j] = 3;
                  immobileEntities.get(i).setDifference(true);
                }
              }
            }
            addUp = true;
          }
        }
      }
    }
  }

}
