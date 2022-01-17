package fr.polytech.projet.naturalthescattering.controller.ws;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.polytech.projet.naturalthescattering.controller.ws.msg.ActionFrame;
import fr.polytech.projet.naturalthescattering.controller.ws.msg.DeckFrame;
import fr.polytech.projet.naturalthescattering.controller.ws.msg.QueryFrame;

@Component
public class DuelController extends TextWebSocketHandler {
	@Autowired
	private Logger log;
	
	@Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		try {
			session.getAttributes().put("game", session.getAttributes().get("game") == null ? new DuelGame() : session.getAttributes().get("game"));
			DuelGame game = (DuelGame) session.getAttributes().get("game");
			
			ObjectMapper mapper  = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			JsonNode req = mapper.readTree(message.getPayload());
			if (req == null || req.get("type") == null) {
				session.close(CloseStatus.BAD_DATA);
				return;
			}

			String response = null;
			switch (req.get("type").asText()) {
			case "QUERY":
				QueryFrame qf = mapper.readValue(message.getPayload(), QueryFrame.class);
				response = game.processQuery(qf); // add player to waitlist / bot
				break;
			case DeckFrame.type:
				DeckFrame df = mapper.readValue(message.getPayload(), DeckFrame.class);
				response = game.processDeck(df); // deck selected successfully?
				break;
			case ActionFrame.type:
				ActionFrame af = mapper.readValue(message.getPayload(), ActionFrame.class);
				// execute action on gameboard
				response = game.processAction(af); // move correct? 
				break;
			default:
				// close socket ? ask retry ?
				session.close(CloseStatus.BAD_DATA);
				return;
			}
			session.sendMessage(new TextMessage(response));
		} catch (JsonProcessingException e) {
			log.warn("Unable to parse JSON");
			e.printStackTrace();
			session.close(CloseStatus.BAD_DATA);
		}
    }
}
