package br.maua;

import br.maua.models.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Scanner;


/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) throws Exception {

//        Register registerA = new Register("000000101111");
//        Register registerB = new Register("000000000001");
//        Register registerC = new Register("000000000000");
//        Counter  programCounter = new Counter("000000000000");
//        Bus bus = new Bus();
//        Alu alu = new Alu(registerA,registerB);
//
//        alu.connectTo(registerC);
//        bus.connectTo(registerA);
//        bus.connectTo(registerB);
//        bus.connectTo(registerC);
//        bus.connectTo(programCounter);
//        registerA.connectToBuffered(bus);
//        registerB.connectToBuffered(bus);
//        registerC.connectToBuffered(bus);
//        programCounter.connectToBuffered(bus);
//
//        Memory memory = new Memory(16, registerA,programCounter);
//        memory.connectTo(bus);
//        registerA.Control(false,true,false);
//
//        memory.Control(false,true);
//
//        programCounter.Control(false,true, false, true);
//
//        memory.Control(true,false);
//
//
//        memory.Show();
//        System.out.println("PC " +programCounter.getOutcomingData());
//        System.out.println("Memory " +memory.getOutcomingData());
//        System.out.println("C int exp 103 " +registerC.getInternalData());
//        System.out.println("B int 56 "  +registerB.getInternalData());
//        System.out.println("A int 47 "  +registerA.getOutcomingData());
//        System.out.println("BUS: 0 "+ bus.getOutcomingData());
//        System.out.println("ALU 103 "+ alu.getOutcomingData());


        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setResizable(false);
        SequenceComputer computer = new SequenceComputer();
        computer.setup();
        Group root = new Group(computer.UIObject());
        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.show();



    }
}