package Graphics;

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

    public void render() {
        int imgHeight = 256;
        int imgWidth = 256;
        int xmovement = (int) (Math.sin(System.currentTimeMillis() % 1000.0 / 1000 * Math.PI * 2) * 100);
        int ymovement = (int) (Math.cos(System.currentTimeMillis() % 1000.0 / 1000 * Math.PI * 2) * 100);
        int xPos = (width - 256) / 2 + xmovement;
        int yPos = (height - 256) / 2 - ymovement;
        draw(test, xPos, yPos);
    }

    @Override
    public String toString() {
        return "Screen{}";
    }
}
