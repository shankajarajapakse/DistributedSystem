package CS4262;

import CS4262.Models.Node;
import java.io.IOException;

/**
 *
 * @author Lahiru Kaushalya
 */
public class MainController {
    
    private static Node node;
    private static MainFrame mainFrame;
    private static MainController instance;
    
    public static MainController getInstance() {
        if(instance == null){
            instance = new MainController();
        }
        return instance;
    }
      
    private MainController(){}
    
    public static void main(String args[]) throws IOException{
        MainController.mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }
    
    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        MainController.node = node;
    }
    
    public MainFrame getMainFrame() {
        return mainFrame;
    }
    
}
