package br.maua.views.uiobjects;

import br.maua.interfaces.Drawer;
import br.maua.models.connectables.Register;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class RegisterDrawer implements Drawer {

    public RegisterDrawer(Register register, double x, double y) {
        this.register = register;
        this.x = x;
        this.y = y;
    }

    Register register;

    double x;
    double y;

    static double w=130;
    static double h = 60;

    double topPortOneX = x + w/3;
    double topPortTwoX = x + 2*w/3;
    double bottomPortTwoX =x + 2*w/3;
    double bottomPortOneX = x + w/3;
    double topPortOneY =this.y ;
    double topPortTwoY = this.y ;
    double bottomPortTwoY = this.y + h;
    double bottomPortOneY = this.y + h;

    public Group drawConnections(int port, int objectheight){
        Shape arrow= new Rectangle();
        switch (port){
            case 1: arrow = new Rectangle((x+w/3-10),objectheight,20,(y-objectheight));break;
            case 2: arrow = new Rectangle(topPortTwoX, topPortTwoY, 20, y-objectheight);break;
            case 3: arrow = new Rectangle(bottomPortOneX, objectheight, 20, objectheight-y);break;

        }
        return new Group(arrow);
    }

    public Group draw() {
        Shape body = new Rectangle(x,y,w,h);
        Text text = new Text();
        text.textProperty().bind(register.RegDisplayText);
        Color color = Color.rgb(255,150,100);
        body.setFill(color);
        text.setX(x+w/10);
        text.setY(y+h/2+2);
        text.setTextAlignment(TextAlignment.CENTER);
        return new Group(body,text);
    }

}
