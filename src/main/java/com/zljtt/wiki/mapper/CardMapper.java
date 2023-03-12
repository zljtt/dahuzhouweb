package com.zljtt.wiki.mapper;

import com.zljtt.wiki.domain.Card;
import com.zljtt.wiki.domain.CardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardMapper {
    long countByExample(CardExample example);

    int deleteByExample(CardExample example);

    int deleteByPrimaryKey(@Param("setCode") String setCode, @Param("collectorNumber") String collectorNumber);

    int insert(Card record);

    int insertSelective(Card record);

    List<Card> selectByExample(CardExample example);

    Card selectByPrimaryKey(@Param("setCode") String setCode, @Param("collectorNumber") String collectorNumber);

    int updateByExampleSelective(@Param("record") Card record, @Param("example") CardExample example);

    int updateByExample(@Param("record") Card record, @Param("example") CardExample example);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);
}