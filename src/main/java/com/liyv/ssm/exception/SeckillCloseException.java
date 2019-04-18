package com.liyv.ssm.exception;

import com.liyv.ssm.dto.SeckillExecution;

/**
 * 秒杀关闭异常,
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
