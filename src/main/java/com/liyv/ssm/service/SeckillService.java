package com.liyv.ssm.service;

import com.liyv.ssm.dto.Exposer;
import com.liyv.ssm.dto.SeckillExecution;
import com.liyv.ssm.entity.Seckill;
import com.liyv.ssm.exception.RepeatKillException;
import com.liyv.ssm.exception.SeckillCloseException;
import com.liyv.ssm.exception.SeckillException;

import java.util.List;

/**
 * 业务接口
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     *
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址，否则输出系统时间和秒杀时间
     *
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSecKill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;
}
