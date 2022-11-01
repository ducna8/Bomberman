package uet.oop.bomberman.Menu;

import java.io.IOException;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.CreateMap;
import uet.oop.bomberman.graphics.Sprite;

public class Menu {
  private Scene scene1;

  public Menu() {
//    mediaPlayer.mediaMenuPlayer.play();

    int HEIGHT = BombermanGame.HEIGHT;
    int WIDTH = BombermanGame.WIDTH;
    // tao scene menu
    Image img1 = new Image("file:res/background/start.jpg");
    ImageView imgv1 = new ImageView(img1);
    imgv1.setFitHeight(Sprite.SCALED_SIZE * HEIGHT - 2);
    imgv1.setFitWidth(Sprite.SCALED_SIZE * WIDTH - 2);

    Label label1 = new Label("Press SPACE to start");
    label1.setTextFill(Color.WHITE);
    label1.setFont(new Font("Arial", 30));
    label1.setTranslateY(Sprite.SCALED_SIZE * HEIGHT - 100);
    label1.setTranslateX((Sprite.SCALED_SIZE * WIDTH - 2) / 2 - 180);

    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), label1);
    fadeTransition.setFromValue(0.0);
    fadeTransition.setToValue(1.0);
    fadeTransition.setCycleCount(Animation.INDEFINITE);
    fadeTransition.play();

    Group layout = new Group();
    layout.getChildren().addAll(imgv1, label1);

    scene1 = new Scene(layout, Sprite.SCALED_SIZE * WIDTH - 2, Sprite.SCALED_SIZE * HEIGHT - 2);
    BombermanGame.screen.setScene(scene1);

    scene1.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        switch (event.getCode()) {
          case SPACE:
            BombermanGame.key++;
            try {
              CreateMap.createMap = new CreateMap(1);
            } catch (IOException e) {
              e.printStackTrace();
            }
//            mediaPlayer.mediaMenuPlayer.stop();
            break;
        }
      }
    });
  }
}
