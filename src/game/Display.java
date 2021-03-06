package game;

import Graphics.Render;
import Graphics.Screen;
import input.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;

public class Display extends Canvas implements Runnable {
    // window dimensions
    private final static int WIDTH = 800;
    private final static int HEIGHT = 600;
    private final Screen screen;
    private final BufferedImage img;
    private final Game game;
    private final int[] pixels;
    private Thread thread;
    private Render render;
    private boolean running = false;
    private ImageObserver observer;
    private final InputHandler input;

    public Display() {
        Dimension size = new Dimension(width(), height());
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        screen = new Screen(WIDTH, HEIGHT);
        game = new Game();
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

        input = new InputHandler();
        addKeyListener(input);
        addFocusListener(input);
        addMouseListener(input);
        addMouseMotionListener(input);
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

    public static int height() {
        return Display.HEIGHT;
    }

    public static int width() {
        return Display.WIDTH;
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
        int frames = 0;
        double unprocessedSeconds = 0;
        long previousTime = System.nanoTime();
        double secondsPerTick = 1.0 / 60.0;
        int tickCount = 0;
        boolean ticked = false;

        while (running) { // game loop.
            long currentTime = System.nanoTime();
            long passedTime = currentTime - previousTime;

            previousTime = currentTime;
            unprocessedSeconds += passedTime / 1000000000.0;

            while (unprocessedSeconds > secondsPerTick) {
                game.tick(input.key);
                unprocessedSeconds -= secondsPerTick;
                //ticked = true;
                tickCount++;
                if (tickCount % 60 == 0) {
                    System.out.println(frames + " fps");
                    previousTime += 1000;
                    frames = 0;
                }
            }
/*            if (ticked) {
                render();
                frames++;
            }*/
            render();
            frames++;
        }
    }
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3); // 3 dimensions
            return;
        }

        screen.testRender(game);

        /*for(int i=0; i<WIDTH*HEIGHT; i++){
            pixels[i] = screen.pixels[i];
        }*/
        System.arraycopy(screen.pixels, 0, pixels, 0, WIDTH * HEIGHT);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(img, 0, 0, WIDTH, HEIGHT, observer);
        g.dispose();
        bs.show();

    }
}
