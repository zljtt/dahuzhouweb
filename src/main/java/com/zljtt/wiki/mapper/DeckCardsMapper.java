package com.zljtt.wiki.mapper;

import com.zljtt.wiki.domain.DeckCards;
import com.zljtt.wiki.domain.DeckCardsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DeckCardsMapper {
    long countByExample(DeckCardsExample example);

    int deleteByExample(DeckCardsExample example);

    int deleteByPrimaryKey(@Param("deckId") Long deckId, @Param("cardSetCode") String cardSetCode, @Param("cardCollectorNumber") String cardCollectorNumber);

    int insert(DeckCards record);

    int insertSelective(DeckCards record);

    List<DeckCards> selectByExample(DeckCardsExample example);

    DeckCards selectByPrimaryKey(@Param("deckId") Long deckId, @Param("cardSetCode") String cardSetCode, @Param("cardCollectorNumber") String cardCollectorNumber);

    int updateByExampleSelective(@Param("record") DeckCards record, @Param("example") DeckCardsExample example);

    int updateByExample(@Param("record") DeckCards record, @Param("example") DeckCardsExample example);

    int updateByPrimaryKeySelective(DeckCards record);

    int updateByPrimaryKey(DeckCards record);
}