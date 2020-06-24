module br.maua {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens br.maua to javafx.fxml;
    exports br.maua;
}