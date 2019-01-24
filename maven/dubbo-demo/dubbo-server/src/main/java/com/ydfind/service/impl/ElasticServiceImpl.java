package com.ydfind.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ydfind.service.ElasticService;
/**
 * 
 * @author Yudi
 *
 */
public class ElasticServiceImpl implements ElasticService {

    private static final Logger logger = LoggerFactory.getLogger(ElasticServiceImpl.class);

    public String helloDubbo(String msg) {
    	logger.info("服务器收到:" + msg);
        return "hi!" + msg;
    }
}