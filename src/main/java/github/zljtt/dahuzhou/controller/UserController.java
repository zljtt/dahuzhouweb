package github.zljtt.dahuzhou.controller;

import github.zljtt.dahuzhou.config.DahuzhouApplication;
import github.zljtt.dahuzhou.domain.User;
import github.zljtt.dahuzhou.req.UserLoginReq;
import github.zljtt.dahuzhou.req.UserReq;
import github.zljtt.dahuzhou.resp.CommonResp;
import github.zljtt.dahuzhou.resp.UserLoginResp;
import github.zljtt.dahuzhou.service.UserService;
import github.zljtt.dahuzhou.util.SnowFlake;
import jakarta.annotation.Resource;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Expose the website
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private SnowFlake snowFlake;


    @PostMapping("/login")
    public ResponseEntity<UserLoginResp> login(@RequestBody UserLoginReq request) {
        UserLoginResp resp = userService.login(request);
        String token = Long.toString(snowFlake.nextId());
        if (resp.isSuccess()) {
            DahuzhouApplication.tokenMap.put(token, new UserToken(request.getUsername(), LocalDateTime.now(), resp.isSuccess()));
            DahuzhouApplication.LOG.info("user " + request.getUsername() + " login " + resp.isSuccess() + " with token " + token);
        }
        return ResponseEntity.ok(resp.setToken(token));
    }

    public static class UserToken {

        String user = "";
        LocalDateTime created;
        boolean login = false;

        public UserToken() {
        }

        public UserToken(String user, LocalDateTime created, boolean login) {
            this.user = user;
            this.created = created;
            this.login = login;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public LocalDateTime getCreated() {
            return created;
        }

        public void setCreated(LocalDateTime created) {
            this.created = created;
        }

        public boolean isLogin() {
            return login;
        }

        public void setLogin(boolean login) {
            this.login = login;
        }
    }

}
