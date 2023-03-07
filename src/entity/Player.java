package entity;

import main.GameBoard;
import main.KeyManager;

import java.awt.*;

public class Player extends Entity{
    GameBoard gb;
    KeyManager keyManager;
    public int screenWidth, screenHeight;

    public Player(GameBoard gb, KeyManager keyManager, int screenWidth, int screenHeight) { //set ค่าไว้เรียกใช้ภายในตัว class ของตัวเอง ค่าต่างๆเอามาจาก class อื่น
        super(16);
        this.gb = gb;
        this.keyManager = keyManager;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;

        setDefaultValues();
    }

    public void setDefaultValues() { //ใช้ได้เพราะ extends Entity เป็นอันเดียวที่เป็นของตัวเอง
        x = 499;
        y = 450;
        speed = 5;
    }

    public void update() { //รันหลายรอบ เพราะโดนเรียกในรัน
        if (keyManager.rightPressed) { //ถ้าเป็น true ก็จะเคลื่อนที่
            x += speed;
        }
        else if (keyManager.leftPressed) {
            x -= speed;
        }
        else if (keyManager.upPressed) {
            y -= speed;
        }
        else if (keyManager.downPressed) {
            y += speed;
        }
        if (x >= screenWidth) { //เช็คมุม เกินจอให้ไปอีกฝั่งนึง เกินขวา
            x = 0;
        } else if (x + 100 <= 0) { //เกินซ้าย
            x = screenWidth;
        }

        if (y >= screenHeight) {
            y = 0;
        } else if (y + 100 <= 0) {
            y = screenHeight;
        }
    }

    public void draw(Graphics2D g2) { //วาดตัวปลาที่เล่น
        int[] xpos = {x + 40, x + 50, x + 50};
        int[] ypos = {y + 10, y + 15, y + 5};
        g2.setColor(Color.red);
        g2.fillOval(x, y, 44, 21); //ตัว
        g2.fillPolygon(xpos, ypos, 3); //หาง
        g2.setColor(Color.YELLOW);
        g2.drawString("1", x, y); //เลเวล
        g2.setColor(Color.black);
        g2.fillOval(x + 10, y + 10, 5, 5); //ตา
    }
}
