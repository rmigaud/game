package Graphics;

import game.Display;

import java.util.Random;

public class Screen extends Render {
    private final Render test;

    public Screen(int width, int height) {
        super(width, height);
        Random random = new Random();

        test = new Render(256, 256);
        for (int i = 0; i < 256 * 256; i++) {
            test.pixels[i] = random.nextInt();
        }
    }

    public void testRender() {
        //clear screen
        for (int pixel = 0; pixel < Display.HEIGHT * Display.WIDTH; pixel++) {
            pixels[pixel] = 0;
        }

        int imgHeight = 256;
        int imgWidth = 256;
        int xmovement =
                (int) (Math.sin(System.currentTimeMillis() % 1000.0 / 1000 * Math.PI * 2) * 200);
        int ymovement =
                (int) (Math.cos(System.currentTimeMillis() % 1000.0 / 1000 * Math.PI * 2) * 200);
        int xPos = (width - imgHeight) / 2 + xmovement;
        int yPos = (height - imgWidth) / 2 - ymovement;
        draw(test, xPos, yPos);
    }

    @Override
    public String toString() {
        return "Screen{}";
    }
}
