package sv.edu.udb.rest.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import sv.edu.udb.rest.model.Message;

//@Path("hello")
public class HelloJersey {
	//@GET
	//@Produces(MediaType.APPLICATION_JSON)
	public String getMessage() {
		return "Hello World, Jersey server is running!";
	}

	//@GET
	//@Path("messages")
	//@Produces(MediaType.APPLICATION_JSON)
	public Response getMessages() {
		List<Message> messages = new ArrayList<Message>();

		for (int i = 0; i <= 5; i++) {
			Message message = new Message();
			message.setId(i);
			message.setMessage("Mensaje #" + i);
			messages.add(message);
		}

		return Response.status(200).entity(messages).build();
	}
}
