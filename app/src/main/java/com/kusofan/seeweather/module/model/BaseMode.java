package com.kusofan.seeweather.module.model;

/**
 * Created by heming on 2018/1/19.
 */

class BaseMode {
    /**
     * ok	数据正常
     * invalid key	错误的key，请检查你的key是否输入以及是否输入有误
     * unknown location	未知或错误城市/地区
     * no data for this location	该城市/地区没有你所请求的数据
     * no more requests	超过访问次数，需要等到当月最后一天24点后进行访问次数的重置或升级你的访问量
     * param invalid	参数错误，请检查你传递的参数是否正确
     * too fast	超过限定的QPM，请参考QPM说明
     * dead	无响应或超时，接口服务异常请联系我们
     * permission denied	无访问权限，你没有购买你所访问的这部分服务
     * sign error	签名错误，请参考签名算法
     */
    public String status;
}
