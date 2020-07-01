module br.maua {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires de.tesis.dynaware.grapheditor.api;


    opens br.maua to javafx.fxml;
    exports br.maua;
}