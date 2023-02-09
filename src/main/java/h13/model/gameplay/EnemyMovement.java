package h13.model.gameplay;

import h13.model.gameplay.sprites.Enemy;
import h13.shared.Utils;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;

import java.util.Iterator;

import static h13.controller.GameConstants.*;

/**
 * The {@link EnemyMovement} class is responsible for moving the {@linkplain h13.model.gameplay.sprites.Enemy enemies} in a grid.
 */
public class EnemyMovement implements Updatable {

    // --Variables-- //

    /**
     * The current movement direction
     */
    private Direction direction;

    /**
     * The current movement speed
     */
    private double velocity = INITIAL_ENEMY_MOVEMENT_VELOCITY;

    /**
     * The Next y-coordinate to reach
     */
    private double yTarget = 0;

    /**
     * The current {@link GameState}
     */
    private final GameState gameState;

    // --Constructors-- //

    /**
     * Creates a new EnemyMovement.
     *
     * @param gameState The enemy controller.
     */
    public EnemyMovement(final GameState gameState) {
        this.gameState = gameState;
        nextRound();
    }

    // --Getters and Setters-- //

    /**
     * Gets the current {@link #velocity}.
     *
     * @return The current {@link #velocity}.
     * @see #velocity
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * Sets the current {@link #velocity} to the given value.
     *
     * @param velocity The new {@link #velocity}.
     * @see #velocity
     */
    public void setVelocity(final double velocity) {
        this.velocity = velocity;
    }

    /**
     * Gets the current {@link #direction}.
     *
     * @return The current {@link #direction}.
     * @see #direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Sets the current {@link #direction} to the given value.
     *
     * @param direction The new {@link #direction}.
     * @see #direction
     */
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }
    public double getYTarget() {
        return yTarget;
    }
    public void setYTarget(final double yTarget) {
        this.yTarget += yTarget;
    }

    /**
     * Checks whether the bottom was reached.
     *
     * @return {@code true} if the bottom was reached, {@code false} otherwise.
     */
    public boolean bottomWasReached() {
        return getEnemyBounds().getMaxY() >= ORIGINAL_GAME_BOUNDS.getMaxY();
    }

    /**
     * Gets the enemy controller.
     *
     * @return The enemy controller.
     */
    public GameState getGameState() {
        return gameState;
    }

    // --Utility Methods-- //

    /**
     * Creates a BoundingBox around all alive enemies.
     *
     * @return The BoundingBox.
     */
    public Bounds getEnemyBounds() {
        Iterator<Enemy> enemyIterator = getGameState().getAliveEnemies().iterator();
        double min_x = ORIGINAL_GAME_BOUNDS.getWidth(), min_y = ORIGINAL_GAME_BOUNDS.getHeight(),
            max_x = ORIGINAL_GAME_BOUNDS.getMinX(), max_y = ORIGINAL_GAME_BOUNDS.getMinY();
        do {
            Enemy enemy = enemyIterator.next();
            min_x = Math.min(min_x, enemy.getX());
            min_y = Math.min(min_y, enemy.getY());
            max_x = Math.max(max_x, enemy.getX() + enemy.getWidth());
            max_y = Math.max(max_y, enemy.getY() + enemy.getHeight());
        } while (enemyIterator.hasNext());
        return new BoundingBox(min_x, min_y, max_x - min_x, max_y - min_y);
    }

    /**
     * Checks whether the target Position of the current movement iteration is reached.
     *
     * @param enemyBounds The BoundingBox of all alive enemies.
     * @return {@code true} if the target Position of the current movement iteration is reached, {@code false} otherwise.
     */
    public boolean targetReached(final Bounds enemyBounds) {
        return enemyBounds.getMinY() == getYTarget() &&
            (enemyBounds.getMinX() == ORIGINAL_GAME_BOUNDS.getMinX() && (getDirection() == Direction.LEFT || getDirection() == Direction.DOWN)
            || enemyBounds.getMaxX() == ORIGINAL_GAME_BOUNDS.getMaxX() && (getDirection() == Direction.DOWN || getDirection() == Direction.RIGHT));
    }

    // --Movement-- //

    @Override
    public void update(final double elapsedTime) {
        Bounds bounding = getEnemyBounds();
        if (!bottomWasReached()) {
            if (targetReached(bounding)) nextMovement(bounding);
            else {
                Bounds nextPos = Utils.getNextPosition(bounding, getVelocity(), getDirection(), elapsedTime);
                Bounds nextPosClamp = Utils.clamp(nextPos);
                updatePositions(nextPosClamp.getMinX() - bounding.getMinX(), nextPosClamp.getMinY() - bounding.getMinY());
                targetReached(nextPosClamp);
            }
        }
    }

    /**
     * Updates the positions of all alive enemies.
     *
     * @param deltaX The deltaX.
     * @param deltaY The deltaY.
     */
    public void updatePositions(final double deltaX, final double deltaY) {
        getGameState().getAliveEnemies().forEach(e -> {
            e.setX(e.getX() + deltaX);
            e.setY(e.getY() + deltaY);
        });
    }

    /**
     * Starts the next movement iteration.
     *
     * @param enemyBounds The BoundingBox of all alive enemies.
     */
    public void nextMovement(final Bounds enemyBounds) {
        setVelocity(getVelocity() + ENEMY_MOVEMENT_SPEED_INCREASE);
        if (enemyBounds.getMinX() == ORIGINAL_GAME_BOUNDS.getMinX()) {
            if (getDirection() == Direction.LEFT) {
                setDirection(Direction.DOWN);
                setYTarget(VERTICAL_ENEMY_MOVE_DISTANCE);
            } else if (getDirection() == Direction.DOWN) {
                setDirection(Direction.RIGHT);
            }
        } else if (enemyBounds.getMaxX() == ORIGINAL_GAME_BOUNDS.getMaxX()) {
            if (getDirection() == Direction.RIGHT) {
                setDirection(Direction.DOWN);
                setYTarget(VERTICAL_ENEMY_MOVE_DISTANCE);
            } else if (getDirection() == Direction.DOWN) {
                setDirection(Direction.LEFT);
            }
        }
    }

    /**
     * Prepares the next round of enemies.
     * Uses {@link h13.controller.GameConstants#INITIAL_ENEMY_MOVEMENT_DIRECTION} and {@link h13.controller.GameConstants#INITIAL_ENEMY_MOVEMENT_VELOCITY} to set the initial values.
     */
    public void nextRound() {
        direction = INITIAL_ENEMY_MOVEMENT_DIRECTION;
        yTarget = HUD_HEIGHT;
    }
}
