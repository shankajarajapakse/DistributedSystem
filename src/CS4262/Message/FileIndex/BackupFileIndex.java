package CS4262.Message.FileIndex;

import CS4262.Models.DataTransfer.NodeDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import CS4262.Interfaces.IMessage;
import CS4262.Models.DataTransfer.MessageDTO;

/**
 *
 * @author Lahiru Kaushalya
 */
public class BackupFileIndex implements IMessage{
    
    @Override
    public String send(MessageDTO msgDTO){
        String message = createMsg();
        return msgSender.sendMsg(msgDTO.getReceiver(), message);
    }
    
    /*
    Update File Index message format 
    length BACKUP_FI file_count file_id_1 ip port file_id_2 ip port ....
    */
    @Override
    public String createMsg() {
        String msg = " BACKUP_FI ";
        Map<String, List<NodeDTO>> fileIndex = node.getFileIndex();
        msg += fileIndex.size();
        
        for(String fileID : fileIndex.keySet()){
            List<NodeDTO> tempNodes = fileIndex.get(fileID);
            msg += " " + tempNodes.size() + " " + fileID;
            
            for(NodeDTO tempNode : tempNodes){
                if(!node.getId().equals(idCreator.generateNodeID(tempNode.getipAdress(), tempNode.getUdpPort()))){
                    msg += " " + tempNode.getipAdress() + " " + tempNode.getUdpPort();
                }
            }
        }
        return String.format("%04d", msg.length() + 5) + " " + msg;
    }
    
    @Override
    public void handle(StringTokenizer st) {
        int fileCount = Integer.parseInt(st.nextToken());
        Map<String, List<NodeDTO>> fileIndexBackup = node.getFileIndexBackup();
        
        while (fileCount > 0) {
            int nodeCount = Integer.parseInt(st.nextToken());
            String fileID = st.nextToken();
            List<NodeDTO> fileHolders = new ArrayList<>();
            while (nodeCount > 0) {
                NodeDTO fileHolder = new NodeDTO(st.nextToken(), Integer.parseInt(st.nextToken()));
                fileHolders.add(fileHolder);
                nodeCount--;
            }
            fileIndexBackup.put(fileID, fileHolders);
            fileCount--;
        }
        node.setFileIndexBackup(fileIndexBackup);
        System.out.print("");
    }
    
}
