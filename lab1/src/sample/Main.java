package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Polygon;


import java.awt.*;


public class Main extends Application {
    private final static short WIDTH = 600;
    private final static short HEIGHT = 400;
    Color blue = Color.rgb(00,128,255);
    Color brown = Color.rgb(128,64,0);
    Color green = Color.rgb(0,128,0);
    Color yellow = Color.rgb(255,255,0);

    @Override

    public void start(Stage stage) {
        stage.setTitle("Lab1");

        Group root = new Group();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(blue);

        Rectangle r = new Rectangle(200,240,40,100);
        r.setFill(brown);
        root.getChildren().add(r);

        Polygon triangle1 = new Polygon(130.0, 240.0,
                220.0, 120.0,
                310.0, 240.0
        );

        triangle1.setFill(green);
        root.getChildren().add(triangle1);

        Polygon triangle2 = new Polygon(150.0, 175.0,
                215.0, 70.0,
                285.0, 180.0
        );

        triangle2.setFill(green);
        root.getChildren().add(triangle2);

        Polygon triangle3 = new Polygon(170.0, 110.0,
                215.0, 10.0,
                265.0, 120.0
        );

        triangle3.setFill(green);
        root.getChildren().add(triangle3);

        Rectangle square1 = new Rectangle(240,185,15,15);
        square1.setFill(yellow);
        root.getChildren().add(square1);

        Rectangle square2 = new Rectangle(190,150,15,15);
        square2.setFill(yellow);
        root.getChildren().add(square2);

        Rectangle square3 = new Rectangle(230,130,15,15);
        square3.setFill(yellow);
        root.getChildren().add(square3);

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
