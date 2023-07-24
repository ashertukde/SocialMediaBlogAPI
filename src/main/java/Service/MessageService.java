package Service;

import Model.Message;
import DAO.MessageDAO;
import java.util.List;
public class MessageService {
    public MessageDAO messageDAO;
    public MessageService()
    {
        this.messageDAO = new MessageDAO();
    }
    public MessageService(MessageDAO messageDAO)
    {
        this.messageDAO = messageDAO;
    }
    public List<Message> getAllMessages(){
        return messageDAO.getAllMessages();
    }
    public Message addMessage(Message message)
    {   
        if(message.getMessage_text().isBlank())
        {
            return null;
        }
        if(message.getMessage_text().length() >= 255)
        {
            return null;
        }  
        List<Message> messages = this.getAllMessages();
        boolean result = false;
        for(int i = 0; i < messages.size(); i++)
        {
            if(messages.get(i).getPosted_by() == message.getPosted_by())
            {
                result = true;
            }
        }
        if(!result)
        {
            return null;
        } 
        messageDAO.insertMessage(message);
        return message;
    }
}
