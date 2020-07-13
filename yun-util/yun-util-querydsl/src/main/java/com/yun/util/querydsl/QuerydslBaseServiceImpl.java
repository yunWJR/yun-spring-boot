package com.yun.util.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yun.util.sb.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * @author: yun
 * @createdOn: 2019/9/7 22:51.
 */

public class QuerydslBaseServiceImpl extends BaseServiceImpl {
    public JPAQueryFactory queryFactory;

    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void postInit() {
        queryFactory = new JPAQueryFactory(entityManager);
    }
}
