package application;

import javafx.stage.Stage;



public class Main {

  
  
  public void main(String args[]) {
    //create and start the GUI
    GUI gui = new GUI();
    try {
      gui.start(new Stage());
    } catch (Exception e) {
      System.out.println("GUI failed to start");
    }
    
  }
}
