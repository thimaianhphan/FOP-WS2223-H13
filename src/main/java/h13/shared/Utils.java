package h13.shared;

import h13.model.gameplay.Direction;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import static h13.controller.GameConstants.ORIGINAL_GAME_BOUNDS;

/**
 * A {@link Utils} class containing utility methods.
 */
public class Utils {

    /**
     * Returns the closest position for the given {@link Bounds} that is within the game bounds.
     *
     * @param bounds The bounds to be clamped.
     * @return the clamped coordinate.
     * @see <a href="https://en.wikipedia.org/wiki/Clamping_(graphics)">Clamping_(graphics)</a>
     * @see h13.controller.GameConstants
     */
    public static Bounds clamp(final Bounds bounds) {
        if (ORIGINAL_GAME_BOUNDS.contains(bounds)) return new BoundingBox(bounds.getMinX(), bounds.getMinY(), bounds.getWidth(), bounds.getHeight());
//        else if (bounds.getHeight() > ORIGINAL_GAME_BOUNDS.getHeight() || bounds.getWidth() > ORIGINAL_GAME_BOUNDS.getWidth()) return null;
        else {
            double newMinX, newMinY;
            if (bounds.getMinX() < ORIGINAL_GAME_BOUNDS.getMinX()) newMinX = ORIGINAL_GAME_BOUNDS.getMinX();
            else if (bounds.getMinX() + bounds.getWidth() >= ORIGINAL_GAME_BOUNDS.getMinX() + ORIGINAL_GAME_BOUNDS.getWidth())
                newMinX = ORIGINAL_GAME_BOUNDS.getMinX() + ORIGINAL_GAME_BOUNDS.getWidth() - 1 - bounds.getWidth();
            else newMinX = bounds.getMinX();

            if (bounds.getMinY() < ORIGINAL_GAME_BOUNDS.getMinY()) newMinY = ORIGINAL_GAME_BOUNDS.getMinY();
            else if (bounds.getMinY() + bounds.getHeight() >= ORIGINAL_GAME_BOUNDS.getMinY() + ORIGINAL_GAME_BOUNDS.getHeight())
                newMinY = ORIGINAL_GAME_BOUNDS.getMinY() + ORIGINAL_GAME_BOUNDS.getHeight() - 1 - bounds.getHeight();
            else newMinY = bounds.getMinY();

            return new BoundingBox(newMinX, newMinY, bounds.getWidth(), bounds.getHeight());
        }
    }

    /**
     * Returns the Moved Bounding Box for the given {@link Bounds}, {@link Direction}, velocity and time.
     *
     * @param bounds      The bounds to be moved.
     * @param velocity    The velocity of the movement.
     * @param direction   The direction of the movement.
     * @param elapsedTime The time elapsed since the last movement.
     * @return the moved bounds
     */
    public static Bounds getNextPosition(final Bounds bounds, final double velocity, final Direction direction, final double elapsedTime) {
        double newMinX = bounds.getMinX() + velocity * direction.getX() * elapsedTime;
        double newMinY = bounds.getMinY() + velocity * direction.getY() * elapsedTime;
        return new BoundingBox(newMinX, newMinY, bounds.getWidth(), bounds.getHeight());
    }
}
