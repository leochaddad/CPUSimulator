package br.maua;

import br.maua.ui.panes.creator.CreatorController;
import br.maua.ui.panes.creator.CreatorView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    public static void main(String[] args) throws Exception {
//        System.out.println();
//
//        Register registerA = new Register("RegA","000100101111");
//        Register registerB = new Register("RegB","000000000001");
//        Register registerC = new Register("RegC","000000000000");
//        Counter programCounter = new Counter("PC","000000000000");
//        Bus bus = new Bus("Bus");
//        Alu alu = new Alu("Alu", registerA,registerB);
//        Memory memory = new Memory("Mem", 16, registerA,programCounter);
//        ArrayList<Controllable> controllables = new ArrayList<>();
//        controllables.add(registerA);
//        controllables.add(registerB);
//        controllables.add(registerC);
//        controllables.add(programCounter);
//        controllables.add(alu);
//        controllables.add(memory);
//
//        ControlMultiplexer cl = new ControlMultiplexer(controllables);

//        alu.connectTo(registerC);
//        bus.connectTo(registerA);
//        bus.connectTo(registerB);
//        bus.connectTo(registerC);
//        bus.connectTo(programCounter);
//        registerA.connectToBuffered(bus);
//        registerB.connectToBuffered(bus);
//        registerC.connectToBuffered(bus);
//        programCounter.connectToBuffered(bus);
//        memory.connectTo(bus);


//        System.out.println(cl.getControlSequence());

//        cl.Control("0100000000101001");

        //registerA.Control(false,true,false);

        //memory.Control(false,true);

        //programCounter.Control(false,true, false, true);

        //memory.Control(true,false);

//        DataSplitter splitter1 = new DataSplitter("Splitter", bus, true,true);
//
//        registerA.connectTo(splitter1);
//        registerA.outputEnable(true);
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

        CreatorView cv = new CreatorView();
        CreatorController controller = new CreatorController(cv);
        cv.initialize();
        //controller.addElement(new RegisterComponent());
//        controller.addElement(new BusComponent());
//        controller.addElement(new AluComponent());
        Scene scene = new Scene(cv);
        stage.setMinWidth(1000);
        stage.setMinHeight(700);
        stage.setScene(scene);
        stage.show();

    }
}