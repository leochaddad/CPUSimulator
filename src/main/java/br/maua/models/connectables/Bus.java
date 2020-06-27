package br.maua.models.connectables;

import br.maua.models.Connectable;
import br.maua.models.Data;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Bus extends Connectable {

    public Bus(String name) {
        super(name);
    }

    public void Update() {
        this.setOutDataAndNotify(new Data().clear());

        for(Connectable input:inputs){
            this.setOutDataAndNotify(this.getOutcomingData().add(input.getOutcomingData()));
        }
        BusDisplayText.setValue(this.getOutcomingData().toString());
    }

    StringProperty BusDisplayText = new SimpleStringProperty(this.getOutcomingData().toString());

    public Group UIObject(int x, int y) {
        Rectangle rect = new Rectangle(x,y,800,40);
        rect.setFill(Color.rgb(100,200,255));
        Text text = new Text();
        text.textProperty().bind(this.BusDisplayText);
        text.setX(x+30);
        text.setY(y+25);
        return new Group(rect,text);
    }

}
