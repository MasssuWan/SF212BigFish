package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener; //abstract class

public class StartScreen extends JPanel implements MouseListener {

    GameBoard gameBoard = new GameBoard();
    protected Rectangle rect; //เรียกใช้ภายใน package เดียวกัน , เรียกสี่เหลี่ยม Rectangle เป็น rect

    public StartScreen() { //Constructor
        this.setPreferredSize(new Dimension(gameBoard.screenWidth, gameBoard.screenHeight)); //เป็น size อะไรก็ได้ แต่ชั้นต้องการอันนี้
        this.setLayout(new BorderLayout());
        JLabel tf = new JLabel("Create by Wansirimanee Wattanasiri");
        JLabel jb = new JLabel("Version 0.1"); //ตอนแรกจะให้เป็น j button แต่ตอนนี้ j Label
        this.add(tf, BorderLayout.NORTH);
        this.add(jb, BorderLayout.SOUTH);

        rect = new Rectangle(356, 325, 350, 100); //สรา้ง 4 เหลี่ยมขึ้นมา โยเริ่มที่ตำแหน่ง 356 325 มีความกว้าง สูง 350 100

        addMouseListener(this); //เรียกใช้ หน้านี้จะเริ่มฟังเมาส์
    }

    public void drawBackground(Graphics g, String imagePath, int xpos, int ypos, int xsize, int ysize) { //รับ graphics g เข้ามาเพื่อสร้างกราฟฟิก
        Graphics2D g2 = (Graphics2D) g; //explicit casting เปลี่ยนจากกราฟฟิคธรรมดาให้เ็ฯกราฟฟิค 2d โดยการแคส Graphics 2d เข้าไปใน g
        g2.drawImage(Toolkit.getDefaultToolkit().getImage(imagePath), xpos, ypos, xsize, ysize, this); //เลือกให้ draw เป็นภาพ เลือกใช้ .getDefaultToolkit().getImage(imagePath) เพื่อดึงภสพจากไฟล์
    }

    public void drawTitle(Graphics g) { //สร้างกราฟฟิคคำว่า Big fish
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(250, 12, 154));
        g2.setFont(new Font("JejuHallasan", Font.PLAIN, 100));
        g2.drawString("BIG FISH", gameBoard.screenWidth / 2 - 200, gameBoard.screenHeight / 3); //2-200 คือครึ่งของสกรีน
    }

    public void drawStart(Graphics g) { //คล้ายๆ draw.title เป็นปุ่มเริ่มเกม ตรงนี้เป็นสตริงจ้า
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.yellow);
        g2.setFont(new Font("JejuHallasan", Font.PLAIN, 50));
        g2.drawString("START GAME", gameBoard.screenWidth / 2 - 150, gameBoard.screenHeight / 2);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g); //บอกเกมว่าให้เริ่มวาดเลย มึงวาดเรยค่ะ ทุกครั้งที่มีอัพเดทตำแหน่งคือวาดใหม่ วาดใหม่ในทุกเฟรม
        drawBackground(g, "res/waterBackground.jpg", 0, 0, gameBoard.screenWidth , gameBoard.screenHeight);

        //สาหร่ายกับหิน
        Graphics2D g2 = (Graphics2D) g;
        // Background backed-Bottom Left
        drawBackground(g2, "res/fishTile_028.png", 18, 507, 76, 77);
        drawBackground(g2, "res/fishTile_087.png", 26, 524, 60, 62);

        // Background backed-Bottom Left
        drawBackground(g2, "res/fishTile_064.png", 940, 517, 89, 103);
        drawBackground(g2, "res/fishTile_030.png", 868, 481, 101, 103);
        drawBackground(g2, "res/fishTile_086.png", 899, 547, 46, 48);

        // Background fronted-Bottom left
        drawBackground(g2, "res/fishTile_032.png", -35, 607, 122, 123);
        drawBackground(g2, "res/fishTile_082.png", -39, 612, 142, 155);
        drawBackground(g2, "res/fishTile_016.png", 72, 656, 109, 111);

        // Background fronted-Bottom right
        drawBackground(g2, "res/fishTile_033.png", 875, 496, 212, 216);
        drawBackground(g2, "res/fishTile_085.png", 868, 563, 186, 205);
        drawBackground(g2, "res/fishTile_017.png", 798, 618, 106, 150);

        drawTitle(g); //เริ่มวาดไตเติ้น กับสตาร์ท
        drawStart(g);

        // Text Fish ปลาหัวข้อหลัก 2 ตัว
        drawBackground(g2, "res/fishTile_078.png", 360, 135, 68, 43);
        drawBackground(g2, "res/fishTile_075.png", 675, 200, 151, 105);
    }

    @Override //permission สูงสุด ทับทุกอย่าง MouseListener; //abstract class และใช้ MouseEvent เพื่อบอกว่าเกิดอะไรขึ้น
    public void mouseClicked(MouseEvent e) { //ไว้คลิ๊กเริ่มเกม
        Point p = e.getPoint();
        if(rect.contains(p)) { //เมื่อจุดคลิ้กอยู่ในกล่อง 4 เหลี่ยม
            this.setVisible(false); //มองไม่เห็นหน้าต่างตอนนี้
            JFrame jf = new JFrame();
            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jf.setResizable(false);
            jf.setTitle("Big Fish");
            Main.playGame(jf); //คลาสเมน เล่นเกม
            jf.setLocationRelativeTo(null); //ไว้ให้อยู๋ตรงกลาง
            jf.setVisible(true); //ให้เห็นหน้าต่างเกมแทน
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
