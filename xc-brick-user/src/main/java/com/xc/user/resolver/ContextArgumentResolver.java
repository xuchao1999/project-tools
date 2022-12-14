package com.xc.user.resolver;

import com.xc.core.base.R;
import com.xc.core.contstants.BaseContextHandler;
import com.xc.user.annotation.LoginUser;
import com.xc.user.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 描述： 获取ThreadLocal中的用户信息
 *
 * @author xc
 * @date 20220826 15:36:39
 * @since v1.0
 */
@Slf4j
public class ContextArgumentResolver implements HandlerMethodArgumentResolver {


    /**
     * Instantiates a new Context argument resolver.
     */
    public ContextArgumentResolver() {
    }
    /**
     * 入参筛选
     *
     * @param mp 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter mp) {
        return mp.hasParameterAnnotation(LoginUser.class) && mp.getParameterType().equals(SysUser.class);
    }

    /**
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        Long userId = BaseContextHandler.getUserId();
        String account = BaseContextHandler.getAccount();
        String name = BaseContextHandler.getName();
        Long orgId = BaseContextHandler.getOrgId();
        Long stationId = BaseContextHandler.getStationId();

        //以下代码为 根据 @LoginUser 注解来注入 SysUser 对象
        SysUser user = SysUser.builder()
                .id(userId)
                .account(account)
                .name(name)
                .orgId(orgId)
                .stationId(stationId)
                .build();

        LoginUser loginUser = methodParameter.getParameterAnnotation(LoginUser.class);
        if(null != loginUser){
            return user;
        }
        return null;
//        try {
//            LoginUser loginUser = methodParameter.getParameterAnnotation(LoginUser.class);
//            boolean isFull = loginUser.isFull();
//
//            if (isFull || loginUser.isStation() || loginUser.isOrg() || loginUser.isRoles()) {
//                R<SysUser> result = userResolveApi.getById(NumberHelper.longValueOf0(userId),
//                        UserQuery.builder()
//                                .full(isFull)
//                                .org(loginUser.isOrg())
//                                .station(loginUser.isStation())
//                                .roles(loginUser.isRoles())
//                                .build());
//                if (result.getIsSuccess() && result.getData() != null) {
//                    return result.getData();
//                }
//            }
//        } catch (Exception e) {
//            log.warn("注入登录人信息时，发生异常. --> {}", user, e);
//        }
//        return user;
    }
}
