package com.yun.util.examples.util;

/**
 * @author: yun
 * @createdOn: 2019/11/8 14:21.
 */

import com.yun.util.module.rsp.RspDataException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class JrpUtil {

    public static <T1, T2> T1 findById(JpaRepository<T1, T2> jrp, T2 itemId) {
        return findById(jrp, itemId, null);
    }

    public static <T1, T2> T1 findById(JpaRepository<T1, T2> jrp, T2 itemId, String err) {
        if (itemId == null) {
            if (err != null) {
                throw RspDataException.RstComErrBeanWithStr(err);
            }

            return null;
        }

        Optional<T1> optItem = jrp.findById(itemId);
        if (optItem.isPresent()) {
            return optItem.get();
        }

        if (err != null) {
            throw RspDataException.RstComErrBeanWithStr(err);
        }

        return null;
    }

    public static Sort createTimeDescSort() {
        Sort sort = Sort.by(Sort.Direction.DESC, "messageCreateTime");

        return sort;
    }

    // public static <T1> T1 findById(JpaRepository<T1, Long> jrp, Long itemId) {
    //     return findById(jrp, itemId);
    // }
}
