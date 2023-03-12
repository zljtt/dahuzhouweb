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
    public CommonResp<List<DeckResp>> list(DeckReq req) {
        CommonResp<List<DeckResp>> resp = new CommonResp<>();
        resp.setContent(deckService.list(req));
        return resp;
    }
}
