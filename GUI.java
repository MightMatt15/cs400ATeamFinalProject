package application;

import java.io.FileInputStream;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GUI extends Application {
	// store any command-line arguments that were entered.
	// NOTE: this.getParameters().getRaw() will get these also
	private List<String> args;

	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 400;
	//read and write the files
    FileManager fileManager = new FileManager();
    //manage data provided in files for visualization and manipulation
    DataManager dataManager = new DataManager();
    //factory to store all farms
    CheeseFactory cheeseFactory = new CheeseFactory("Cheese Factory");

	@Override
	public void start(Stage primaryStage) throws Exception {
		// save args example
		// args = this.getParameters().getRaw();

		BorderPane panel = new BorderPane();

	     //change the background color
        Background background = new Background(new BackgroundFill(Color.CADETBLUE, CornerRadii.EMPTY, Insets.EMPTY));
        panel.setBackground(background);
        
        //title on the dashboard
		Label title = new Label("Milk Production Dashboard\n   -- Chalet Cheese Factory");
		title.setFont(Font.font("Marker Felt", FontWeight.EXTRA_BOLD, 25));
		panel.setTop(title);
		BorderPane.setAlignment(title, Pos.CENTER);

	      //create the input boxes on the left
		VBox selectionPanel = new VBox();
		Label selection = createLabel("Filters", "Chalkduster", FontWeight.BOLD, 20);
		Label year = createLabel("Year", "Times New Roman", FontWeight.BOLD, 15);
		TextField yearInput = new TextField();
		Label month = createLabel("Month", "Times New Roman", FontWeight.BOLD, 15);
		TextField monthInput = new TextField();
		Label day = createLabel("Day", "Times New Roman", FontWeight.BOLD, 15);
		TextField dayInput = new TextField();
		Label farm = createLabel("Farm", "Times New Roman", FontWeight.BOLD, 15);
		TextField farmInput = new TextField();

	      //new button to search
		Button search = new Button("SEARCH");
		
		
		

	      //add the text fields to the V box on the left
		selectionPanel.getChildren().add(selection);
		selectionPanel.getChildren().add(year);
		selectionPanel.getChildren().add(yearInput);
		selectionPanel.getChildren().add(month);
		selectionPanel.getChildren().add(monthInput);
		selectionPanel.getChildren().add(day);
		selectionPanel.getChildren().add(dayInput);
		selectionPanel.getChildren().add(farm);
		selectionPanel.getChildren().add(farmInput);
		selectionPanel.getChildren().add(search);
		panel.setLeft(selectionPanel);

		//add a section for showing total, max, min, avg, and percentages
		VBox resultsPanel = new VBox();
		GridPane results = new GridPane();
		results.add(createLabel("Total:	", "Times New Roman", FontWeight.BOLD, 15), 0, 0);
		results.add(createLabel("Maximum:	", "Times New Roman", FontWeight.BOLD, 15), 0,
				1);
		results.add(createLabel("Minimum:	", "Times New Roman", FontWeight.BOLD, 15), 0,
				2);
		results.add(createLabel("Average:	", "Times New Roman", FontWeight.BOLD, 15), 0,
				3);
		results.add(
				createLabel("Percentage:	", "Times New Roman", FontWeight.BOLD, 15), 0,
				4);
		for (int i = 0; i < 6; i++)
			results.add(new TextField(), 1, i);
		results.add(new TextField("e.g: farm 01"), 2, 2);
		results.add(new TextField("e.g: farm 02"), 2, 3);
		results.add(new Label("(out of all farms)"), 2, 4);
		results.add(new Label("(out of the whole time period)"), 2, 5);

		resultsPanel.getChildren()
				.add(createLabel("Results", "Chalkduster", FontWeight.BOLD, 20));
		resultsPanel.getChildren().add(results);
		results.setAlignment(Pos.TOP_LEFT);
		panel.setCenter(resultsPanel);
		
		Button inputFile = new Button("Input a file");
        panel.setBottom(inputFile);
        BorderPane.setAlignment(inputFile, Pos.BOTTOM_LEFT);
		
		//button to go to next scene
		Button nextScene = new Button("Output a file");
		panel.setRight(nextScene);
        BorderPane.setAlignment(nextScene, Pos.BOTTOM_RIGHT);
        
        
		
	    //set the main scene
		Scene mainScene = new Scene(panel, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		
		
		
		//secondary scene
		
		//output file scene
	    BorderPane panel2 = new BorderPane();
	    
	    //input file scene
	    BorderPane panel3 = new BorderPane();
	    Background background3 = new Background(new BackgroundFill(Color.AZURE, CornerRadii.EMPTY, Insets.EMPTY));
	    Label title3 = new Label("Milk Production Input\n  -- Chalet Cheese Factory");
	    title3.setFont(Font.font("Marker Felt", FontWeight.EXTRA_BOLD, 25));
	    panel3.setTop(title3);
	    BorderPane.setAlignment(title3, Pos.CENTER);

		//change the background color
        Background background2 = new Background(new BackgroundFill(Color.CADETBLUE, CornerRadii.EMPTY, Insets.EMPTY));
        panel2.setBackground(background2);
        
        //title on the dashboard
        Label title2 = new Label("Milk Production Output\n   -- Chalet Cheese Factory");
        title2.setFont(Font.font("Marker Felt", FontWeight.EXTRA_BOLD, 25));
        panel2.setTop(title2);
        BorderPane.setAlignment(title2, Pos.CENTER);
		
        //button to go to dashboard
        Button dashboard = new Button("Go to Dashboard");
        panel2.setRight(dashboard);
        BorderPane.setAlignment(dashboard, Pos.BOTTOM_RIGHT);
        
        //button to go back to dashboard from the input scene
        Button dashboard1 = new Button("Go to Dashboard");
        panel3.setRight(dashboard1);
        BorderPane.setAlignment(dashboard1, Pos.BOTTOM_RIGHT);
        VBox input = new VBox();
        input.getChildren().add(createLabel("Please input file location Below", "Chalkduster", FontWeight.BOLD, 20));
        TextField inputTextField = new TextField("Ex C:/users/myUser/file.txt");
        input.getChildren().add(inputTextField);
        panel3.setCenter(input);
        BorderPane.setAlignment(panel3, Pos.CENTER);
		
        //create input fields to output files
		VBox outputTypes = new VBox();
		outputTypes.getChildren().add(createLabel("Output Types", "Chalkduster", FontWeight.BOLD, 20));
		outputTypes.getChildren().add(createLabel("Farm Report", "Times New Roman", FontWeight.BOLD, 15));
		outputTypes.getChildren().add(new TextField("Farm ID, Year"));
		outputTypes.getChildren().add(createLabel("Annual Report", "Times New Roman", FontWeight.BOLD, 15));
		outputTypes.getChildren().add(new TextField("Year"));
		outputTypes.getChildren().add(createLabel("Monthly Report", "Times New Roman", FontWeight.BOLD, 15));
		outputTypes.getChildren().add(new TextField("Year, Month"));
		outputTypes.getChildren().add(createLabel("Date Range Report", "Times New Roman", FontWeight.BOLD, 15));
		outputTypes.getChildren().add(new TextField("Start date, End Date: year-month-day, month-day"));
        panel2.setCenter(outputTypes);
        BorderPane.setAlignment(panel2, Pos.CENTER);
        
		//page for outputs
		Scene secondaryScene = new Scene(panel2, WINDOW_WIDTH, WINDOW_HEIGHT);
		//page for inputs
		Scene inputScene = new Scene(panel3, WINDOW_WIDTH, WINDOW_HEIGHT);
		//make the buttons switch between scenes
	    nextScene.setOnAction(e -> {primaryStage.setScene(secondaryScene);primaryStage.show();});
        dashboard.setOnAction(e -> {primaryStage.setScene(mainScene);primaryStage.show();});
        inputFile.setOnAction(e -> {primaryStage.setScene(inputScene);primaryStage.show();});
        dashboard1.setOnAction(e -> {primaryStage.setScene(mainScene);primaryStage.show();});
		
        
        inputTextField.setOnAction(e -> {fileManager.inputFile = inputTextField.getText();
          if(fileManager.readFile()) {
            Alert alert = new Alert(AlertType.INFORMATION, "Input file " + inputTextField.getText() + " loaded");
            alert.showAndWait();
          }else {
            Alert alert = new Alert(AlertType.ERROR, "Input file was unable to be loaded");
            alert.showAndWait();
          }
          });
        
		
		// Add the stuff and set the primary stage
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	/**
	 * Create a label with specified font, font weight and size.
	 * 
	 * @param label  content of the label
	 * @param font   font
	 * @param weight font weight
	 * @param size   size of font
	 * @return a Label reference
	 */
	private Label createLabel(String label, String font, FontWeight weight, int size) {
		Label newLabel = new Label(label);
		newLabel.setFont(Font.font(font, weight, size));
		return newLabel;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
