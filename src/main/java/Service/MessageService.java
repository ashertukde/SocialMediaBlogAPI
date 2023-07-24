package Service;

import Model.Account;
import Model.Message;
import DAO.MessageDAO;
import DAO.AccountDAO;
import java.util.List;
public class MessageService {
    public MessageDAO messageDAO;
    public AccountDAO accountDAO;
    public MessageService()
    {
        this.messageDAO = new MessageDAO();
        this.accountDAO = new AccountDAO();
    }
    public MessageService(MessageDAO messageDAO)
    {
        this.messageDAO = messageDAO;
        this.accountDAO = new AccountDAO();
    }
    public MessageService(MessageDAO messageDAO, AccountDAO accountDAO)
    {
        this.messageDAO = messageDAO;
        this.accountDAO = accountDAO;
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
        List<Account> accounts = accountDAO.getAllAccounts(); 
        boolean result = false;
        for(int i = 0; i < accounts.size(); i++)
        {
            if(accounts.get(i).getAccount_id() == message.getPosted_by())
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
        Message message = messageDAO.getMessagebyID(id);
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
        if(message_text.isBlank())
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
    public List<Message> getMessagebyUserID(int id)
    {       
        List<Message> message = messageDAO.getMessagebyUserID(id);                
        return message;
    }
}
