package com.zljtt.wiki.controller;

import com.zljtt.wiki.domain.Deck;
import com.zljtt.wiki.req.DeckReq;
import com.zljtt.wiki.resp.CommonResp;
import com.zljtt.wiki.resp.DeckResp;
import com.zljtt.wiki.service.DeckService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deck")
public class DeckController {

    @Resource
    private DeckService deckService;

    @GetMapping("/list")
    public List<DeckResp> list(DeckReq req) {
        return deckService.list(req);
    }

    @GetMapping()
    public DeckResp deck(DeckReq req) {
        List<DeckResp> list = deckService.list(req);
        if (list.size() == 0) {
            return null;
        }
        return list.get(0);
    }
}
