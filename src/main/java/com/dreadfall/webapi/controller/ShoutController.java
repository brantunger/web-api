package com.dreadfall.webapi.controller;

import com.dreadfall.webapi.model.ShoutMessage;
import com.dreadfall.webapi.request.ShoutRequest;
import com.dreadfall.webapi.response.ShoutResponse;
import com.dreadfall.webapi.service.ShoutMessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/shoutmessage")
public class ShoutController {

    private final ShoutMessageService shoutMessageService;

    public ShoutController(ShoutMessageService shoutMessageService) {
        this.shoutMessageService = shoutMessageService;
    }

    @MessageMapping("/shout")
    @SendTo("/topic/shoutmessage")
    public ShoutResponse shout(@Valid ShoutRequest shoutRequest) {
        shoutMessageService.saveShoutMessage(shoutRequest);
        String shoutContent = String.format("%s: %s", shoutRequest.getName(), shoutRequest.getMessage());
        return new ShoutResponse(HtmlUtils.htmlEscape(shoutContent));
    }

    @GetMapping
    public @ResponseBody List<ShoutMessage> getAllShoutMessages() {
        return shoutMessageService.getAllShoutMessages();
    }
}
