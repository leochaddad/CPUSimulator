package br.maua.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Register extends Connectable  {

        //Constructor for testing purposes only
        public Register(String internalData) {
            this.internalData = new Data(internalData);
        }

        Connectable bufferedConnection;
        protected boolean bufferEnabled = false;
        boolean outputEnabled = false;

    /**
     * @param BE BufferEnable
     * @param OE OutputEnable
     * @param IN Clock (Latch Input)
     */
        public void Control(boolean BE, boolean OE, boolean IN ) throws Exception {
            if(OE^outputEnabled){
                outputEnable(OE);
            }
            if(BE^bufferEnabled){
                bufferEnable(BE);
            }
            if(IN){
                latchInput();
            }
        }

    /**
     * Sets the buffered connection
     * @param connectable bufferedConnection
     */
        public void connectToBuffered(Connectable connectable){
            bufferedConnection = connectable;
        }

    /**
     * Adds or removes bufferedConnection from outputs
     * @param enabled
     */
        public void bufferEnable(boolean enabled) throws Exception {
            if (enabled & !bufferEnabled){
                bufferEnabled = true;
                this.connectTo(bufferedConnection);
            }else if (!enabled & bufferEnabled){
                bufferEnabled = false;
                this.disconnectFrom(bufferedConnection);
                bufferedConnection.Update();
            }
        }


    public void setInternalData(Data internalData) {
        this.internalData = internalData;
        if(outputEnabled){
            setOutDataAndNotify(this.getInternalData());
        }
    }

    /**
         * Getter for register's internal data
         * @return internalData
         */
        public Data getInternalData() {
            return internalData;
        }

        /**
         * Register's internal data
         */
        private Data internalData = new Data("000000011000");

        /**
         * Latches on external data
         */
            public void latchInput() throws Exception {
                if(bufferEnabled){
                    throw new Exception("BUFFER MUST BE DISABLED TO LATCH VALUE INTO REGISTER");
                }
                else{
                    this.internalData.clear();
                    for(Connectable input:inputs) {
                        this.internalData = (this.internalData.add(input.getOutcomingData()));
                    }
                    this.setInternalData(internalData);
                    this.Update();
                }
            }

        /**
         * Enables or disables output
         * @param enable
         */
            public void outputEnable(boolean enable){
                this.outputEnabled = enable;
                if (enable){
                    setOutDataAndNotify(this.getInternalData());
                }
                else{
                    setOutDataAndNotify(new Data().clear());
                }
            }

    public void Update() {
            RegDisplayText.setValue(this.getInternalData().toString());
    }

    StringProperty RegDisplayText = new SimpleStringProperty(this.getInternalData().toString());
    public Group UIObject(int x, int y) {
        Rectangle rect = new Rectangle(x,y,100,50);
        rect.setFill(Color.rgb(255,150,100));
        Text text = new Text();
        text.textProperty().bind(this.RegDisplayText);
        text.setX(x+30);
        text.setY(y+25);
        Circle oe = new Circle(x+10,y+60,10);
        Circle be = new Circle(x+45,y+60,10);
        Circle in = new Circle(x+80,y+60,10);


        EventHandler<MouseEvent> oeClicked  = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                outputEnable(!outputEnabled);
                if (outputEnabled) {
                    oe.setFill(Color.rgb(0, 255, 200));
                } else {
                    oe.setFill(Color.rgb(255, 100, 100));
                }
            }};

        EventHandler<MouseEvent> inClicked  = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    latchInput();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }};
        EventHandler<MouseEvent> beClicked  = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                try {
                    bufferEnable(!bufferEnabled);
                    if (bufferEnabled) {
                        be.setFill(Color.rgb(0, 255, 200));
                    } else {
                        be.setFill(Color.rgb(255, 100, 100));
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            };
        oe.addEventFilter(MouseEvent.MOUSE_CLICKED, oeClicked);
        be.addEventFilter(MouseEvent.MOUSE_CLICKED, beClicked);
        in.addEventFilter(MouseEvent.MOUSE_CLICKED, inClicked);

        return new Group(rect,text, oe,be,in);
    }
}
