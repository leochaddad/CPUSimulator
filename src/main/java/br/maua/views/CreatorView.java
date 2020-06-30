package br.maua.views;

import br.maua.views.uiobjects.Draggable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;

public class CreatorView extends AnchorPane {


    public VBox getElementsArea() {
        return elementsArea;
    }

    public AnchorPane getCreationArea() {
        return creationArea;
    }


    public void setDragOverElement(Draggable dragOverElement) {
        this.dragOverElement = dragOverElement;
    }

    public Draggable getDragOverElement() {
        return dragOverElement;
    }

    private Draggable dragOverElement = new Draggable();
    private AnchorPane creationArea = new AnchorPane();
    private VBox elementsArea = new VBox();
    private ScrollPane scrollPane = new ScrollPane();
    private SplitPane splitPane = new SplitPane();


    public void initialize() {
        creationArea.setPadding(new Insets(10, 10, 10, 10));
        scrollPane.setContent(elementsArea);
        splitPane.getItems().addAll(scrollPane,creationArea);
        this.getChildren().add(splitPane);
        elementsArea.setSpacing(10);
        elementsArea.setAlignment(Pos.TOP_CENTER);
        elementsArea.setPadding(Insets.EMPTY);
        elementsArea.paddingProperty().setValue(new Insets(10));
        elementsArea.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.25));
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        splitPane.prefWidthProperty().bind(this.widthProperty());
        splitPane.prefHeightProperty().bind(this.heightProperty());
        this.setPrefSize(USE_COMPUTED_SIZE,USE_COMPUTED_SIZE);
    }


}
