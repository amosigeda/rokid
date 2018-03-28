package com.bracelet.controller;

import com.bracelet.dto.HttpBaseDto;
import com.bracelet.dto.LatestVoltageDto;
import com.bracelet.entity.Voltage;
import com.bracelet.exception.BizException;
import com.bracelet.service.IVoltageService;
import com.bracelet.util.RespCode;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
   
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public HttpBaseDto getLatestVoltage(@RequestBody String msg) {
       
    	
    	logger.info(msg);
        HttpBaseDto dto = new HttpBaseDto();
       
        return dto;
    }
  
}
