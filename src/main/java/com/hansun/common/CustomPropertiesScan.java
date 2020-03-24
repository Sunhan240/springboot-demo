/*
 * Copyright (C),sunhan240@163.com
 * Author:   sunhan
 * Date:     2020/3/17 4:01 PM
 * History:
 * <author>        <time>          <version>        <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.hansun.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 〈一句话功能简述〉<br>
 * TODO(自定义property加入spring扫描)
 *
 * @author sunhan
 * @version 1.0.0
 * @since 2020/3/17 4:01 PM
 */
@Component
@ConfigurationProperties()
@PropertySource(value = {"classpath:config/other.properties","classpath:config/db.properties"})
public class CustomPropertiesScan {

}
