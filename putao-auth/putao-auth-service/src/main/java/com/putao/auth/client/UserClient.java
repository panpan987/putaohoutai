package com.putao.auth.client;

import com.putao.user.api.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @version 1.0
 * @author: panpan
 * @create: 2020-08-26 01:59
 **/
@FeignClient("user-service")
public interface UserClient extends UserApi {};

