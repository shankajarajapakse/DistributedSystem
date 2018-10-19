package CS4262.Core;

import CS4262.Helpers.IDCreator;
import CS4262.MainController;
import CS4262.Models.File;
import CS4262.Models.Node;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Lahiru Kaushalya
 */
public class ContentInitializer {
    
    private final String[] availableFiles;
    private final IDCreator idCreator;
    private final Node node;
    private final MainController mainController;
    
    private static ContentInitializer instance;
    
    public static ContentInitializer getInstance(){
        if(instance == null){
            instance = new ContentInitializer();
        }
        return instance;
    }
    
    private ContentInitializer(){
        this.availableFiles = new String[]{
            "Adventures of Tintin",
            "Jack and Jill",
            "Glee",
            "The Vampire Diarie",
            "King Arthur",
            "Windows XP",
            "Harry Potter",
            "Kung Fu Panda",
            "Lady Gaga",
            "Twilight",
            "Windows 8",
            "Mission Impossible",
            "Turn Up The Music",
            "Super Mario",
            "American Pickers",
            "Microsoft Office 2010",
            "Happy Feet",
            "Modern Family",
            "American Idol",
            "Hacking for Dummies"
        };
        this.idCreator = new IDCreator();
        this.mainController = MainController.getInstance();
        this.node = mainController.getNode();
    }
    
    public void createNodeContent(){
        
        Random ran = new Random();
        int numOfFiles = 3 + ran.nextInt(3);
        
        List<File> content = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        
        String text = "ID\tName\n\n",fileName;
        
        //genatate random number of files from available files
        int x = 0, index;
        while(x < numOfFiles){
            index = ran.nextInt(availableFiles.length - 1);
            //Avoid file duplications
            if(!temp.contains(index)){
                fileName = availableFiles[index];
                String fileID = idCreator.generateFileID(fileName);
                content.add(new File(fileName, fileID));
                text += fileID + "\t" + fileName + "\n";
                temp.add(index);
                x++;
            }
        }
        //set node content
        node.setContent(content);
        //update UI
        mainController.getMainFrame().updateContent(text);
    }
    
}