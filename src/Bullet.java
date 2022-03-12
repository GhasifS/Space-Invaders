import processing.core.PApplet;

public class Bullet {
    private PApplet p;

    public int xPos = 1000;
    public int yPos = 1000;
    public int width = 5;
    public int height = 15;
    public int speed = 30;
    public boolean shooting = false;
    public int aXPos = 1000;
    public int aYPos = 1000;
    public int aWidth = 5;
    public int aHeight = 10;
    public int aSpeed = 20;
    public boolean aShooting = false;

    public Bullet (PApplet p) {
        this.p = p;
    }

    public void draw() {
        p.fill(0,255,0);
        p.rect(xPos+20,yPos,width,height);
        p.fill(255,0,0);
        p.rect(aXPos+25,aYPos,aWidth,aHeight);
        firing();
        alienFiring();
    }


    public void firing () {
        if (shooting) {
            yPos-=speed;
        }
        if (!shooting) {
            yPos=1000;
        }
        if (yPos<0) {
            yPos=1000;
            shooting=false;
        }
    }

    public void alienFiring () {
        if (aShooting) {
            aYPos+=aSpeed;
        }
        if (!aShooting) {
            aYPos=1000;
        }
        if (aYPos>700) {
            aYPos=1000;
            aShooting=false;
        }
    }

    public void fireLocation (int x, int y) {
        xPos=x;
        yPos=y;
        shooting=true;
    }

    public void fireAlienLocation (int x, int y) {
        aXPos=x;
        aYPos=y;
        aShooting=true;
    }
}