//Author: Zhanghao Wen
//it runs!

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	
	GridPane gridPane = new GridPane();
	boolean XPlayerTurn = true;
	String board[][] = new String[3][3];
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			gridPane.setPadding(new Insets(30,5,30,150));
			gridPane.setVgap(10);
			gridPane.setHgap(15);
			
			Button restartButn = new Button("Restart");
			buildGrid(restartButn);
			
			restartButn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					gridPane.getChildren().clear();
					XPlayerTurn = true;
					board = new String[3][3];
					buildGrid(restartButn);
				}
			});
			
			Scene scene = new Scene(gridPane,1000,600);
			primaryStage.setTitle("Tic Tac Toe");
			primaryStage.setScene(scene);
			primaryStage.show();
				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void buildGrid(Button restartButton) {
		
		Image xImg = new Image(getClass().getResource("x.jpg").toString(), 140, 160, true, false);
		Image oImg = new Image(getClass().getResource("o.jpg").toString(), 140, 170, true, false);
		
		Label user1 = new Label("User1");
		user1.setFont(new Font("Arial", 40));
		Label user2 = new Label("User2");
		user2.setFont(new Font("Arial", 40));

	    gridPane.add(user1, 3, 0);
	    gridPane.add(user2, 3, 2);
	    user2.setVisible(false);
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				Label whiteGrid = new Label("");
				whiteGrid.setBorder(new Border(new BorderStroke(Color.WHITE, BorderStrokeStyle.SOLID, null, new BorderWidths(70))));

				gridPane.add(whiteGrid, j, i);
				//handle each click in each panel
				whiteGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent mouseEvent) {
						//get coordinate
						int col = GridPane.getColumnIndex( (Node) mouseEvent.getSource() );
						int row = GridPane.getRowIndex( (Node) mouseEvent.getSource() );
						//if game is ongoing
						if(checkGame()=="" && board[col][row] == null) {	
							if(XPlayerTurn) {	
								if(gridPane.contains(3, 0)) {
									//show player turn
									user1.setVisible(false);
								}

								ImageView imageX = new ImageView(xImg);
								gridPane.add(imageX, col, row);
								board[col][row] = "X";

								if(checkGame()=="") {
									user2.setVisible(true);
									
								}

							}else {
								if(gridPane.contains(3, 2)) {
									user2.setVisible(false);
								}

								ImageView imageO = new ImageView(oImg);
								gridPane.add(imageO, col, row);
								board[col][row] = "O";

								if(checkGame()=="") {
									user1.setVisible(true);
								}
							} 
							//after set new value, system check again to see whether there is a winner
							//game ends
							if(checkGame() == "draw") {
								Label end = new Label("Game Draw");
								end.setFont(new Font("Arial", 30));
								gridPane.add(end, 2, 3);
								gridPane.add(restartButton, 0, 3);
							}else if(checkGame() != "") {
								Label end = new Label("Congratulations, " + checkGame() + " win the game!");
								end.setFont(new Font("Arial", 25));
								gridPane.add(end, 2, 3);
								gridPane.add(restartButton, 0, 3);
							}
								//switch player turn
								XPlayerTurn = !XPlayerTurn;
						}

						
					}
				});
			}
		}
	}
	
	//indicates game status -- ongoing/draw/who is the winner
	private String checkGame() {
		
		for(int i=0; i<3; i++) {
			if(check(new String[]{board[0][i], board[1][i], board[2][i]})) {
				return board[0][i];
			}
		}
		for(int j=0; j<3; j++) {
			if(check(new String[]{board[j][0], board[j][1], board[j][2]})) {
				return board[j][0];
			}
		}
		if(check(new String[]{board[0][0], board[1][1], board[2][2]})) {
			return board[0][0];
		}
		if(check(new String[]{board[2][0], board[1][1], board[0][2]})) {
			return board[2][0];
		}
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(board[j][i] == null) {
					return "";
				}
			}
		}
		
		return "draw";
	}
	
	
	private boolean check(String[] nodes) {
		for(String value : nodes) {
			if(value == null) {
				return false;
			}
		}
		return nodes[0].equals(nodes[1]) && nodes[0].equals(nodes[2]);
	}
	

}
