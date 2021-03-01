package game;

import input.Controls;

import java.awt.event.KeyEvent;

public class Game {
    public static double time;
    public Controls controls;

    public Game() {
        controls = new Controls();
    }

    /**
     * Tick an internal unit of time.
     */
    public void tick(boolean[] key) {
        time++;
        boolean forward = key[KeyEvent.VK_W];
        boolean back = key[KeyEvent.VK_S];
        boolean left = key[KeyEvent.VK_A];
        boolean right = key[KeyEvent.VK_D];
        boolean turnLeft = key[KeyEvent.VK_Q];
        boolean turnRight = key[KeyEvent.VK_E];
        controls.tick(forward, back, left, right, turnLeft, turnRight);
    }

    public double getTime() {
        return time;
    }
}
