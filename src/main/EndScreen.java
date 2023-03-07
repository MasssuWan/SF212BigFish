package main;

import javax.swing.*;
import java.awt.*;

public class EndScreen extends JPanel { //คาปิบาร่า
    GameBoard gameBoard = new GameBoard();

    public EndScreen() { //constructor
        this.setPreferredSize(new Dimension(gameBoard.screenWidth, gameBoard.screenHeight));
        this.setLayout(new FlowLayout()); //คือ Congrate, now you are a Capybara
        JButton jb = new JButton("Congrate, now you are a Capybara");
        this.add(jb); //เพิ่ม jbutton
    }

    public void drawBackground(Graphics g, String imagePath, int xpos, int ypos, int xsize, int ysize) { //วาดภาพ
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Toolkit.getDefaultToolkit().getImage(imagePath), xpos, ypos, xsize, ysize, this);
    }

    public void drawNewGame(Graphics g) { //เขีบนคำว่า new game
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.yellow);
        g2.setFont(new Font("JejuHallasan", Font.PLAIN, 50));
        g2.drawString("New Game", gameBoard.screenWidth / 2 - 120, gameBoard.screenHeight / 3);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBackground(g, "res/waterBackground.jpg", 0, 0, gameBoard.screenWidth , gameBoard.screenHeight);
        drawBackground(g, "res/image-removebg-preview.png", gameBoard.screenWidth / 2 - 125, gameBoard.screenHeight / 2, 250, 250); //capybara
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
        drawNewGame(g);
    }
}
