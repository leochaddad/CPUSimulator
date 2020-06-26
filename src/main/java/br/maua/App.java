package br.maua;

import br.maua.models.*;
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
        Register registerB = new Register("000000000001");
        Register registerC = new Register("000000000000");
        Counter  programCounter = new Counter("000000000000");
        Bus bus = new Bus();
        Alu alu = new Alu(registerA,registerB);

        alu.connectTo(registerC);
        bus.connectTo(registerA);
        bus.connectTo(registerB);
        bus.connectTo(registerC);
        bus.connectTo(programCounter);
        registerA.connectToBuffered(bus);
        registerB.connectToBuffered(bus);
        registerC.connectToBuffered(bus);
        programCounter.connectToBuffered(bus);

        Memory memory = new Memory(16, registerA,programCounter);
        memory.connectTo(bus);
        registerA.Control(false,true,false);

        memory.Control(false,true);

        programCounter.Control(false,true, false, true);

        memory.Control(true,false);


        memory.Show();
        System.out.println("PC " +programCounter.getOutcomingData());
        System.out.println("Memory " +memory.getOutcomingData());
        System.out.println("C int exp 103 " +registerC.getInternalData());
        System.out.println("B int 56 "  +registerB.getInternalData());
        System.out.println("A int 47 "  +registerA.getOutcomingData());
        System.out.println("BUS: 0 "+ bus.getOutcomingData());
        System.out.println("ALU 103 "+ alu.getOutcomingData());


        //launch();
    }

}