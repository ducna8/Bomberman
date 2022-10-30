package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.List;
import uet.oop.bomberman.entities.immobileEntity.immobileEntity;
import uet.oop.bomberman.entities.mobileEntity.Bomber;
import uet.oop.bomberman.entities.mobileEntity.MobileEntity;

public class Checking {

  public static List<immobileEntity> immobileEntities;
  public static List<MobileEntity> mobileEntities;
  public static List<immobileEntity> renderImmobileEntities;
  public static boolean collisionEnemy;

  public Bomber bomberBaby;


  public static List<String> movable(double axisX, double axisY) {
    List<String> list = new ArrayList<>();

    double x = Math.ceil(axisX * 100) / 100;
    double y = Math.ceil(axisY * 100) / 100;

    list.add("left");
    list.add("right");
    list.add("up");
    list.add("down");

    for (int i = 0; i < immobileEntities.size() - 1; i++) {
      if (immobileEntities.get(i).getX() == x - 1 && immobileEntities.get(i).getY() == y) {
        list.remove("left");
      }
      if (immobileEntities.get(i).getX() == x + 1 && immobileEntities.get(i).getY() == y) {
        list.remove("right");
      }
      if (immobileEntities.get(i).getX() == x && immobileEntities.get(i).getY() == y - 1) {
        list.remove("up");
      }
      if (immobileEntities.get(i).getX() == x && immobileEntities.get(i).getY() == y + 1) {
        list.remove("down");
      }

    }
    return list;
  }

  public static boolean CollisionMove(double aX, double aY, double range, double sizeEntity,
      String direction) {
    double x = (double) Math.round(aX * 100) / 100;
    double y = (double) Math.round(aY * 100) / 100;
    for (int i = 0; i < immobileEntities.size(); i++) {

      if (direction == "left") {
        if ((immobileEntities.get(i).getY() > y - 1 && immobileEntities.get(i).getY() < y
            && immobileEntities.get(i).getX() < x)
            || (immobileEntities.get(i).getY() > y && immobileEntities.get(i).getY() < y + 1
            && immobileEntities.get(i).getX() < x)
            || (immobileEntities.get(i).getY() == y && immobileEntities.get(i).getX() < x)) {
          if (((double) Math.round((x - 1 - range) * 100) / 100) < immobileEntities.get(i).getX()) {
            return false;
          }
        }
      }

      if (direction == "right") {
        if (((immobileEntities.get(i).getY() > y && immobileEntities.get(i).getY() < y + 1
            && immobileEntities.get(i).getX() > x)
            || immobileEntities.get(i).getY() > y - 1 && immobileEntities.get(i).getY() < y
            && immobileEntities.get(i).getX() > x)
            || (immobileEntities.get(i).getY() == y && immobileEntities.get(i).getX() > x)) {
          if (((double) Math.round((x + 1 + range) * 100) / 100) > immobileEntities.get(i).getX()) {
            return false;
          }
        }
      }

      if (direction == "up") {
        if ((immobileEntities.get(i).getX() < x + 1
            && immobileEntities.get(i).getX() > x && immobileEntities.get(i).getY() < y) || (
            immobileEntities.get(i).getX() == x && immobileEntities.get(i).getY() < y) || (
            immobileEntities.get(i).getX() > x - 1 && immobileEntities.get(i).getX() < x
                && immobileEntities.get(i).getY() < y)) {
          if (y - range - 1 < immobileEntities.get(i).getY()) {
            return false;
          }
        }
      }
      if (direction == "down") {
        if ((immobileEntities.get(i).getX() > x - 1 && immobileEntities.get(i).getX() < x
            && immobileEntities.get(i).getY() > y) || (immobileEntities.get(i).getX() < x + 1
            && immobileEntities.get(i).getX() > x && immobileEntities.get(i).getY() > y) || (
            immobileEntities.get(i).getX() == x && immobileEntities.get(i).getY() > y)) {
          if (y + range + 1 > immobileEntities.get(i).getY()) {
            return false;
          }
        }

      }
    }
    return true;
  }

  public static boolean Collision(double x1, double y1, double x2, double y2) {
    double X1 = (double) Math.round(x1 * 100) / 100;
    double Y1 = (double) Math.round(x2 * 100) / 100;
    double X2 = (double) Math.round(y1 * 100) / 100;
    double Y2 = (double) Math.round(y2 * 100) / 100;

    if (X2 + 1 > X1 && X2 <= X1 && Y2 + 1 > Y1 && Y2 <= Y1) {
      return true;
    } else if (X2 - 1 < X1 && X2 >= X1 && Y2 - 1 < Y1 && Y2 >= Y1) {
      return true;
    } else if (X2 + 1 > X1 && X2 <= X1 && Y2 - 1 < Y1 && Y2 >= Y1) {
      return true;
    } else if (X2 - 1 < X1 && X2 >= X1 && Y2 + 1 > Y1 && Y2 <= Y1) {
      return true;
    } else {
      return false;
    }

  }

  public static boolean collisionEnemy(double axisX, double axisY) {
    for (int i = 0; i < mobileEntities.size(); i++) {
      if (Collision(axisX, axisY, mobileEntities.get(i).getX(), mobileEntities.get(i).getY())
          && !(mobileEntities.get(i) instanceof Bomber)){
        return true;
      }
    }
    return false;
  }

}


