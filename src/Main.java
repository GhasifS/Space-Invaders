import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {
    Ship s;
    Aliens a;
    Bullet b;
    PImage space;
    PImage intro;
    PImage loss;
    PImage win;
    PImage pause;
    // 0 = Instruction
    // 1 = Round 1
    // 2 = Round 2
    // 3 = Round 3
    // 4 = Round 4
    // 5 = Pause
    // 6 = Game Over
    // 7 = Game Win
    private int state = 0;
    public boolean callMethod=true;

    public static void main(String[] args) {
        PApplet.main("Main", args);
    }

    public void settings() {
        space = loadImage("space.png");
        intro = loadImage("intro.png");
        loss = loadImage("loss.png");
        win = loadImage("win.png");
        pause = loadImage("pause.png");
        size(800, 700);
        s = new Ship (this);
        a = new Aliens (this);
        b = new Bullet (this);
    }

    public void draw() {
        frameRate(60);
        image(space,0,0,800,700);
        gameState();
        states();
        if (state==6&&callMethod&&mousePressed&&mouseX>=200&&mouseX<600&&mouseY>=200&&mouseY<500) {
            state=s.round;
            s.resetGame();
            callMethod=false;
        }
        if (state==7&&callMethod&&mousePressed&&mouseX>=200&&mouseX<600&&mouseY>=200&&mouseY<500) {
            state=s.round;
            s.resetGame();
            callMethod=false;
        }
    }

    public void gameState() {
        if (state==0&&mousePressed&&mouseX>=200&&mouseX<600&&mouseY>=200&&mouseY<500) {
            state=s.round;
        }
        if (s.paused) {
            state=5;
        }
        if (!s.paused&&state!=0) {
            state=s.round;
        }
        if (s.lives<=0) {
            state = 6;
        }
        if (s.kills==72&&s.lives>0) {
            state=7;
        }
    }

    public void states () {
        if (state==0) {
            image(intro,200,200,400,300);
        }
        if (state>0) {
            s.draw();
        }
        if (state==1) {
            fill(255);
            text("ROUND 1",25,675);
        }
        if (state==2) {
            fill(255);
            text("ROUND 2",25,675);
        }
        if (state==3) {
            fill(255);
            text("ROUND 3",25,675);
        }
        if (state==4) {
            fill(255);
            text("ROUND 4",25,675);
        }
        if (state==5) {
            image(pause,200,200,400,300);
        }
        if (state==6) {
            image(loss,200,200,400,300);
            s.paused=true;
        }
        //TODO create continue button, which restarts sketch and keeps the previous score 'highscore'
        if (state==7) {
            image(win,200,200,400,300);
            s.paused=true;
        }
    }
}