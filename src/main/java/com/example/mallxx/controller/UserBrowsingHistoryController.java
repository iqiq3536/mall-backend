package com.example.mallxx.controller;


import com.example.mallxx.entity.UserBrowsingHistory;
import com.example.mallxx.mapper.UserBrowsingHistoryMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/api/history")
public class UserBrowsingHistoryController {
    private final UserBrowsingHistoryMapper UserBrowsingHistoryMapper;
    public UserBrowsingHistoryController(UserBrowsingHistoryMapper UserBrowsingHistoryMapper) {
        this.UserBrowsingHistoryMapper = UserBrowsingHistoryMapper;
    }

    @PostMapping("/findHisById") // 使用POST方法
    public ResponseEntity<UserBrowsingHistory> findHisById(@RequestBody UserBrowsingHistory request) {
        //System.out.println(request.getHistory_Id()+"**********-------------------------");
        UserBrowsingHistory his = UserBrowsingHistoryMapper.getById(request.getHistory_Id()); // 使用传入的userId参数查找用户
        if (his == null) {
            return ResponseEntity.notFound().build();
        }
        //System.out.println(request.getHistory_Id()+"**********-------------------------");
        //System.out.println(his.getHistory_Id()+"**********-------------------------");
        return ResponseEntity.ok(his);
    }

    @PostMapping("/findHisByUserId") // 使用POST方法
    public ResponseEntity<List<UserBrowsingHistory>> getByUserId(@RequestBody UserBrowsingHistory request) {
        List<UserBrowsingHistory> his = UserBrowsingHistoryMapper.getByUserId(request.getUser_id());
        if (his.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(his);
    }

    @PostMapping("/addHistory") // 使用POST方法
    public ResponseEntity<Boolean> addHistory(@RequestBody UserBrowsingHistory request) {
        UserBrowsingHistoryMapper.addHistory(request);
        if (!UserBrowsingHistoryMapper.addHistory(request)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
        return ResponseEntity.ok(true);
    }
    @PostMapping("/deleteById")
    public Boolean deleteById(@RequestBody UserBrowsingHistory request) {
        return UserBrowsingHistoryMapper.deleteById(request.getHistory_Id());
    }
}
