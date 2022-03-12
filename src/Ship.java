import processing.core.PApplet;
import processing.core.PImage;

public class Ship {
    private PApplet p;
    private Aliens a;
    private Bullet b;
    PImage player;

    private int xPos = 375;
    private int yPos = 600;
    private int width = 50;
    private int height = 25;
    public int speed = 10;
    private int score = 0;
    private boolean col = false;
    private boolean aCol = false;
    public int lives = 3;
    public int kills = 0;
    public int round = 1;
    public boolean paused = false;
    public boolean reset = false;

    public Ship(PApplet p) {
        player = p.loadImage("player.png");
        this.p = p;
        a = new Aliens(p);
        b = new Bullet(p);
    }

    public void draw() {
        p.image (player,xPos,yPos,width,height);
        p.line(0,580,800,580);
        a.draw();
        b.draw();
        parameters();
        keyPressed();
        pause();
        collision();
        alienCollision();
        shipCollision();
        healthShipCollision();
        alienFiring();
        round2();
        round3();
        round4();
        life();
        stats();
    }

    public void parameters() {
        if (xPos > 800 - width) {
            xPos = 800 - width;
        }
        if (xPos < 0) {
            xPos = 0;
        }
    }

    public void keyPressed() {
        if (p.keyPressed && p.key == 'd' || p.key == 'D') {
            xPos += speed;
        }
        if (p.keyPressed && p.key == 'a' || p.key == 'A') {
            xPos -= speed;
        }
        if (p.keyPressed && p.key == ' ' && !b.shooting) {
            b.fireLocation(xPos, yPos);
        }
        if (p.keyPressed && p.key == 'p') {
            paused = true;
        }
        if (p.keyPressed && p.key == '[') {
            paused = false;
        }
    }

    public void pause() {
        if (!paused) {
            speed = 10;
            a.speed = 2;
            a.eSpeed = 10;
            a.hSpeed = 10;
            b.aSpeed = 20;
            b.speed = 30;
            if (round==2) {
                a.speed=6;
            }
            if (round==3) {
                a.speed=10;
            }
            if (round==4) {
                a.speed=14;
            }
        }
        if (paused) {
            speed =0;
            a.speed = 0;
            a.eSpeed = 0;
            a.hSpeed = 0;
            b.aSpeed = 0;
            b.speed = 0;
        }
    }
    public void collision() {
        for (int r = 0; r < a.alienXSize; r++) {
            for (int c = 0; c < a.alienYSize; c++) {
                if (b.xPos >= a.alienX[r] && b.xPos < a.alienX[r] + a.aliensMeasure && b.yPos >= a.alienY[c] && b.yPos < a.alienY[c] + a.aliensMeasure && a.aliens[r][c] != 2) {
                    col = true;
                    a.aliens[r][c] = 2;
                    b.shooting=false;
                    kills++;
                    score+=35;
                }
            }
        }
        if (col) {
            col=false;
        }
    }

    public void alienCollision() {
        if (b.aXPos >= xPos-25 && b.aXPos < xPos+25 && b.aYPos >= yPos && b.aYPos < yPos+25&&b.aShooting) {
            aCol=true;
        }
        if (aCol) {
            b.aShooting = false;
        }
    }

    public void shipCollision() {
        if (b.xPos >= a.eXPos && b.xPos< a.eXPos+50 &&b.yPos<=a.eYPos&&b.shooting) {
            a.eXPos=(int)p.random(1500,3000);
            col = true;
            b.shooting=false;
            score+=100;
        }
        if (col) {
            col=false;
        }
    }

    public void healthShipCollision() {
        if (b.xPos >= a.hXPos && b.xPos< a.hXPos+50 &&b.yPos<=a.hYPos&&b.shooting) {
            a.hXPos=(int)p.random(5000,10000);
            col = true;
            b.shooting=false;
            lives++;
        }
        if (col) {
            col=false;
        }
    }

    public void alienFiring() {
        int x = (int) p.random(a.alienXSize);
        int y = (int) p.random(a.alienYSize);
        int random = (int) p.random(100);
        if (random >= 0 && random < 5 && !b.aShooting&&a.aliens[x][y]!=2) {
            b.fireAlienLocation(a.alienX[x], a.alienY[y]);
        }
    }

    public void round2() {
        if (kills==18) {
            for (int r = 0; r < a.aliens.length; r++) {
                for (int c = 0; c < a.aliens[r].length; c++) {
                    if (c == 2) {
                        a.aliens[r][c]=1;
                    } else {
                        a.aliens[r][c] = 0;
                    }
                    a.xPos=0;
                    a.yPos=75;
                    a.speed=6;
                    a.moveRight=true;
                    a.moveLeft=false;
                }
            }
            round=2;
        }
    }

    public void round3() {
        if (kills==36) {
            for (int r = 0; r < a.aliens.length; r++) {
                for (int c = 0; c < a.aliens[r].length; c++) {
                    if (c == 2) {
                        a.aliens[r][c]=1;
                    } else {
                        a.aliens[r][c] = 0;
                    }
                    a.xPos=0;
                    a.yPos=75;
                    a.speed=10;
                    a.moveRight=true;
                    a.moveLeft=false;
                }
            }
            round=3;
        }
    }

    public void round4() {
        if (kills==54) {
            for (int r = 0; r < a.aliens.length; r++) {
                for (int c = 0; c < a.aliens[r].length; c++) {
                    if (c == 2) {
                        a.aliens[r][c]=1;
                    } else {
                        a.aliens[r][c] = 0;
                    }
                    a.xPos=0;
                    a.yPos=75;
                    a.speed=14;
                    a.moveRight=true;
                    a.moveLeft=false;
                }
            }
            round=4;
        }
    }

    public void resetGame() {
        if (!reset) {
            for (int r = 0; r < a.aliens.length; r++) {
                for (int c = 0; c < a.aliens[r].length; c++) {
                    if (c == 2) {
                        a.aliens[r][c] = 1;
                    } else {
                        a.aliens[r][c] = 0;
                    }
                }
            }
            a.xPos = 0;
            a.yPos = 75;
            a.speed = 1;
            a.moveRight = true;
            a.moveLeft = false;
            speed = 5;
            a.eSpeed = 5;
            a.hSpeed = 5;
            b.aSpeed = 10;
            b.speed = 15;
            round=1;
        }
        paused=false;
        reset=true;
    }

    public void life () {
        for (int i = 0; i < a.alienYSize; i++) {
            if (a.alienY[i] >580) {
                lives = 0;
            }
        }
        if (aCol) {
            lives--;
            aCol = false;
        }
    }

    public void stats () {
        p.textSize(50);
        p.text("SCORE:",25,50);
        p.text(score,200,50);
        p.text("LIVES:",575,50);
        p.text(lives,725,50);
    }
}