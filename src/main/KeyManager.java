package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    public boolean rightPressed, leftPressed, upPressed, downPressed; //ดูว่าปุ่มนั้นโดนกดใช่มั้ย เช็คค่า โดนกดเป็นจริง ไม่โดนเป็นผิด

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { //เมื่อกดลงไป ดูว่าผู้ใช้กดปุ่มอะไร
        int key = e.getKeyCode(); //ดูว่ากดปุ่มอะไรไป ส่งกลับเป็นค่า int

        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
        if (key == KeyEvent.VK_UP) {
            upPressed = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { //เมื่อผู้ใช้ปล่อยปุ่ม เรียกใช้ event
        int key = e.getKeyCode(); //ดูว่าปล่อยปุ่มอะไรไป ส่งกลับเป็นค่า int

        if (key == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (key == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (key == KeyEvent.VK_UP) {
            upPressed = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            downPressed = false;
        }
    }
}
