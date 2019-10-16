package com.javarush.games.snake;
import com.javarush.engine.cell.*;


public class SnakeGame extends Game {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    private Snake snake;
    private int turnDelay;
    private Apple apple;
    private boolean isGameStopped;
    private static final int GOAL = 28;
    private int score;
    
    @Override
    
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }
    
    private void createGame(){
        snake = new Snake(WIDTH/2,HEIGHT/2);
        createNewApple();
        isGameStopped = false;
        drawScene();
        turnDelay = 300;
        setTurnTimer(turnDelay);
        
        score = 0;
        setScore(score);
        
        
        
        
    }
    @Override
    public void onTurn(int k) {
        snake.move(apple);
        if (apple.isAlive == false) {
            createNewApple();
            score += 5;
            setScore(score);
            turnDelay -= 10;
            setTurnTimer(turnDelay);
        }
        if (snake.isAlive == false){
            gameOver();
        }
        if(snake.getLength() > GOAL){
            win();
        }
        drawScene();
    } 
    String str = "";
    private void drawScene(){
        for (int x = 0; x < WIDTH; x++){
            for (int y = 0; y < HEIGHT; y++){
                setCellValueEx(x, y, Color.DARKSEAGREEN, str);
             }
         }
         snake.draw(this);
         apple.draw(this);
        
                
     }
     
     public void onKeyPress(Key key) {
        
        if (key == Key.LEFT){
            snake.setDirection(Direction.LEFT);
        }
        if (key == Key.DOWN){
            snake.setDirection(Direction.DOWN);
        }
        if (key == Key.RIGHT){
            snake.setDirection(Direction.RIGHT);
        }
        if (key == Key.UP){
            snake.setDirection(Direction.UP);
        }
        if (key == Key.SPACE && isGameStopped == true) {
            createGame();
        }
        
        
    }
    private void createNewApple(){
        do {
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        } while (snake.checkCollision(apple));
        
        
        
    }
    private void gameOver(){
        isGameStopped = true;
        stopTurnTimer();
        showMessageDialog(Color.BLACK, "Game Over", Color.RED, 75);
        
    }
    private void win(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BLACK, "Winner", Color.RED, 75);
    }
     
}