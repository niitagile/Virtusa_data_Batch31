package star.astro.chat.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import star.astro.chat.service.TimeService;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping("/time")
    @CrossOrigin(origins="*", allowedHeaders="*")
    public JSONObject getTime() {
        JSONObject ret = new JSONObject();
        JSONObject UTCTime = timeService.getTime();
        ret.put("UTCTime", UTCTime);
        return ret;
    }

}
