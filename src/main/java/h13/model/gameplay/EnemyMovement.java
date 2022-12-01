package h13.model.gameplay;

import javafx.geometry.Bounds;

import static h13.controller.GameConstants.*;
import static org.tudalgo.algoutils.student.Student.crash;

/**
 * The EnemyMovement class is responsible for moving the enemies in a grid.
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

    /**
     * Checks whether the bottom was reached.
     *
     * @return {@code true} if the bottom was reached, {@code false} otherwise.
     */
    public boolean bottomWasReached() {
        return crash(); // TODO: H1.6 - remove if implemented
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
        return crash(); // TODO: H1.6 - remove if implemented
    }

    /**
     * Checks whether the target Position of the current movement iteration is reached.
     *
     * @param enemyBounds The BoundingBox of all alive enemies.
     * @return {@code true} if the target Position of the current movement iteration is reached, {@code false} otherwise.
     */
    private boolean targetReached(final Bounds enemyBounds) {
        return crash(); // TODO: H1.6 - remove if implemented
    }

    // --Movement-- //

    @Override
    public void update(final double elapsedTime) {
        crash(); // TODO: H1.6 - remove if implemented
    }

    /**
     * Updates the positions of all alive enemies.
     *
     * @param deltaX The deltaX.
     * @param deltaY The deltaY.
     */
    private void updatePositions(final double deltaX, final double deltaY) {
        crash(); // TODO: H1.6 - remove if implemented
    }

    /**
     * Starts the next movement iteration.
     *
     * @param enemyBounds The BoundingBox of all alive enemies.
     */
    private void nextMovement(final Bounds enemyBounds) {
        crash(); // TODO: H1.6 - remove if implemented
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
