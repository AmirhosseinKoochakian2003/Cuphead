module view {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    opens view to javafx.fxml;
    exports view;
    exports view.animations;
    opens view.animations to javafx.fxml;
    exports view.components;
    opens view.components to javafx.fxml;
}
