package com.example.mallxx.Service;

import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.Random;

@Service
public class RandomNumberService {
    /**
     * 根据当前时间戳和随机数生成一个int类型的随机数.
     *
     * @return 生成的随机整数.
     */
    public int generateRandomInt() {
        // 获取当前时间的时间戳（秒）
        long currentTimeStamp = Instant.now().getEpochSecond();
        // 创建一个Random实例
        Random random = new Random();
        // 使用时间戳作为随机数种子
        random.setSeed(currentTimeStamp);
        // 生成一个随机整数
        return random.nextInt(Integer.MAX_VALUE);
    }
}
