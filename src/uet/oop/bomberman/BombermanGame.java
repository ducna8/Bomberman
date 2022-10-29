package uet.oop.bomberman;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.util.Duration;
import uet.oop.bomberman.entities.moblieEntity.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.immobileEntity.Grass;
import uet.oop.bomberman.entities.immobileEntity.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    public static ImageView backgroundView;

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();


    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        Image background = new Image("background/start.jpg");
        backgroundView = new ImageView(background);
        backgroundView.setFitHeight(Sprite.SCALED_SIZE * HEIGHT);
        backgroundView.setFitWidth(Sprite.SCALED_SIZE * WIDTH);

        Label pressSpace = new Label("Press SPACE to start <3");
        pressSpace.setTextFill(Color.YELLOW);
        pressSpace.setFont(new Font("Time New Roman", 30));
        pressSpace.setTranslateY(Sprite.SCALED_SIZE * HEIGHT - 280);
        pressSpace.setTranslateX((Sprite.SCALED_SIZE * WIDTH - 2) / 2 - 250);

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.5), pressSpace);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();

        // Tao root container
        Group root = new Group();
        root.getChildren().addAll(canvas,backgroundView,pressSpace);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.setTitle("Bomberman nhom 86");
        stage.show();

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()){
                    case SPACE:
                        render();
                }
            }
        });
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        createMap();

        Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
    }

    public void createMap() {
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }
                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
