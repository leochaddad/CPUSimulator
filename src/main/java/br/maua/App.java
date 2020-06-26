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

    public static void main(String[] args) throws Exception {

        Register registerA = new Register("000000101111");
        Register registerB = new Register("000000111000");
        Register registerC = new Register("000000000000");
        Bus bus = new Bus();
        Alu alu = new Alu(registerA,registerB);

        alu.connectTo(registerC);
        bus.connectTo(registerA);
        bus.connectTo(registerB);
        bus.connectTo(registerC);
        registerA.connectToBuffered(bus);
        registerB.connectToBuffered(bus);
        registerC.connectToBuffered(bus);

        registerA.outputEnable(true);
        registerB.outputEnable(true);

        registerC.latchInput();

        registerC.bufferEnable(true);
        registerC.outputEnable(true);

        registerB.outputEnable(false);
        registerB.latchInput();

        registerB.outputEnable(true);
        registerC.outputEnable(false);
        registerC.latchInput();



        System.out.println("B int 56 "  +registerB.getInternalData());
        System.out.println("C int exp 103 " +registerC.getInternalData());
        System.out.println("B int 56 "  +registerB.getInternalData());
        System.out.println("A int 47 "  +registerA.getInternalData());
        System.out.println("BUS: 0 "+ bus.getOutcomingData());
        System.out.println("ALU 103 "+ alu.getOutcomingData());


        //launch();
    }

}