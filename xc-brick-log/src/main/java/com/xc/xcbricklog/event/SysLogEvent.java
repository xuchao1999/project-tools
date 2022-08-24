package com.xc.xcbricklog.event;

import com.xc.xcbricklog.dto.OptLogDTO;
import org.springframework.context.ApplicationEvent;

/**
 * @ClassName: SysLogEvent
 * @description: 系统日志事件
 * @Author: Chao Xu
 * @Date: 2022/8/20 20:43
 **/
public class SysLogEvent extends ApplicationEvent {
    public SysLogEvent(OptLogDTO optLogDTO) {
        super(optLogDTO);
    }
}
