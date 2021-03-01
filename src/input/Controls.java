package input;

public class Controls {
    public double x, z, rotation, deltaX, deltaZ, rotationAngle;

    public void tick(boolean forward, boolean back, boolean left, boolean right, boolean turnLeft,
                     boolean turnRight) {
        double rotationSpeed = .02, walkSpeed = 1, xMove = 0, zMove = 0;

        if (forward) {
            zMove++;
        }
        if (back) {
            zMove--;
        }
        if (left) {
            xMove--;
        }
        if (right) {
            xMove++;
        }
        if (turnLeft) {
            rotationAngle -= rotationSpeed;
        }
        if (turnRight) {
            rotationAngle += rotationSpeed;
        }

        deltaX += (xMove * Math.cos(rotation) + zMove * Math.sin(rotation)) * walkSpeed;
        deltaZ += (zMove * Math.cos(rotation) - xMove * Math.sin(rotation)) * walkSpeed;
        x += deltaX;
        z += deltaZ;
        deltaX *= 0.1;
        deltaZ *= 0.1;
        rotation += rotationAngle;
        rotationAngle *= 0.5;
    }
}
