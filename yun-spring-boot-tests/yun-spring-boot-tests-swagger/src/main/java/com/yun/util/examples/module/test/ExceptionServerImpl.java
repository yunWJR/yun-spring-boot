package com.yun.util.examples.module.test;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

/**
 * @author yun
 * created_time 2020/3/19 16:41.
 */

@Service
public class ExceptionServerImpl {
    private EntityManager entityManager;

    public void runtimeException() {
        throw new RuntimeException("exception test");
    }

}
