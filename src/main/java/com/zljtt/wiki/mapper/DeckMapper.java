package com.zljtt.wiki.mapper;

import com.zljtt.wiki.domain.Deck;
import com.zljtt.wiki.domain.DeckExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeckMapper {
    long countByExample(DeckExample example);

    int deleteByExample(DeckExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Deck record);

    int insertSelective(Deck record);

    List<Deck> selectByExample(DeckExample example);

    Deck selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Deck record, @Param("example") DeckExample example);

    int updateByExample(@Param("record") Deck record, @Param("example") DeckExample example);

    int updateByPrimaryKeySelective(Deck record);

    int updateByPrimaryKey(Deck record);
}