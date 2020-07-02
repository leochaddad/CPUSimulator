package br.maua.ui.views;

import br.maua.ui.views.draggables.Draggable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.transform.Scale;

public class CreatorView extends AnchorPane {


    public VBox getElementsArea() {
        return elementsArea;
    }

    public AnchorPane getCreationArea() {
        return creationArea;
    }

    public ScrollPane getCreationAreaScrollPane() {
        return creationAreaScrollPane;
    }

    public void setDraggedElement(Draggable draggedElement) {
        this.draggedElement = draggedElement;
    }

    public Draggable getDraggedElement() {
        return draggedElement;
    }

    private Draggable draggedElement = new Draggable();


    private ScrollPane creationAreaScrollPane = new ScrollPane();
    private AnchorPane creationArea = new AnchorPane();
    private VBox elementsArea = new VBox();
    private ScrollPane elementsScrollPane = new ScrollPane();
    private HBox hBox = new HBox();


    public void initialize() {
        creationAreaScrollPane.setContent(creationArea);

        elementsScrollPane.setContent(elementsArea);
        hBox.getChildren().addAll(elementsScrollPane,creationAreaScrollPane);
        this.getChildren().add(hBox);
        elementsArea.setSpacing(10);
        elementsArea.setAlignment(Pos.TOP_CENTER);
        elementsArea.setPadding(Insets.EMPTY);
        elementsArea.paddingProperty().setValue(new Insets(10));

        elementsScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        elementsArea.getTransforms().add(new Scale(0.6,0.6));

        hBox.prefWidthProperty().bind(this.widthProperty());
        hBox.prefHeightProperty().bind(this.heightProperty());

        creationArea.prefHeightProperty().bind(creationAreaScrollPane.heightProperty());
        creationArea.prefWidthProperty().bind(creationAreaScrollPane.widthProperty());
        creationAreaScrollPane.prefWidthProperty().bind(hBox.widthProperty().multiply(0.9));
        creationAreaScrollPane.prefHeightProperty().bind(hBox.heightProperty());
        this.setPrefSize(USE_COMPUTED_SIZE,USE_COMPUTED_SIZE);


    }


}
