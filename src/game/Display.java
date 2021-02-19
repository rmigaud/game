package game;

import Graphics.Render;
import Graphics.Screen;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;

public class Display extends Canvas implements Runnable {
    // window dimensions
    final static int WIDTH = 800;
    final static int HEIGHT = 600;
    private final Screen screen;
    private final BufferedImage img;
    private final int[] pixels;
    private Thread thread;
    private Render render;
    private boolean running = false;
    private ImageObserver observer;

    public Display() {
        screen = new Screen(WIDTH, HEIGHT);
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();
    }

    public static void main(String[] args) {
        Display display = new Display();
        JFrame frame = new JFrame();

        frame.add(display); // attach Display to JFrame.
        frame.pack(); // resizes frame to smallest fit.
        frame.setSize(WIDTH, HEIGHT);
        frame.setTitle("Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // center initial window
        frame.setResizable(false); // disable resizing of the window
        frame.setVisible(true); //set visibility
        display.start();
        System.out.println("game completed successfully"); // test
    }

    private void start() {
        if (!running) {
            running = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    private void stop() {
        if (running) {
            running = false;
            try {
                thread.join();
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
    }

    public void run() {
        while (running) { // game loop.
            tick();
            render();
        }
    }

    private void tick() {

    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3); // 3 dimensions
            return;
        }

        screen.render();

        /*for(int i=0; i<WIDTH*HEIGHT; i++){
            pixels[i] = screen.pixels[i];
        }*/
        System.arraycopy(screen.pixels, 0, pixels, 0, WIDTH * HEIGHT);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH * 10, HEIGHT * 10, observer);
        g.dispose();
        bs.show();

    }
}