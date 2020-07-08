package br.maua.ui.panes.creator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;

public class CreatorView extends AnchorPane {


    public VBox getAvailableComponentsBox() {
        return availableComponentsBox;
    }

    public static AnchorPane getCreationAreaAnchorPane() {
        return creationAreaAnchorPane;
    }

    public ScrollPane getCreationAreaScrollPane() {
        return creationAreaScrollPane;
    }


    private final ScrollPane creationAreaScrollPane = new ScrollPane();
    private static final AnchorPane creationAreaAnchorPane = new AnchorPane();
    private final VBox availableComponentsBox = new VBox();
    private final ScrollPane availableComponentsScrollPane = new ScrollPane();
    private final HBox hBox = new HBox();


    public void initialize() {
        creationAreaScrollPane.setContent(creationAreaAnchorPane);

        availableComponentsScrollPane.setContent(availableComponentsBox);
        hBox.getChildren().addAll(availableComponentsScrollPane,creationAreaScrollPane);
        this.getChildren().add(hBox);
        availableComponentsBox.setSpacing(10);
        availableComponentsBox.setAlignment(Pos.TOP_CENTER);
        availableComponentsBox.setPadding(Insets.EMPTY);
        availableComponentsBox.paddingProperty().setValue(new Insets(10));

        availableComponentsScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        availableComponentsBox.getTransforms().add(new Scale(0.6,0.6));

        hBox.prefWidthProperty().bind(this.widthProperty());
        hBox.prefHeightProperty().bind(this.heightProperty());

        creationAreaAnchorPane.prefHeightProperty().bind(creationAreaScrollPane.heightProperty());
        creationAreaAnchorPane.prefWidthProperty().bind(creationAreaScrollPane.widthProperty());
        creationAreaScrollPane.prefWidthProperty().bind(hBox.widthProperty().multiply(0.9));
        creationAreaScrollPane.prefHeightProperty().bind(hBox.heightProperty());
        this.setPrefSize(USE_COMPUTED_SIZE,USE_COMPUTED_SIZE);
    }


}
