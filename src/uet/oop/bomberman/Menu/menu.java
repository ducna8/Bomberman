package uet.oop.bomberman.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
public class menu {
  public menu(){

    Image image = new Image("Background/start.jpg");
    ImageView iv1 = new ImageView();
    iv1.setImage(image);
    
    Label label = new Label("Press SPACE to start!!!");


  }
}
