package github.zljtt.dahuzhou.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import github.zljtt.dahuzhou.config.DahuzhouApplication;
import github.zljtt.dahuzhou.domain.User;
import github.zljtt.dahuzhou.domain.UserExample;
import github.zljtt.dahuzhou.mapper.UserMapper;
import github.zljtt.dahuzhou.req.UserLoginReq;
import github.zljtt.dahuzhou.req.UserReq;
import github.zljtt.dahuzhou.resp.CommonResp;
import github.zljtt.dahuzhou.resp.FileResp;
import github.zljtt.dahuzhou.resp.UserLoginResp;
import github.zljtt.dahuzhou.util.SnowFlake;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Obtain data from database and provide functionality to return these data
 */
@Service
public class UserService {

    @Value("${path.users}")
    private String userLocation;


//    @Resource
//    private UserMapper userMapper;

//    public CommonResp<User> get(UserReq req) {
//        UserExample userExample = new UserExample();
//        UserExample.Criteria criteria = userExample.createCriteria();
//        if (!ObjectUtils.isEmpty(req.getName())) {
//            criteria.andNameLike("%" + req.getName() + "%");
//        }
//        CommonResp<User> resp = new CommonResp<>();
//        if (userMapper.selectByExample(userExample).size() > 0) {
//            resp.setContent(userMapper.selectByExample(userExample).get(0));
//        } else {
//            resp.setSuccess(false);
//        }
//        return resp;
//    }

    public UserLoginResp login(UserLoginReq request) {
        try {
            File file = new File(userLocation);
            if (file.exists()) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode json = mapper.readTree(new FileReader(userLocation));
                if (json.has(request.getUsername())) {
                    if (json.get(request.getUsername()).asText().equals(request.getPassword())) {
                        return new UserLoginResp().setUsername(request.getUsername()).setMessage("登录成功").setSuccess(true);
                    }
                }
            }
            return new UserLoginResp().setUsername(request.getUsername()).setMessage("用户名或密码不正确").setSuccess(false);
        } catch (IOException e) {
            return new UserLoginResp().setUsername(request.getUsername()).setMessage("服务器错误").setSuccess(false);
        }
    }
}
