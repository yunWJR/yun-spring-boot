package com.yun.util.jpa.repository;

import com.yun.util.common.JrpUtil;
import com.yun.util.common.StringUtil;
import com.yun.util.module.rsp.RspData;
import com.yun.util.module.rsp.RspDataException;
import org.springframework.stereotype.Component;

/**
 * The itemType Repository helper.
 * @param <T> the itemType parameter
 * @author: yun
 * @createdOn: 2018 /5/30 09:44.
 */
@Component
public class RepositoryHelper<T> {

    // region --Field

    private String entityName = "对象";

    // endregion

    // region --Constructor

    public RepositoryHelper() {
    }

    public RepositoryHelper(String entityName) {
        this.entityName = entityName;
    }

    // endregion

    // region --static method

    public String checkValidUuid(String id) {
        if (StringUtil.isNullOrEmpty(id)) {
            return "id无效(1)";
        }

        if (id.length() != 32 && id.length() != 36) {
            // 长度不对
            return "id无效(2)";
        }

        return null;
    }

    public boolean isValidUuid(String id) {
        String err = checkValidUuid(id);

        return err == null;
    }

    // endregion

    // region --Getter and Setter

    /**
     * Gets entity name.
     * @return the entity name
     */
    public String getEntityName() {
        return entityName;
    }

    /**
     * Sets entity name.
     * @param entityName the entity name
     */
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    // endregion

    // region --Public method

    /**
     * Find by pkId base rst bean.
     * @param jpa the jpa
     * @param id  the pkId
     * @return the base rst bean
     */
    public RspData findById(BaseJpaRepositoryByUuid<T> jpa, String id) {
        if (jpa == null) {
            return RspData.ComErrBean("无法获取Rp对象");
        }

        String err = checkValidUuid(id);
        if (err != null) {
            return RspData.ComErrBean(err);
        }

        T item = JrpUtil.findById(jpa, id);
        if (item != null) {
            return RspData.SurBean(item);
        } else {
            return RspData.ComErrBean(String.format("未找到%s", entityName));
        }
    }

    public T findByGlId(BaseJpaRepositoryByAutoId<T> jpa, Long id) {
        if (jpa == null) {
            throw RspDataException.RstComErrBeanWithStr("无法获取Rp对象");
        }

        if (id == null) {
            throw RspDataException.RstComErrBeanWithStr("id无效");
        }

        T item = JrpUtil.findById(jpa, id);
        if (item != null) {
            return item;
        } else {
            throw RspDataException.RstComErrBeanWithStr(String.format("未找到%s", entityName));
        }
    }

    // endregion

    // region --private method

    // endregion

    // region --Other

    // endregion
}
