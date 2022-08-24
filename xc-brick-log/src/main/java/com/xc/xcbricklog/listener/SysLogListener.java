package com.xc.xcbricklog.listener;

import com.xc.xcbricklog.dto.OptLogDTO;
import com.xc.xcbricklog.event.SysLogEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import java.util.function.Consumer;

/**
 * @ClassName: SysLogListener
 * @description: 异步监听系统日志
 * @Author: Chao Xu
 * @Date: 2022/8/20 20:45
 **/
@Slf4j
@AllArgsConstructor
public class SysLogListener {

    private Consumer<OptLogDTO> consumer;

    @Async//异步处理
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        consumer.accept((OptLogDTO) event.getSource());
    }
}
