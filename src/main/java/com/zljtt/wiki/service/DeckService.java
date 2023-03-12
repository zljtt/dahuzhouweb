package com.zljtt.wiki.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.zljtt.wiki.domain.Deck;
import com.zljtt.wiki.domain.DeckExample;
import com.zljtt.wiki.mapper.DeckMapper;
import com.zljtt.wiki.req.DeckReq;
import com.zljtt.wiki.resp.DeckResp;
import com.zljtt.wiki.util.CopyUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.lang.model.type.DeclaredType;
import javax.naming.Name;
import javax.security.auth.login.AccountExpiredException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeckService {

    @Resource
    private DeckMapper deckMapper;

    public List<DeckResp> list(DeckReq req) {
        DeckExample deckExample = new DeckExample();
        DeckExample.Criteria criteria = deckExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        return CopyUtil.copyList(deckMapper.selectByExample(deckExample), DeckResp.class);
    }
}
