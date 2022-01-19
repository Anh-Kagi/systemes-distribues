package fr.polytech.projet.naturalthescattering.service;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.polytech.projet.naturalthescattering.db.Message;
import fr.polytech.projet.naturalthescattering.db.Thread;
import fr.polytech.projet.naturalthescattering.db.User;
import fr.polytech.projet.naturalthescattering.db.repository.IMessageRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IThreadRepository;
import fr.polytech.projet.naturalthescattering.db.repository.IUserRepository;
import fr.polytech.projet.naturalthescattering.service.result.ForumResult;

@Service
public class ForumService {
	@Autowired
	private IUserRepository users;
	
	@Autowired
	private IThreadRepository threads;
	
	@Autowired
	private IMessageRepository messages;

	public ForumResult.Thread.Create createThread(String pseudo, String name, String content) {
		User user = users.findByPseudo(pseudo);
		ForumResult.Thread.Create result = new ForumResult.Thread.Create();
		
		Thread thread = new Thread(name, user, content);
		threads.save(thread);
		
		result.setStatus(ForumResult.Thread.CreateStatus.OK);
		result.setId(thread.getId());
		return result;
	}
	
	public ForumResult.Thread.Read readThread(Long id) {
		Thread thread = threads.findById(id).orElse(null);
		ForumResult.Thread.Read result = new ForumResult.Thread.Read();
		
		if (thread == null) {
			result.setStatus(ForumResult.Thread.ReadStatus.THREAD_DOESNT_EXISTS);
			return result;
		}
		
		result.setStatus(ForumResult.Thread.ReadStatus.OK);
		result.fromThread(thread);
		return result;
	}
	
	public ForumResult.Thread.Update updateThread(String pseudo, Long id, boolean open) {
		Thread thread = threads.findById(id).orElse(null);
		ForumResult.Thread.Update result = new ForumResult.Thread.Update();
		
		if (thread == null) {
			result.setStatus(ForumResult.Thread.UpdateStatus.THREAD_DOESNT_EXISTS);
			return result;
		}
		
		if (!thread.getAuthor().getPseudo().equals(pseudo)) {
			result.setStatus(ForumResult.Thread.UpdateStatus.THREAD_DOESNT_BELONG_TO_USER);
			return result;
		}

		thread.setOpen(open);
		threads.save(thread);
		
		result.setStatus(ForumResult.Thread.UpdateStatus.OK);
		result.fromThread(thread);
		return result;
	}
	
	public ForumResult.Thread.Delete deleteThread(String pseudo, Long id) {
		Thread thread = threads.findById(id).orElse(null);
		ForumResult.Thread.Delete result = new ForumResult.Thread.Delete();
		
		if (thread == null) {
			result.setStatus(ForumResult.Thread.DeleteStatus.THREAD_DOESNT_EXISTS);
			return result;
		}
		
		if (!thread.getAuthor().getPseudo().equals(pseudo)) {
			result.setStatus(ForumResult.Thread.DeleteStatus.THREAD_DOESNT_BELONG_TO_USER);
			return result;
		}

		result.setStatus(ForumResult.Thread.DeleteStatus.OK);
		return result;
	}

	public ForumResult.Thread.List readMsgList(Long id, Long offset, Long limit) {
		Thread thread = threads.findById(id).orElse(null);
		ForumResult.Thread.List result = new ForumResult.Thread.List();
		
		if (thread == null) {
			result.setStatus(ForumResult.Thread.ListStatus.THREAD_DOESNT_EXISTS);
			return result;
		}
		
		Stream<Message> stream = messages.findByThreadOrderByDateAsc(thread);
		if (offset != null)
			stream = stream.skip(offset);
		if (limit != null)
			stream = stream.limit(limit);
		
		long messages[] = stream.mapToLong((m) -> m.getId()).toArray();
		
		result.setStatus(ForumResult.Thread.ListStatus.OK);
		result.setIds(messages);
		return result;
	}

	public ForumResult.Message.Create createMessage(String pseudo, Long id, String content) {
		Thread thread = threads.findById(id).orElse(null);
		User user = users.findByPseudo(pseudo);
		ForumResult.Message.Create result = new ForumResult.Message.Create();
		
		if (thread == null) {
			result.setStatus(ForumResult.Message.CreateStatus.THREAD_DOESNT_EXISTS);
			return result;
		}
		
		if (!thread.getOpen()) {
			result.setStatus(ForumResult.Message.CreateStatus.THREAD_CLOSED);
			return result;
		}
		
		Message message = new Message(thread, user, content);
		message = messages.save(message);
		
		result.setStatus(ForumResult.Message.CreateStatus.OK);
		result.setId(message.getId());
		return result;
	}

	public ForumResult.Message.Read readMsg(Long id) {
		Message message = messages.findById(id).orElse(null);
		ForumResult.Message.Read result = new ForumResult.Message.Read();
		
		if (message == null) {
			result.setStatus(ForumResult.Message.ReadStatus.MESSAGE_DOESNT_EXISTS);
			return result;
		}
		
		result.setStatus(ForumResult.Message.ReadStatus.OK);
		result.fromMessage(message);
		return result;
	}
}
