package uet.oop.bomberman.Image;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.Label;
public class menu {
  public menu(){

    Image image = new Image("Background/start.jpg");
    ImageView iv1 = new ImageView();
    iv1.setImage(image);

    Label label = new Label("Press SPACE to start!!!");


  }
}
