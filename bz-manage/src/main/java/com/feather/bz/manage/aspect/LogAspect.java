package com.feather.bz.manage.aspect;

import com.feather.bz.common.exception.BaseBizException;
import com.feather.bz.common.utils.HttpContextUtils;
import com.feather.bz.common.utils.IpUtil;
import com.feather.bz.manage.annoation.Log;
import com.feather.bz.manage.domain.SysLog;
import com.feather.bz.manage.domain.SysUser;
import com.feather.bz.manage.service.ISysLogService;
import com.feather.bz.manage.support.UserSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author feather
 * @projectName dev-common
 * @description: TODO
 * @since 18-Jul-22 4:07 PM
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Aspect
@Component
public class LogAspect {


    private  final ISysLogService sysLogService;

    private  final UserSupport userSupport;

    @Pointcut("@annotation(com.feather.bz.manage.annoation.Log)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public void around(ProceedingJoinPoint point) {
        long beginTime = System.currentTimeMillis();
        try {
            // 执行方法
            point.proceed();
        } catch (Throwable e) {
           if (( e instanceof BaseBizException)){
               throw  new BaseBizException(((BaseBizException) e).getErrorCode(), ((BaseBizException) e).getErrorMsg());
           }
          log.error("[切面执行方法异常:{}]",e.getMessage());

        }
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveLog(point, time);
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = new SysLog();
        sysLog.setTime((int) time);
        Log logAnnotation = method.getAnnotation(Log.class);
        if (logAnnotation != null) {
            // 注解上的描述
            sysLog.setOperation(logAnnotation.description());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的方法参数值
        Object[] args = joinPoint.getArgs();
        // 请求的方法参数名称
        LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = u.getParameterNames(method);
        if (args != null && paramNames != null) {
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                params.append(" ").append(paramNames[i] ).append(":").append(args[i]);
            }
            sysLog.setParams(params.toString());
        }
        // 获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        //设置请求地址
        sysLog.setUri(request.getRequestURI());
        //请求的方法类型(post/get)
        sysLog.setMethodType(request.getMethod());
        // 设置IP地址
        sysLog.setIp(IpUtil.getIpAddr(request));
        sysLog.setContentType(request.getContentType());
       SysUser sysUser = userSupport.getCurrentUser();
       if (!Objects.isNull(sysUser)){
           sysLog.setUser(sysUser.getUsername());
       }
        // 保存系统日志
        sysLogService.save(sysLog);
    }
}
