package br.maua;

import br.maua.models.Alu;
import br.maua.models.Bus;
import br.maua.models.Register;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("mainscreen"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        Register registerA = new Register("000000000010");
        Register registerB = new Register("000000001000");
        Bus bus = new Bus();
        Alu alu = new Alu(registerA,registerB);

        System.out.println(registerA.getInternalData());
        System.out.println(registerB.getInternalData());

        System.out.println(registerA.getExternalData());
        System.out.println(registerB.getExternalData());
        System.out.println(bus.getExternalData());

        System.out.println(registerA.getExternalData());
        System.out.println(registerB.getExternalData());
        System.out.println(" "+bus.getExternalData());

        System.out.println(registerA.getInternalData());
        System.out.println(registerB.getInternalData());

        //launch();
    }

}