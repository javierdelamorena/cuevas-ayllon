package com.cuevasdeayllon.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cuevasdeayllon.firebase.FirebaseInitializer;
import com.cuevasdeayllon.post.managment.NotificationRequest;
import com.cuevasdeayllon.post.managment.PostDTO;
import com.cuevasdeayllon.service.PostManagementService;
import com.cuevasdeayllon.websocket.NotificationWebSocket;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
@Controller
@RequestMapping(value = "/post")
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	  @Autowired
	    private PostManagementService service;

	    @GetMapping(value = "/greet/{name}")
	    public String greet(@PathVariable(value = "name") String name){
	        return  "Hello, "+name;
	    }

	    @GetMapping(value = "/list")
	    public ResponseEntity list(){
	        return new ResponseEntity(service.list(), HttpStatus.OK);
	    }

	    @PostMapping(value = "/add")
	    public ResponseEntity add(@RequestBody PostDTO post){
	        return new ResponseEntity(service.add(post), HttpStatus.OK);
	    }

	    @PutMapping(value = "/{id}/update")
	    public ResponseEntity edit(@PathVariable(value = "id") String id, @RequestBody PostDTO post){
	        return new ResponseEntity(service.edit(id,post), HttpStatus.OK);
	    }

	    @DeleteMapping(value = "/{id}/delete")
	    public ResponseEntity delete(@PathVariable(value = "id") String id){
	        return  new ResponseEntity(service.delete(id), HttpStatus.OK);
	    }
	    
	    public String sendPushNotificationService(NotificationRequest request) {
	        Map<String, String> firebaseMessageBody = new HashMap<>();
	        firebaseMessageBody.put("title", request.getTitle());
	        firebaseMessageBody.put("body", request.getBody());
	        try {
	            Message message = Message
	                .builder()
	                .setToken(request.getToken())
	                .putAllData(firebaseMessageBody)
	                .build();

	            String response = FirebaseMessaging.getInstance().send(message);
	            return response;
	        } catch (FirebaseMessagingException e) {
	            logger.error("Firebase error sending: {}", e);
	            
	            return "Firebase error sending"; 
	        }
	    }
	    @PostMapping("registroDeAplicacion")
	    public @ResponseBody String registroDeAplicacion(@RequestBody NotificationRequest notification) {
	    	logger.info("Notification body: "+notification.getBody()+ " Notificacion title: "+ notification.getTitle()+ " Notificacion token: "+ notification.getToken());
	    
	        return "";
	    }
	    @PostMapping("send")
	    public String sendPushNotification(@RequestBody NotificationRequest request) {
	        String response = this.sendPushNotificationService(request);
	   	 NotificationWebSocket.notifyAllClients("Nuevo registro insertado");
	        return response;
	    }

}
