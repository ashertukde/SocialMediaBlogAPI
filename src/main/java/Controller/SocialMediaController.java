package Controller;

import Model.Account;
import Model.Message;
import Service.AccountService;
import Service.MessageService;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("/messages", this::getMessageHandler);
        app.post("/messages", this::postMessageHandler);
        app.get("/messages/{message_id}", this::getMessagebyIDHandler);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void getMessageHandler(Context context) {
        List<Message> messages = messageService.getAllMessages();
        context.json(messages);
        context.status(200);
    }
    private void getMessagebyIDHandler(Context context) throws JsonMappingException, JsonProcessingException {
        //ObjectMapper mapper = new ObjectMapper();
        //Message message = mapper.readValue(context.body(), Message.class);
        Message foundMessage = messageService.getMessagebyID(Integer.parseInt(context.pathParam("message_id")));
        System.out.println((context.pathParam("message_id")));
        if(foundMessage != null)
        {
            context.json(foundMessage);
        }
        else
        {
            context.json("");
        }
        context.status(200);
    }
    private void postMessageHandler(Context context) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(context.body(), Message.class);
        Message addedMessage = messageService.addMessage(message);
        if(addedMessage!=null){
            context.status(200);
            context.json(mapper.writeValueAsString(addedMessage));
                       
        }else{
            context.status(400);
        }
    }

}