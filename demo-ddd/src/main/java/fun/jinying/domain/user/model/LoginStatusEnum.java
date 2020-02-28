package fun.jinying.domain.user.model;

import fun.jinying.domain.shard.model.Vo;

/**
 * @description: 登录状态
 * @author: sjy
 * @create: 2020-02-26 22:14
 **/
public enum LoginStatusEnum implements Vo {
    /**
     * 成功
     */
    SUCCESS,
    /**
     * 失败
     */
    FAIL;
}
