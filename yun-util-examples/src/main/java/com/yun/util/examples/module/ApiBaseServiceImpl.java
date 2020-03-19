package com.yun.util.examples.module;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.yun.util.auth.AuthDtoUtil;
import com.yun.util.module.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

/**
 * @author: yun
 * @createdOn: 2019/9/7 22:51.
 */

public class ApiBaseServiceImpl extends BaseServiceImpl {
    public JPAQueryFactory queryFactory;
    @Autowired
    private EntityManager entityManager;

    @PostConstruct
    public void postInit() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    public void saveDeviceType(Integer type) {
        AuthDtoUtil.saveDeviceDto(type);
    }

    public Integer getDeviceType() {
        Integer type = (Integer) AuthDtoUtil.getDeviceDto();

        return type;
    }
}
