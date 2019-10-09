package com.wildangelsguild.webapi.controller;

import com.wildangelsguild.webapi.model.ShoutMessage;
import com.wildangelsguild.webapi.request.ShoutRequest;
import com.wildangelsguild.webapi.response.ShoutResponse;
import com.wildangelsguild.webapi.service.ShoutMessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/shoutmessage", produces="application/json")
public class ShoutController {
    private final ShoutMessageService shoutMessageService;

    public ShoutController(ShoutMessageService shoutMessageService) {
        this.shoutMessageService = shoutMessageService;
    }

    @MessageMapping("/shout")
    @SendTo("/topic/shoutmessage")
    public ShoutResponse shout(ShoutRequest shoutRequest) {
        shoutMessageService.saveShoutMessage(shoutRequest);
        String shoutContent = String.format("%s: %s", shoutRequest.getName(), shoutRequest.getMessage());
        return new ShoutResponse(HtmlUtils.htmlEscape(shoutContent));
    }

    @GetMapping
    public ResponseEntity<List<ShoutMessage>> getAllShoutMessages() {
        return new ResponseEntity<>(shoutMessageService.getAllShoutMessages(), HttpStatus.OK);
    }
}
