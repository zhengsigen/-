package com.c1801.spring.dzy.config;

import com.c1801.spring.dzy.model.AuthorizedValids;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信授权存储中心
 */
@Data
@Configuration
public class WXAuthorizedValids {

    private Map<String, AuthorizedValids> wxAuthorizedValids = new HashMap<>();


}
