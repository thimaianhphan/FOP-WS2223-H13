package h13.model.gameplay.sprites;

import h13.controller.GameConstants;
import h13.model.gameplay.Direction;
import h13.model.gameplay.GameState;
import h13.shared.Utils;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

import static h13.controller.GameConstants.*;

/**
 * A {@link Bullet} is a {@link Sprite} that can be fired by a {@link BattleShip} and can can hit other BattleShips.
 *
 * <ul>
 *   <li>A Bullet cannot damage itself, other Bullets, its owner or Friends of its owner.</li>
 *   <li>A Bullet can only damage the same BattleShip only once.</li>
 * </ul>
 */
public class Bullet extends Sprite {
    // --Variables-- //

    /**
     * The owner of the Bullet.
     */
    private final BattleShip owner;

    /**
     * The set of BattleShips that have been damaged by the Bullet.
     */
    private final Set<Sprite> hits = new HashSet<>();
//    private boolean hasHit = true;

    // --Constructors-- //

    /**
     * Creates a new {@link Bullet}.
     *
     * @param x         The initial x-coordinate of the Bullet.
     * @param y         The initial y-coordinate of the Bullet.
     * @param gameState The game state.
     * @param owner     The owner of the Bullet.
     * @param direction The direction the Bullet is travelling towards.
     */
    public Bullet(final double x, final double y, final GameState gameState, final BattleShip owner, final Direction direction) {
        super(x, y, BULLET_WIDTH, BULLET_HEIGHT, Color.WHITE, BULLET_VELOCITY, 1, gameState);
        this.owner = owner;
        setDirection(direction);
    }

    // --Getters and Setters-- //

    /**
     * Gets the owner of the Bullet.
     *
     * @return The owner of the Bullet.
     * @see #owner
     */
    public BattleShip getOwner() {
        return owner;
    }

    /**
     * Gets the set of BattleShips that have been damaged by the Bullet.
     *
     * @return The set of BattleShips that have been damaged by the Bullet.
     * @see #hits
     */
    public Set<Sprite> getHits() {
        return hits;
    }

    /**
     * Checks if the Bullet can damage the given Sprite.
     *
     * @param other The Sprite to check.
     * @return True if the Bullet can damage the given Sprite.
     */
    public boolean canHit(final BattleShip other) {
        if (getOwner().isEnemy(other) && other.isAlive() && !getHits().contains(other)) {
            return getBounds().intersects(other.getX(), other.getY(), other.getWidth(), other.getHeight());
        } else return false;
    }

    /**
     * Hits the given BattleShip by damaging both the Bullet and the BattleShip. Also saves the BattleShip in the set of hits.
     *
     * @param other The BattleShip to hit.
     */
    public void hit(final BattleShip other) {
        if (getHealth() > 0 && isAlive()) {
            other.damage();
            damage();
            getHits().add(other);
        }
    }

    @Override
    public void update(final double elapsedTime) {
        Bounds newBounds = Utils.getNextPosition(getBounds(), getVelocity(), getDirection(), elapsedTime);
        double new_x = newBounds.getMinX(), new_y = newBounds.getMinY();
        if (new_x >= ORIGINAL_GAME_BOUNDS.getWidth() || new_x < ORIGINAL_GAME_BOUNDS.getMinX()
            || new_y < ORIGINAL_GAME_BOUNDS.getMinY() || new_y >= ORIGINAL_GAME_BOUNDS.getHeight()) {
            die();
            return;
        }
        setX(new_x);
        setY(new_y);
    }
}
