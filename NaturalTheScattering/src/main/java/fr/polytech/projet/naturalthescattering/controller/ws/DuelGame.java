package fr.polytech.projet.naturalthescattering.controller.ws;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.polytech.projet.naturalthescattering.controller.ws.msg.ActionFrame;
import fr.polytech.projet.naturalthescattering.controller.ws.msg.ActionFrameResponse;
import fr.polytech.projet.naturalthescattering.controller.ws.msg.DeckFrame;
import fr.polytech.projet.naturalthescattering.controller.ws.msg.DeckFrameResponse;
import fr.polytech.projet.naturalthescattering.controller.ws.msg.EmergencyEndGameFrame;
import fr.polytech.projet.naturalthescattering.controller.ws.msg.QueryFrame;
import fr.polytech.projet.naturalthescattering.controller.ws.msg.QueryFrameResponse;
import fr.polytech.projet.naturalthescattering.db.Duel;

public class DuelGame {
	public static enum DuelStage {
		QUERY,
		DECK,
		ACTION,
		END;
	}
	
	private DuelStage stage = DuelStage.QUERY;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	private Duel duel;
	
	public String processQuery(QueryFrame qf) throws JsonProcessingException {
		if (stage == DuelStage.QUERY) {
			if (qf.getBot()) { // TODO
				// set bot
				stage = DuelStage.DECK;
				return mapper.writeValueAsString(new QueryFrameResponse());
			} else {
				// find another player
				// set player
				stage = DuelStage.DECK;
				return mapper.writeValueAsString(new QueryFrameResponse());
			}
		} else {
			return mapper.writeValueAsString(new EmergencyEndGameFrame("Not in Query stage."));
		}
	}

	public String processDeck(DeckFrame df) throws JsonProcessingException {
		if (stage == DuelStage.DECK) {
			if (df.getDeck() != 0) { // TODO: actually verify the deck is valid
				// set deck
				stage = DuelStage.ACTION;
				return mapper.writeValueAsString(new DeckFrameResponse()); // TODO: valid
			} else {
				return mapper.writeValueAsString(new DeckFrameResponse()); // TODO: retry
			}
		} else {
			return mapper.writeValueAsString(new EmergencyEndGameFrame("Not in Deck stage."));
		}
	}

	public String processAction(ActionFrame af) throws JsonProcessingException {
		if (stage == DuelStage.ACTION) {
			// do smth with action
			return mapper.writeValueAsString(new ActionFrameResponse());
		} else {
			return mapper.writeValueAsString(new EmergencyEndGameFrame("Not in Action stage."));
		}
	}
}
