package br.maua;

import br.maua.models.Alu;
import br.maua.models.Buffer;
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

        Register registerA = new Register("000000101111");
        Register registerB = new Register("000000111000");
        Register registerC = new Register("000000000000");
        Bus bus = new Bus();
        Alu alu = new Alu(registerA,registerB);
        Buffer bufferA = new Buffer();
        Buffer bufferB = new Buffer();
        Buffer bufferC = new Buffer();
        registerC.connectTo(alu);
        registerA.connectTo(bufferA);
        registerB.connectTo(bufferB);
        registerC.connectTo(bufferC);
        bufferA.connectTo(bus);
        bufferB.connectTo(bus);
        bufferC.connectTo(bus);

        bufferA.setEnabled(false);
        bufferB.setEnabled(false);
        bufferC.setEnabled(false);

        registerA.outputEnable(true);
        registerB.outputEnable(true);
        registerC.latchInput();

        System.out.println("C int exp 103 " +registerC.getInternalData());
        System.out.println("B int 56 "  +registerB.getInternalData());
        System.out.println("A int 47 "  +registerA.getInternalData());
        System.out.println("BUS: 0 "+ bus.getExternalData());
        System.out.println("ALU 103 "+ alu.getExternalData());

        registerB.outputEnable(false);
        registerA.outputEnable(false);
        bufferB.setEnabled(true);
        bufferC.setEnabled(true);
        registerC.outputEnable(true);
        registerA.outputEnable(true);

        registerB.latchInput();


        System.out.println("C int exp 103 " +registerC.getInternalData());
        System.out.println("B int 103 "  +registerB.getInternalData());
        System.out.println("A int 47 "  +registerA.getInternalData());
        System.out.println("BUS: 103 "+ bus.getExternalData());
        System.out.println("ALU 150 "+ alu.getExternalData());

        bufferC.setEnabled(false);
        registerB.outputEnable(true);
        registerA.outputEnable(true);
        registerC.latchInput();


        System.out.println("C int " +registerC.getInternalData());
        System.out.println("B int"  +registerB.getInternalData());
        System.out.println("A int"  +registerA.getInternalData());
        System.out.println("BUS: "+ bus.getExternalData());
        System.out.println("ALU "+ alu.getExternalData());



        //launch();
    }

}