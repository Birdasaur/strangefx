package com.gluonhq.strange.ui;

import com.gluonhq.strange.simulator.Gate;
import de.jensd.fx.glyphs.materialicons.MaterialIcon;
import de.jensd.fx.glyphs.materialicons.utils.MaterialIconFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        System.out.println("hello, strange");
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        CircuitBoard board = new CircuitBoard();

        HBox toolbarFiller = new HBox();
        HBox.setHgrow( toolbarFiller, Priority.ALWAYS);

        Button btAddCircuit = new Button("",
                MaterialIconFactory.get().createIcon(MaterialIcon.PLAYLIST_ADD, "2em"));
        //btAddCircuit.setOnAction( e -> board.getCircuits().add( new Circuit(board.getCircuits().size())));

        ImageView logo = new ImageView("/Gluon_combined_logo_300px.png");
        logo.setPreserveRatio(true);
        logo.setFitWidth(100);
        ToolBar gatesPane = new ToolBar(
                btAddCircuit,
                new Separator(),
                GateSymbol.of(Gate.NOT, false),
                GateSymbol.of(Gate.HADAMARD, false),
                toolbarFiller,
                logo
        );

        board.getCircuits().addAll(new Circuit(0), new Circuit(1), new Circuit(2));
        ScrollPane scroller = new ScrollPane(board);
        scroller.setPannable(true);
        scroller.setFitToWidth(true);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(gatesPane);
        borderPane.setCenter(scroller);

        Scene scene = new Scene(borderPane, 600, 400);
        scene.getStylesheets().add(Main.class.getResource("/styles.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

}
