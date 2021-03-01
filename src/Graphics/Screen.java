package Graphics;

import game.Display;
import game.Game;

import java.util.Random;

public class Screen extends Render {
    private final Render test;
    private final Render3D render;

    public Screen(int width, int height) {
        super(width, height);
        Random random = new Random();
        render = new Render3D(Display.width(), Display.height());
        test = new Render(256, 256);
        for (int i = 0; i < 256 * 256; i++) {
            test.pixels[i] = random.nextInt() * (random.nextInt(5) / 4);
        }
    }

    public void testRender(Game game) {
        //clear screen
        for (int pix = 0; pix < Display.height() * Display.width(); pix++) {
            pixels[pix] = 0;
        }

        render.floor(game);
        draw(render, 0, 0);
    }

    @Override
    public String toString() {
        return "Screen{}";
    }
}
