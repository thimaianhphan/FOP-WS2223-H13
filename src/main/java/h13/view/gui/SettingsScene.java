package h13.view.gui;

import h13.controller.ApplicationSettings;
import h13.controller.scene.menu.SettingsController;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

/**
 * The {@link SettingsScene} is a {@link SubMenuScene} that displays the "Settings" menu.
 */
public class SettingsScene extends SubMenuScene<SettingsController, TabPane> {

    /**
     * Creates a new {@link SettingsScene}.
     */
    public SettingsScene() {
        super(new TabPane(), new SettingsController(), "Settings");
        init();
    }

    /**
     * Initialize the content of the scene.
     */
    private void init() {
        Tab settingsTab = new Tab("Settings");
        settingsTab.setClosable(false);

        //gridPane
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        gridPane.prefHeightProperty().bind(getContentRoot().heightProperty());
        gridPane.prefWidthProperty().bind(getContentRoot().widthProperty());

        //checkbox for instant shooting of player
        getController().instantShootingCheckBox = new CheckBox("Player's instant shooting");
        gridPane.add(getController().instantShootingCheckBox, 0, 0);
        getController().instantShootingCheckBox.selectedProperty().bindBidirectional(ApplicationSettings.instantShootingProperty());


        //slider for minimal shooting interval of enemy
        Label enemyShootingDelayLabel = new Label("Enemy's minimal shooting interval");
        gridPane.add(enemyShootingDelayLabel, 0, 1);
        getController().enemyShootingDelay = new Slider();
        getController().enemyShootingDelay.setMin(0);
        getController().enemyShootingDelay.setMax(1000000);
        getController().enemyShootingDelay.setValue(ApplicationSettings.enemyShootingDelayProperty().getValue());
        gridPane.add(getController().enemyShootingDelay, 1, 1);
        getController().enemyShootingDelay.valueProperty().bindBidirectional(ApplicationSettings.enemyShootingDelayProperty());

        //slider for shooting probability of enemy
        Label enemyShootingProbabilityLabel = new Label("Enemy's shooting probability");
        gridPane.add(enemyShootingProbabilityLabel, 0, 2);
        getController().enemyShootingProbability = new Slider();
        getController().enemyShootingProbability.setMin(0);
        getController().enemyShootingProbability.setMax(1);
        getController().enemyShootingProbability.setValue(ApplicationSettings.enemyShootingProbability.getValue());
        gridPane.add(getController().enemyShootingProbability, 1, 2);
        getController().enemyShootingProbability.valueProperty().bindBidirectional(ApplicationSettings.enemyShootingProbabilityProperty());

        //checkbox for full screen mode
        getController().fullscreenCheckBox = new CheckBox("Start game at full screen mode");
        gridPane.add(getController().fullscreenCheckBox, 0, 3);
        getController().fullscreenCheckBox.selectedProperty().bindBidirectional(ApplicationSettings.fullscreenProperty());

        //checkbox for textures loading
        getController().loadTexturesCheckBox = new CheckBox("Load texture of sprites");
        gridPane.add(getController().loadTexturesCheckBox, 0, 4);
        getController().loadTexturesCheckBox.selectedProperty().bindBidirectional(ApplicationSettings.loadTexturesProperty());

        //checkbox for background loading
        getController().loadBackgroundCheckBox = new CheckBox("Load background");
        gridPane.add(getController().loadBackgroundCheckBox, 0, 5);
        getController().loadBackgroundCheckBox.selectedProperty().bindBidirectional(ApplicationSettings.loadBackgroundProperty());
    }
}
