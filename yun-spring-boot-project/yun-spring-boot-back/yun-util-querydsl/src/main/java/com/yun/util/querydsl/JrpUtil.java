package com.yun.util.querydsl;

/**
 * @author yun
 * created_time 2019/11/8 14:21.
 */

import com.yun.util.common.CommonException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class JrpUtil {
    public static <T1, T2> T1 findById(JpaRepository<T1, T2> jrp, T2 itemId) {
        return findById(jrp, itemId, null);
    }

    public static <T1, T2> T1 findById(JpaRepository<T1, T2> jrp, T2 itemId, String err) {
        if (itemId == null) {
            if (err != null) {
                throw CommonException.comEp(err);
            }

            return null;
        }

        Optional<T1> optItem = jrp.findById(itemId);
        if (optItem.isPresent()) {
            return optItem.get();
        }

        if (err != null) {
            throw CommonException.comEp(err);
        }

        return null;
    }
}
