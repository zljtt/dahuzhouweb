package github.zljtt.dahuzhou.controller;

import github.zljtt.dahuzhou.config.DahuzhouApplication;
import github.zljtt.dahuzhou.domain.User;
import github.zljtt.dahuzhou.req.UserReq;
import github.zljtt.dahuzhou.resp.CommonResp;
import github.zljtt.dahuzhou.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

/**
 * Expose the website
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping()
    public CommonResp<User> get(UserReq req) {
        DahuzhouApplication.LOG.info("read " + req.toString());
        return userService.get(req);
    }

}
