package main;

import entity.Player;

import javax.swing.*; //๋J....
import java.awt.*; //graphic draw....
import java.util.Objects; //class java เราใช้เทียบอันนี้เท่ากับอันนี่
import java.util.concurrent.ThreadLocalRandom; //import random

public class GameBoard extends JPanel implements Runnable{ //โปรแกรมนี้มีการัน มีการอัพเดทได้ ไม่ได้หยึดอยู่ที่จุดเดิม

    //โค้ดที่หลงเหลือ ตอนนี้ย้ายไป Playerjava แล้ว
    public int x = 100;
    public int y = 100;
    public int speed = 10;

    private int FPS = 60; //60เฟรมต่อวิ

    public int screenWidth = 1024;
    public int screenHeight = 768;

    Image backImage; //บอกว่าอันนี้เก็บภาพ

    Thread gameThread;
    KeyManager keyManager = new KeyManager(); //เช็คว่าผู้ใช้กดปุ่มไหนในแป้นพิม
    Player player = new Player(this, keyManager, screenWidth, screenHeight); //ส่งไปให้ผู้ใช้

    public GameBoard() { //constructor
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //ไซส์ที่อยากได้ เพื่อให้ร๔้ว่าเป็นมิติ
        this.setDoubleBuffered(true); //มี 2 tread
        this.addKeyListener(keyManager); //ฟังแป้นพิม
        this.setFocusable(true); //เพื่อให้กดแป้นพิมแล้วก็ไม่มาควบคุมหน้าต่างนี้
    }

    public void startGameThread() {
        gameThread = new Thread(this); //สร้าง tread ใหม่ขึ้นมาเพื่อใช้กับหน้าต่างนี้
        gameThread.start();
    }

    private String randomLevel(int minRange, int maxRange) { //ยังไม่ได้ใช้ เอาไว้แรนด้อมเลข
        int rand = ThreadLocalRandom.current().nextInt(minRange, maxRange);
        return Integer.toString(rand);
    }

    public void drawBubble(Graphics g,  int xpos, int ypos, int size) { //สร้างฟองอากาศ
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(186, 234, 240));
        g2.fillOval(xpos, ypos, size, size);
    }

    public void drawBackground(Graphics g, String imagePath, int xpos, int ypos, int xsize, int ysize, String randomLevel) { //วาดภาพ , random level ใส่ค่าว่าคืออะไร
        Graphics2D g2 = (Graphics2D) g;
        backImage = Toolkit.getDefaultToolkit().getImage(imagePath);
        if (Objects.equals(randomLevel, "none")) { //ฉาก
            g2.drawImage(backImage, xpos, ypos, xsize, ysize, this);
        } else if (Objects.equals(randomLevel, "Danger")) { //ปลาปั๊กเป้า
            g2.drawImage(backImage, xpos, ypos, xsize, ysize, this);
            g2.setColor(Color.red);
            g2.setFont(new Font("JejuHallasan", Font.PLAIN, 10));
            g2.drawString(randomLevel, xpos, ypos);
        } else { //ปลาอื่น
            g2.drawImage(backImage, xpos, ypos, xsize, ysize, this);
            g2.setColor(Color.yellow);
            g2.setFont(new Font("JejuHallasan", Font.PLAIN, 10));
            g2.drawString(randomLevel, xpos + 20, ypos + 20);
        }
    }

    public void drawLevel(Graphics g, int level) { //เขียนสตริง
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("JejuHallasan", Font.BOLD, 20));
        g2.drawString("Level: " + level, 32, 28);
    }

    @Override
    public void run() { //ทั้งหมดนี้จะโดนรันตลอด กำหนดเวลาความถี่ในการรัน
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta -= 1;
            }
        }
    }

    public void update() {
        player.update();
    } //อะไรจะโดน update บ้าง

    public void paintComponent(Graphics g) { //เริ่มวาด
        super.paintComponent(g);

        Graphics2D g2 =  (Graphics2D) g;
        // Background
        drawBackground(g2, "res/waterBackground.jpg", 0, 0, screenWidth, screenHeight, "none");

        // Background backed-Bottom Left
        drawBackground(g2, "res/fishTile_028.png", 18, 507, 76, 77, "none");
        drawBackground(g2, "res/fishTile_087.png", 26, 524, 60, 62, "none");

        // Background backed-Bottom Left
        drawBackground(g2, "res/fishTile_064.png", 940, 517, 89, 103, "none");
        drawBackground(g2, "res/fishTile_030.png", 868, 481, 101, 103, "none");
        drawBackground(g2, "res/fishTile_086.png", 899, 547, 46, 48, "none");

        // Player
        player.draw(g2); //draw จาก class player

        // Background fronted-Bottom left
        drawBackground(g2, "res/fishTile_032.png", -35, 607, 122, 123, "none");
        drawBackground(g2, "res/fishTile_082.png", -39, 612, 142, 155, "none");
        drawBackground(g2, "res/fishTile_016.png", 72, 656, 109, 111, "none");

        //Bubble Left
        drawBubble(g2, 8, 585, 23);
        drawBubble(g2, -5, 470, 22);
        drawBubble(g2, 46, 347, 15);
        drawBubble(g2, 7, 187, 11);

        //Bubble Right
        drawBubble(g2, 957, 489, 23);
        drawBubble(g2, 1004, 360, 22);
        drawBubble(g2, 930, 169, 15);

        // Background fronted-Bottom right
        drawBackground(g2, "res/fishTile_033.png", 875, 496, 212, 216, "none");
        drawBackground(g2, "res/fishTile_085.png", 868, 563, 186, 205, "none");
        drawBackground(g2, "res/fishTile_017.png", 798, 618, 106, 150, "none");

        // Pufferfish
        drawBackground(g2, "res/fishTile_100.png", 167, 229, 42, 42, "Danger");
        drawBackground(g2, "res/fishTile_100.png", 415, 93, 42, 42, "Danger");
        drawBackground(g2, "res/fishTile_100.png", 519, 672, 42, 42, "Danger");
        drawBackground(g2, "res/fishTile_100.png", 1012, 238, 42, 42, "Danger");

        // Level 0 Fish
        drawBackground(g2, "res/fishTile_092.png", 173, 393, 77, 70, "0");
        drawBackground(g2, "res/fishTile_092.png", 269, 607, 102, 87, "0");
        drawBackground(g2, "res/fishTile_093.png", 337, 482, 78, 71, "6");
        drawBackground(g2, "res/fishTile_092.png", 701, 545, 103, 86, "2");
        drawBackground(g2, "res/fishTile_092.png", 945, 330, 77, 71, "0");


        // Level 10 Fish
        drawBackground(g2, "res/fishTile_075.png", 119, 577, 103, 87, "16");
        drawBackground(g2, "res/fishTile_075.png", 830, 442, 103, 104, "18");

        // Level 20 Fish
        drawBackground(g2, "res/fishTile_080.png", 638, 352, 103, 87, "25");

        // Level 30 Fish
        drawBackground(g2, "res/fishTile_077.png", 272, 280, 102, 87, "42");

        // Level 50 Fish
        drawBackground(g2, "res/fishTile_072.png", 836, 119, 102, 88, "56");

        // Level 70 Fish
        drawBackground(g2, "res/fishTile_078.png", 816, 588, 103, 87, "84");

        // Level
        drawLevel(g2, 1);

        g2.dispose(); //สรา้งและทิ้ง
    }
}
