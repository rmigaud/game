package input;

import java.awt.event.*;
import java.util.Arrays;

public class InputHandler implements KeyListener, FocusListener, MouseListener, MouseMotionListener {

    public boolean[] key = new boolean[68836];

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {
        Arrays.fill(key, false);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //System.out.println("keyPressed = " + e);
        if (keyCode > 0 && keyCode < key.length) {
            key[keyCode] = true;
            System.out.println(e.getKeyChar() + " pressed");
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        Arrays.fill(key, false);
        System.out.println(e.getKeyChar() + " key released.");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
