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
        Message addedmessage = messageDAO.insertMessage(message);
        return addedmessage;
    }
    public Message getMessagebyID(int id)
    {
        //Message message = messageDAO.getMessagebyID(id);
        List<Message> messages = this.getAllMessages();
        Message message = new Message();
        boolean result = false;
        for(int i = 0; i < messages.size(); i++)
        {
            if(messages.get(i).getMessage_id() == id)
            {
                message = messages.get(i);
                result = true;
                break;
            }
        }
        if(!result)
        {
            return null;
        }
        return message;
    }
    public Message deleteMessagebyID(int id)
    {       
        Message message = this.getMessagebyID(id);
        if(message == null)
        {
            return null;
        }        
        messageDAO.deleteMessagebyID(id);        
        return message;
    }
    public Message updateMessagebyID(int id, String message_text)
    {       
        Message message = this.getMessagebyID(id);
        if(message == null)
        {
            return null;
        }
        if(message_text == "")
        {
            return null;
        }
        if(message_text.length() >= 255)
        {
            return null;
        }        
        messageDAO.updateMessagebyID(id, message_text); 
        message = this.getMessagebyID(id);       
        return message;
    }
}
