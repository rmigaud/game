package Graphics;

import game.Display;
import game.Game;

public class Render3D extends Render {

    public Render3D(int width, int height) {
        super(width, height);
    }

    public void floor(Game game) {

        double rotation = game.controls.rotation;//game.getTime() / 100.0;
        double cosine = Math.cos(rotation);
        double sine = Math.sin(rotation);
        double sideToSide = game.controls.x;//Math.sin(game.getTime() / 8.0);

        double forward = game.controls.z;
        double right = game.controls.x;

        for (int y = 0; y < Display.height(); y++) {
            double yDepth = (y - height / 2.00) / height;

            double floorPosition = 8.0;
            double ceilingPosition = 8.0;
            double z = floorPosition / yDepth;
            if (yDepth < 0) z = ceilingPosition / -yDepth;


            for (int x = 0; x < width; x++) {
                double xDepth = (x - width / 2.0) / height;
                xDepth *= z;
                //left right movement depth * cos + z * sin allows for rotation,
                // - side to side is the left and right movement
                int xx = (int) (xDepth * cosine + z * sine) & 15;
                //up and down movement
                int yy = (int) (z * cosine - xDepth * sine) & 15;
                pixels[x + y * width] =
                        ((xx + (int) sideToSide) * 16) | ((yy + (int) forward) * 16) << 8;
            }

        }
    }
}
