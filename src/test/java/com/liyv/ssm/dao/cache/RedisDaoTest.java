package com.liyv.ssm.dao.cache;

import com.liyv.ssm.BaseTest;
import com.liyv.ssm.dao.SeckillDao;
import com.liyv.ssm.entity.Seckill;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class RedisDaoTest extends BaseTest {

    private long id = 1001;
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private RedisDao redisDao;

    @Test
    public void testSeckillCache() throws Exception {
        //get and set
        Seckill seckill = redisDao.getSeckill(id);
        if (null == seckill) {
            seckill = seckillDao.queryById(id);
            if (null != seckill) {
                String result = redisDao.putSeckill(seckill);
                System.out.println(result);
                seckill = redisDao.getSeckill(id);
                System.out.println(seckill);
            }
        }
    }
}