package com.liyv.ssm.service;

import com.liyv.ssm.BaseTest;
import com.liyv.ssm.dto.Exposer;
import com.liyv.ssm.dto.SeckillExecution;
import com.liyv.ssm.entity.Seckill;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SeckillServiceTest extends BaseTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);
    }

    @Test
    public void testGetById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("expose={}", exposer);
        /**
         *  expose=Exposer{exposed=true, md5='20104a9b3c667f3899cd1f342af78957', seckillId=1000, now=0, start=0, end=0}
         */
    }

    @Test
    public void testExecuteSeckill() throws Exception {
        long id = 1000;
        long phone = 13035127351L;
        String md5 = "20104a9b3c667f3899cd1f342af78957";
        SeckillExecution execution = seckillService.executeSecKill(id, phone, md5);
        logger.info("result={}", execution);

    }
}