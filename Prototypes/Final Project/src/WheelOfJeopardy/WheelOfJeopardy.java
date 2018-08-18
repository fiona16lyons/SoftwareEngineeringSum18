package WheelOfJeopardy;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
	This is the main startup file for Prototype 2 of the Wheel of Jeopardy
	game. 
	
	@author Fiona Lyons
	@version 1.0
*/

public class WheelOfJeopardy extends Application
{
	@Override
    public void start(final Stage primaryStage) {
		// Create grid for the basic board layout
        GridPane root = new GridPane();
        
        root.setAlignment (Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25,25,25,25));
        
        // Start Menu
        Button p2Btn = new Button("2 Player");
        Button p3Btn = new Button("3 Player");
        Button editBtn = new Button("Question Editor");
        Button instrBtn = new Button("Instruction");
        VBox vbBtn = new VBox(20);
        vbBtn.setAlignment(Pos.CENTER);
        vbBtn.getChildren().add(p2Btn);
        vbBtn.getChildren().add(p3Btn);
        vbBtn.getChildren().add(editBtn);
        vbBtn.getChildren().add(instrBtn);
        root.add(vbBtn,0,0);

        //Button Actions
        final Text actionTarget = new Text();
        root.add(actionTarget,0,1);
        
        p2Btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            	actionTarget.setFill(Color.FIREBRICK);
                actionTarget.setText("2 player button pressed");
                //primaryStage.setScene(testFunction());
                //primaryStage.show();
            }
        });
        
        p3Btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            actionTarget.setFill(Color.TEAL);
            actionTarget.setText("3 player button pressed");
        }});
        
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            actionTarget.setFill(Color.GREEN);
            actionTarget.setText("Question Editor button pressed");
        }});
        
        instrBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            actionTarget.setFill(Color.CHOCOLATE);
            actionTarget.setText("Instruction button pressed");
        }});

        Scene scene = new Scene(root, 1000, 1000);
        
        primaryStage.setTitle("Wheel of Jeopardy");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public Scene testFunction()
	{
		GridPane root2 = new GridPane();
        
        root2.setAlignment (Pos.CENTER);
        root2.setHgap(10);
        root2.setVgap(10);
        root2.setPadding(new Insets(25,25,25,25));
        VBox vbBtn = new VBox(20);
        vbBtn.setAlignment(Pos.CENTER);
        Button p2Btn = new Button("AAAAAAAAAAAAAAAAAA");
        vbBtn.getChildren().add(p2Btn);
        root2.add(vbBtn,0,0);
        Scene scene2 = new Scene(root2, 1000, 1000);
        return scene2;
	}
	
	public static Scanner userInput = new Scanner(System.in);
	public static void main(String [] args)
	{
		Application.launch(args);
		String myInputFile = "input/real_questions.txt"; //args[0];//
		try
		{
			BufferedReader readIn = new BufferedReader(new FileReader(myInputFile));
			System.out.println("Able to read input file!");
			readIn.close();
			
			// Make sure data is loaded in properly
			Controller newGame = new Controller(myInputFile);
			//Game newGame = new Game(myInputFile);
			System.out.println(newGame.toString());
			System.out.println("=================================");
			System.out.println("=================================");
			System.out.println("Welcome to Wheel of Jeopardy!");
			System.out.println("=================================");
	        int userResponse = -1;
			while (!newGame.isGameOver())
			{
				while (!((userResponse ==0) || (userResponse == 1) || userResponse == 2)) 
	            {
                    System.out.println(newGame.displayPoints());
					System.out.println(newGame.printOptions());
	                // Make sure user input is valid
	                if (userInput.hasNext())
                    try
	                {
	                    userResponse = userInput.nextInt();
	                }
	                catch (InputMismatchException a)
	                { // Non integer input, reset user input
	                    userInput.next();                     
	                }

	            }
				if (userResponse == 1)
				{
					newGame.takeTurn();
				}
				else if (userResponse == 2)
				{
					System.out.println(newGame.displayBoard());
					userResponse = -1;
				}
				userResponse = -1;
			}
			userInput.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e);
			System.out.println("Stack Trace: " + e.getStackTrace());
			System.out.println("Unable to read input file :-(");
		}
	}

}