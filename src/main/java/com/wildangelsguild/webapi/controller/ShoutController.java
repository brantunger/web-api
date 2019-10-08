package com.wildangelsguild.webapi.controller;

import com.wildangelsguild.webapi.request.ShoutRequest;
import com.wildangelsguild.webapi.response.ShoutResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ShoutController {
    @MessageMapping("/shout")
    @SendTo("/topic/shoutmessage")
    public ShoutResponse shout(ShoutRequest shoutRequest) {
        String shoutContent = String.format("%s: %s", shoutRequest.getName(), shoutRequest.getMessage());
        return new ShoutResponse(HtmlUtils.htmlEscape(shoutContent));
    }
}
