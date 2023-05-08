package github.zljtt.dahuzhou.service;

import github.zljtt.dahuzhou.domain.User;
import github.zljtt.dahuzhou.domain.UserExample;
import github.zljtt.dahuzhou.mapper.UserMapper;
import github.zljtt.dahuzhou.req.UserReq;
import github.zljtt.dahuzhou.resp.CommonResp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;


/**
 * Obtain data from database and provide functionality to return these data
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public CommonResp<User> get(UserReq req) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        CommonResp<User> resp = new CommonResp<>();
        if (userMapper.selectByExample(userExample).size() > 0) {
            resp.setContent(userMapper.selectByExample(userExample).get(0));
        } else {
            resp.setSuccess(false);
        }
        return resp;
    }
}
