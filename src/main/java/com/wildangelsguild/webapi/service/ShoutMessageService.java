package com.wildangelsguild.webapi.service;

import com.wildangelsguild.webapi.model.ShoutMessage;
import com.wildangelsguild.webapi.repository.ShoutMessageRepository;
import com.wildangelsguild.webapi.request.ShoutRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ShoutMessageService {
    private final ShoutMessageRepository shoutMessageRepository;

    public ShoutMessageService(ShoutMessageRepository shoutMessageRepository) {
        this.shoutMessageRepository = shoutMessageRepository;
    }

    public List<ShoutMessage> getAllShoutMessages() {
        return shoutMessageRepository.findAll();
    }

    public void saveShoutMessage(ShoutRequest shoutRequest) {
        ShoutMessage shoutMessage = new ShoutMessage();
        shoutMessage.setUsername(shoutRequest.getName());
        shoutMessage.setMessage(shoutRequest.getMessage());
        shoutMessage.setDateCreated(new Date());

        shoutMessageRepository.save(shoutMessage);
    }
}
