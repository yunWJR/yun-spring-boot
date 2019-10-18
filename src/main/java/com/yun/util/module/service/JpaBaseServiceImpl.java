package com.yun.util.module.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * @author: yun
 * @createdOn: 2019/9/7 22:34.
 */

public class JpaBaseServiceImpl extends BaseServiceImpl {
    public JPAQueryFactory queryFactory;
    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void postInit() {
        queryFactory = new JPAQueryFactory(entityManager);
    }
}
