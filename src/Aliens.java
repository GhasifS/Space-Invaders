import processing.core.PApplet;
import processing.core.PImage;

public class Aliens {
    private PApplet p;
    PImage alien1;
    PImage alien2;
    PImage ship;
    PImage hship;

    public int[][] aliens;
    public int[] alienX;
    public int[] alienY;
    public int alienXSize = 6;
    public int alienYSize = 3;
    public int aliensMeasure = 50;
    public int xPos=0;
    public int yPos=75;
    public int speed = 2;
    public boolean moveRight = true;
    public boolean moveLeft = false;
    public int eXPos = 3500;
    public int eYPos = 100;
    public int eWidth = 50;
    public int eHeight = 25;
    public int eSpeed = 10;
    public int hXPos= 5000;
    public int hYPos = 100;
    public int hWidth = 50;
    public int hHeight = 25;
    public int hSpeed = 10;

    public Aliens(PApplet p) {
        alien1 = p.loadImage("alien1.png");
        alien2 = p.loadImage("alien2.png");
        ship = p.loadImage("ship.png");
        hship = p.loadImage("hship.png");
        alienX = new int[alienXSize];
        alienY = new int[alienYSize];
        this.p = p;
        aliens = new int[alienXSize][alienYSize];
        for (int r = 0; r < aliens.length; r++) {
            for (int c = 0; c < aliens[r].length; c++) {
                if (c == 2) {
                    aliens[r][c]=1;
                } else {
                    aliens[r][c] = 0;
                }
            }
        }
    }

    public void draw() {
        p.image(ship,eXPos,eYPos,eWidth,eHeight);
        p.image(hship,hXPos,hYPos,hWidth,hHeight);
        drawAliens();
        move();
        eMove();
        hMove();
    }

    public void drawAliens() {
        for (int i = 0; i < alienXSize; i++) {
            alienX[i] = xPos + i * 60;
        }
        for (int i = 0; i < alienYSize; i++) {
            alienY[i] = yPos + i * 45;
        }

        for (int r = 0; r < alienXSize; r++) {
            for (int c = 0; c < alienYSize; c++) {
                if (aliens[r][c]==1) {
                    p.image(alien2,alienX[r], alienY[c], aliensMeasure, aliensMeasure);
                }
                if (aliens[r][c]==0) {
                    p.image(alien1,alienX[r], alienY[c], aliensMeasure, aliensMeasure);
                }
            }
        }
    }

    public void move () {
        if (moveRight) {
            xPos+=speed;
        }
        if (moveLeft) {
            xPos-=speed;
        }
        if (xPos>450) {
            moveLeft=true;
            moveRight=false;
            yPos+=50;
        }
        if (xPos<0) {
            moveLeft=false;
            moveRight=true;
            yPos+=50;
        }
    }

    public void eMove() {
        eXPos-=eSpeed;
        if (eXPos<-100) {
            eXPos=(int)p.random(1500,3000);
        }
    }

    public void hMove() {
        hXPos-=hSpeed;
        if (hXPos<-100) {
            hXPos=(int)p.random(5000,10000);
        }
    }
}