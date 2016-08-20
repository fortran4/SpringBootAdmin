package com.zcoder.admin.modules.core.message;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author: lin
 * @Date: 2016-07-26 Time: 13:21
 * @description: 消息接口
 *               定义控制器输出到页面的消息
 */
public interface Msg<T> {


    /**
     * 返回自定义类型消息
     * @param model
     * @param type @link{com.zcoder.admin.modules.core.common.constant.Contants}
     * @param content 消息内容
     */
    void rtnMessage(Model model, String type, String... content);


    /**
     * 返回自定义类型flash消息
     * @param redirectAttributes
     * @param type @link{com.zcoder.admin.modules.core.common.constant.Contants}
     * @param content 消息内容
     */
    void rtnMessage(RedirectAttributes redirectAttributes, String type, String... content);

    /**
     * 自定义消息
     * @param code 自定义code @link{ com.zcoder.admin.modules.core.message.RespMsgStatus}
     * @param msg 自定义提示语
     * @param data 返回的数据集合
     * @return
     */
    RespMsg<T> rtnMessage(int code,String msg,T data);


    //--------------------------异步操作-------------------------------


    /**
     * 操作成功
     * @param content 自定义提示语
     * @param data 返回的数据集合
     * @return
     */
    RespMsg<T> ok(String content,T data);

    /**
     * 操作成功
     * @param data 返回的数据集合
     * @return
     */
    RespMsg<T> ok(T data);

    /**
     * 操作失败
     * @param content 自定义提示语
     * @param data 返回的数据集合
     * @return
     */
    RespMsg<T> error(String content,T data);

    /**
     * 操作失败
     * @param data 返回的数据集合
     * @return
     */
    RespMsg<T> error(T data);



    //--------------------------异步操作-------------------------------




}
