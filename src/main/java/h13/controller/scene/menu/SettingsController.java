package h13.controller.scene.menu;

import h13.controller.ApplicationSettings;
import h13.controller.scene.SceneController;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

import static org.tudalgo.algoutils.student.Student.crash;


/**
 * A {@link SceneController} that manages the "Settings" scene.
 */
public class SettingsController extends SceneController {

    // --Variables-- //

    /**
     * The checkbox for the "load textures" setting.
     */
    public CheckBox loadTexturesCheckBox;

    /**
     * The checkbox for the "load background" setting.
     */
    public CheckBox loadBackgroundCheckBox;

    /**
     * The checkbox for the "instant shooting" setting.
     */
    public CheckBox instantShootingCheckBox;

    /**
     * The checkbox for the "enable Horizontal enemy Movement" setting.
     */
    public CheckBox enemyHorizontalMovementCheckBox;

    /**
     * The checkbox for the "enable Vertical enemy Movement" setting.
     */
    public CheckBox enemyVerticalMovementCheckBox;

    /**
     * The checkbox for the "full screen" setting.
     */
    public CheckBox fullscreenCheckBox;

    /**
     * The checkbox for the "enable sound" setting.
     */
    public CheckBox enableSoundCheckBox;

    /**
     * The checkbox for the "enable music" setting.
     */
    public CheckBox enableMusicCheckBox;

    /**
     * The slider for the "music volume" setting.
     */
    public Slider musicVolumeSlider;

    /**
     * The slider for the "gameplay volume" setting.
     */
    public Slider gameplayVolumeSlider;

    /**
     * The slider for the "enemy shooting delay" setting
     */
    public Slider enemyShootingDelay;

    /**
     * The slider for the "enemy shooting probability" setting
     */
    public Slider enemyShootingProbability;

    /**
     * The checkbox for enabling or disabling autoplay.
     */
    public CheckBox autoPlayCheckBox;


    @Override
    public String getTitle() {
        return "Space Invaders - Settings";
    }

    @Override
    public void initStage(final Stage stage) {
        super.initStage(stage);

        fullscreenCheckBox.selectedProperty().bindBidirectional(ApplicationSettings.fullscreenProperty());
        enemyShootingDelay.valueProperty().bindBidirectional(ApplicationSettings.enemyShootingDelayProperty());
        enemyShootingProbability.valueProperty().bindBidirectional(ApplicationSettings.enemyShootingProbabilityProperty());
        loadTexturesCheckBox.selectedProperty().bindBidirectional(ApplicationSettings.loadTexturesProperty());
        loadBackgroundCheckBox.selectedProperty().bindBidirectional(ApplicationSettings.loadBackgroundProperty());
        instantShootingCheckBox.selectedProperty().bindBidirectional(ApplicationSettings.instantShootingProperty());
        autoPlayCheckBox.selectedProperty().bindBidirectional(ApplicationSettings.autoPlayProperty());
    }
}
