package ViewGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // abstract method, must be overridden
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();

        //Stage mainStage = new Stage();

        try
        {
            // create the second window (MainWindow), which will contain details about the current program
            FXMLLoader loaderMainWindow = new FXMLLoader();
            loaderMainWindow.setLocation(getClass().getResource("MainWindow.fxml"));
            GridPane rootMainWindow = (GridPane) loaderMainWindow.load();
            ControllerGUIMainWindow controllerGUIMainWindow = loaderMainWindow.getController();

            Scene sceneMainWindow = new Scene(rootMainWindow, 400, 400);
            Stage secondaryStage = new Stage();
            secondaryStage.setTitle("Main Window");
            secondaryStage.setScene(sceneMainWindow);
            secondaryStage.show();

            // create the first window (ListProgramStatesWindow)
            FXMLLoader loaderListProgramsWindow = new FXMLLoader();
            loaderListProgramsWindow.setLocation(getClass().getResource("sample.fxml"));
            GridPane root = (GridPane) loaderListProgramsWindow.load();
            ControllerGUIListProgramStatesWindow controllerGUIListProgramStatesWindow = loaderListProgramsWindow.getController();
            // set the main window controller
            controllerGUIListProgramStatesWindow.setMainWindowController(controllerGUIMainWindow);

            Scene scene = new Scene(root, 400, 400);
            primaryStage.setTitle("Programs");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }


//        HBox root2 = new HBox(5);
//        root2.setPadding(new Insets(100));
//        root2.setAlignment(Pos.BASELINE_RIGHT);
//        Button prevBtn = new Button("Previous");
//        Button nextBtn = new Button("Next");
//        Button cancBtn = new Button("Cancel");
//        Button helpBtn = new Button("Help");
//        root2.getChildren().addAll(prevBtn,
//                nextBtn, cancBtn, helpBtn);
//
//        Scene scene = new Scene(root2, 150, 200);
//        primaryStage.setTitle("horizontal box");
//        primaryStage.setScene(scene);
//        primaryStage.show();

        // ListView

    }


    public static void main(String[] args) {
        launch(args);
    }
}
