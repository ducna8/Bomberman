package uet.oop.bomberman.entities;

import java.util.ArrayList;
import java.util.List;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.immobileEntity.immobileEntity;
import uet.oop.bomberman.entities.items.bombItem;
import uet.oop.bomberman.entities.items.flameItem;
import uet.oop.bomberman.entities.items.item;
import uet.oop.bomberman.entities.items.speedItem;
import uet.oop.bomberman.entities.mobileEntity.Bomber;
import uet.oop.bomberman.entities.mobileEntity.MobileEntity;

public class Checking {

  public static List<immobileEntity> immobileEntities;
  public static List<MobileEntity> mobileEntities;
  public static List<immobileEntity> renderImmobileEntities;
  public static boolean collisionEnemy;

  public static List<item> items;
  public static int bomberLive;
  public static Bomb bomb;
  public static int[] bombExplosionReal;
  public static Bomber bomberBaby;

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
    for (uet.oop.bomberman.entities.immobileEntity.immobileEntity immobileEntity : immobileEntities) {

      if (direction == "left") {
        if ((immobileEntity.getY() > y - 1 && immobileEntity.getY() < y
            && immobileEntity.getX() < x)
            || (immobileEntity.getY() > y && immobileEntity.getY() < y + 1
            && immobileEntity.getX() < x)
            || (immobileEntity.getY() == y && immobileEntity.getX() < x)) {
          if (((double) Math.round((x - 1 - range) * 100) / 100) < immobileEntity.getX()) {
            return false;
          }
        }
      }

      if (direction == "right") {
        if (((immobileEntity.getY() > y && immobileEntity.getY() < y + 1
            && immobileEntity.getX() > x)
            || immobileEntity.getY() > y - 1 && immobileEntity.getY() < y
            && immobileEntity.getX() > x)
            || (immobileEntity.getY() == y && immobileEntity.getX() > x)) {
          if (((double) Math.round((x + 1 + range) * 100) / 100) > immobileEntity.getX()) {
            return false;
          }
        }
      }

      if (direction == "up") {
        if ((immobileEntity.getX() < x + 1
            && immobileEntity.getX() > x && immobileEntity.getY() < y) || (
            immobileEntity.getX() == x && immobileEntity.getY() < y) || (
            immobileEntity.getX() > x - 1 && immobileEntity.getX() < x
                && immobileEntity.getY() < y)) {
          if (y - range - 1 < immobileEntity.getY()) {
            return false;
          }
        }
      }
      if (direction == "down") {
        if ((immobileEntity.getX() > x - 1 && immobileEntity.getX() < x
            && immobileEntity.getY() > y) || (immobileEntity.getX() < x + 1
            && immobileEntity.getX() > x && immobileEntity.getY() > y) || (
            immobileEntity.getX() == x && immobileEntity.getY() > y)) {
          if (y + range + 1 > immobileEntity.getY()) {
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
    for (MobileEntity mobileEntity : mobileEntities) {
      if (Collision(axisX, axisY, mobileEntity.getX(), mobileEntity.getY())
          && !(mobileEntity instanceof Bomber)) {
        return true;
      }
    }
    return false;
  }

  public static void collisionBomb(double axisX, double axisY) {
    for (MobileEntity mobileEntity : mobileEntities) {
      if (Collision(axisX, axisY, mobileEntity.getX(), mobileEntity.getY())) {
        mobileEntity.setDead(true);
      }
    }
  }

  public void collisionItem(double axisX, double axisY) {
    for (uet.oop.bomberman.entities.items.item item : items) {
      if (Collision(bomberBaby.getX(), bomberBaby.getY(), item.getX(),
          item.getY())) {
        if (item instanceof speedItem) {
          bomberBaby.setDistance(1.0);
          item.setTransform(false);
        }
        if (item instanceof bombItem) {
          Bomb.countBomb = 2;
          item.setTransform(false);
        }
        if (item instanceof flameItem) {
          bomberLive += 1;
          item.setTransform(false);
        }
      }
    }
  }
  public static boolean checkEmpty(double a, double b, double i) {
    return (a < i && i < b) || (a > i && i > b);
  }

  public boolean checkVertical(double x, double y){
    for (MobileEntity mobileEntity : mobileEntities) {
      double notMoveX = mobileEntity.getX();
      double notMoveY = mobileEntity.getY();
      double bomberY = Checking.bomberBaby.getY();
      if (notMoveX == x && checkEmpty(bomberY, y, notMoveY)) {
        return false;
      }
    }
    return true;
  }

  public boolean checkHorizontal(double x, double y){
    for (MobileEntity mobileEntity : mobileEntities) {
      double notMoveX = mobileEntity.getX();
      double notMoveY = mobileEntity.getY();
      double bomberY = Checking.bomberBaby.getX();
      if (notMoveX == x && checkEmpty(bomberY, y, notMoveY)) {
        return false;
      }
    }
    return true;
  }


}


