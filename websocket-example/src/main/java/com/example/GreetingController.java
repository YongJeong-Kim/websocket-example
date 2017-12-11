package com.example;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {
	@Autowired private SimpMessagingTemplate simpMessagingTemplate;
	

    @MessageMapping("/hello/{user}")
//    @SendToUser  // "/user/queue/hello/{user}" // mapping
    public void greeting(@DestinationVariable String user,
    					HelloMessage message,
    					@Header("simpSessionId") String sessionId,
    					Principal principal
    					) throws Exception {
//        Thread.sleep(1000); // simulated delay
       System.out.println(user);
       System.out.println(sessionId);
       simpMessagingTemplate.convertAndSendToUser(principal.getName(), "/queue/hello/uuu", message);
//        return new Greeting(message.getName(), message.getMessage());
    }
    
}
