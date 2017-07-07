//Checked

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;

public class Fighter extends Aircraft{
    private int hp;
    Image image = new Image("Media\\Fighter.png");
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
    public Fighter( double x, double y, double speed, int hp ) {
        this.setCoordinate(x, y);
        this.setSpeed(speed);
        this.hp = hp;
        ax.setFitWidth(75);
        ax.setFitHeight(65);
        setBearing(Math.atan(((this.getCoordinate().getY() - 400) / (this.getCoordinate().getX() - 600)))* (180 / Math.PI));
        System.out.println(getBearing());
        ax.setRotate(180 + getBearing());
        if( this.getCoordinate().getX() > 600 )
            ax.setRotate(ax.getRotate() + 180);
    }

}
