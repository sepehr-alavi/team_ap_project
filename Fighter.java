//Checked

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;

public class Fighter extends Aircraft{
    private int hp;
    Image image = new Image("file:C:\\Users\\Sepehr\\Desktop\\project\\Fighter.png");
    ImageView ax = new ImageView(image);
    LineTo lineTo = new LineTo(600, 400);


    //Getter and setter
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    //Constructor
    public Fighter( double x, double y, double speed, int hp ){
        this.setCoordinate( x, y );
        this.setSpeed( speed );
        this.hp = hp;
        ax.setFitWidth(75);
        ax.setFitHeight(65);
    }

}
