package com.javarush.games.snake;

import java.util.ArrayList;
import java.util.List;
import com.javarush.engine.cell.*;
import com.javarush.engine.cell.Game;
import java.util.*;


public class Snake {
    
    private List<GameObject> snakeParts =  new ArrayList<>();
    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    public boolean isAlive = true;
    
    
    
    
    public Snake(int x, int y){
        GameObject g1 = new GameObject(x,y);
        GameObject g2 = new GameObject(x + 1,y);
        GameObject g3 = new GameObject(x + 2,y);
        
        snakeParts.add(g1);
        snakeParts.add(g2);
        snakeParts.add(g3);
    }
    
    public void draw(Game game) {

        for (int i = 0; i < snakeParts.size(); i++) {
            if (i == 0)
                game.setCellValueEx(
                        snakeParts.get(i).x,
                        snakeParts.get(i).y,
                        Color.NONE,
                        HEAD_SIGN,
                        (isAlive == true) ? Color.BLACK : Color.RED,
                        75);
            else
                game.setCellValueEx(
                        snakeParts.get(i).x,
                        snakeParts.get(i).y,
                        Color.NONE,
                        BODY_SIGN,
                        (isAlive == true) ? Color.BLACK : Color.RED,
                        75);
        }
    }

    private Direction direction = Direction.LEFT;
    
    public void setDirection(Direction direction) {
        switch (this.direction) {
            case LEFT:
            case RIGHT:
                if (snakeParts.get(0).x == snakeParts.get(1).x) return;
                break;
            case UP:
            case DOWN:
                if (snakeParts.get(0).y == snakeParts.get(1).y) return;
                break;
        }
        /*
        if (direction == Direction.LEFT && this.direction == Direction.RIGHT){
            return;
        }
        if (direction == Direction.RIGHT && this.direction == Direction.LEFT){
            return;
        }
        if (direction == Direction.DOWN && this.direction == Direction.UP){
            return;
        }
        if (direction == Direction.UP && this.direction == Direction.DOWN){
            return;
        }
        */
        this.direction = direction;
    }
        
    
    boolean flag = false;
    
    public void move(Apple apple) {
        GameObject head = createNewHead();
        if (checkCollision(head) == true) {
            isAlive = false;
            return;
        }
        
        if(head.x > SnakeGame.WIDTH -1 || head.y > SnakeGame.HEIGHT -1
           || head.x < 0 || head.y < 0){
                 isAlive = false;
            }
        else if(head.x == apple.x && head.y == apple.y){
            apple.isAlive = false;
            snakeParts.add(0, head);
        }
        else {
             snakeParts.add(0, head);
             removeTail();
        }
        

    }

    public GameObject createNewHead() {
    GameObject objectnew = null; 

        if (direction == Direction.UP) {
             objectnew = new GameObject(snakeParts.get(0).x, snakeParts.get(0).y - 1);
             
        } else if (direction == Direction.DOWN) {
             objectnew = new GameObject(snakeParts.get(0).x, snakeParts.get(0).y + 1);
             
        } else if (direction == Direction.RIGHT) {
             objectnew = new GameObject(snakeParts.get(0).x + 1, snakeParts.get(0).y);
             
        } else if (direction == Direction.LEFT) {
             objectnew = new GameObject(snakeParts.get(0).x - 1, snakeParts.get(0).y);
             
        }
         return objectnew;
    }
    public void removeTail(){
        snakeParts.remove(snakeParts.size() - 1);
    }
    public boolean checkCollision(GameObject object){
        for(int i = 0; i < snakeParts.size(); i++){
            if(snakeParts.get(i).x == object.x && snakeParts.get(i).y == object.y){
                return true;
            }
        }
        return false;
    }
    
    
    public int getLength(){
        return snakeParts.size();
    }
    
    

}