package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); //window เปลี่ยนขนาดไม่ได้ false
        window.setTitle("Big Fish");

        // แสดงหน้า Start ให้ comment EndScreen กับ GameBoard
        // แสดงหน้า End ให้ comment StartScreen กับ GameBoard
        // แสดงหน้า Game ให้ comment EndScreen กับ StartScreen

        startGame(window);

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void startGame(JFrame window) {
        StartScreen startScreen = new StartScreen(); //Class StartScreen.java
        window.add(startScreen); //add to JFrame
        window.pack(); //set size mixed with set bound for ให้ขนาดไม่เกินตัว window
    }
    public static void endGame(JFrame window) { //ตอนนี้ยังไม่โดนเรียกใช้
        EndScreen endScreen = new EndScreen();
        window.add(endScreen);
        window.pack();
    }
    public static void playGame(JFrame window) {
        GameBoard gameBoard = new GameBoard();
        window.add(gameBoard);
        window.pack();

        gameBoard.startGameThread(); //เรียก .startGameThread จาก GameBoard.java ให้ run all time อยู๋หน้านี้เพราะต้องอัพเดทจากผู้เล่น
    }
}
