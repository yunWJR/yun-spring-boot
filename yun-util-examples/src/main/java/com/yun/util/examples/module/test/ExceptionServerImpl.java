package com.yun.util.examples.module.test;

import com.yun.util.sb.rsp.RspDataException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * @author: yun
 * @createdOn: 2020/3/19 16:41.
 */

@Service
public class ExceptionServerImpl {
    private EntityManager entityManager;

    public void runtimeException() {
        throw new RuntimeException("exception test");
    }

    public void rspDataException() {
        throw new RspDataException(-1, "dasd");
    }
}
