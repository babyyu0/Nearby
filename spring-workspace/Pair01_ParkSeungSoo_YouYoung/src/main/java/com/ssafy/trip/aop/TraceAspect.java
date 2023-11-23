package com.ssafy.trip.aop;

import java.text.NumberFormat;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class TraceAspect {
    @Pointcut("@annotation(com.ssafy.trip.aop.TimeTrace)")
    private void timeTracePointcut() {
    }
    @Pointcut("@annotation(com.ssafy.trip.aop.MemoryTrace)")
    private void memoryTracePointcut() {
    }

    @Around("timeTracePointcut()")
    public Object traceTime(ProceedingJoinPoint joinPoint) throws Throwable {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            return joinPoint.proceed(); // 실제 타겟 호출
        } finally {
            stopWatch.stop();
            log.debug("{} Total time = {}s",
                    joinPoint.getSignature().toShortString(),
                    stopWatch.getTotalTimeSeconds());
        }
    }
    @AfterReturning(value = "memoryTracePointcut()", returning = "result")
    public void logMemoryUsage(JoinPoint joinPoint, Object result) {
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();

        log.debug("Used Memory: " + (usedMemory/ 1024) + " kb");
    }
}
