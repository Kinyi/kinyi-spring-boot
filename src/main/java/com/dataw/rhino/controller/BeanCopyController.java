package com.dataw.rhino.controller;

import com.dataw.rhino.bean.PeopleConverter;
import com.dataw.rhino.demo.Hobby;
import com.dataw.rhino.demo.People;
import com.dataw.rhino.demo.PeopleDTO;
import com.dataw.rhino.serivce.MyService;
import com.sensorsdata.analytics.javasdk.SensorsAnalytics;
import com.sensorsdata.analytics.javasdk.exceptions.InvalidArgumentException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Kinyi_Chan
 * @since 2020-11-20
 */
@RestController
@RequestMapping
public class BeanCopyController {

    private String name;

    @Autowired
    private PeopleConverter peopleConverter;

    @Autowired
    private MyService myService;

    @GetMapping("random")
    public String random() {
        return myService.random();
    }

    @GetMapping("/testBeanCopy")
    public void testBeanCopy() {
        People people = new People();
        people.setName("allen");
        people.setAge(12);
        people.setAddress("guanghzou");
        people.setBirth(new Date());
        people.setHobbyList(Collections.singletonList(new Hobby("basketball", 100, 0)));

        People people1 = new People();
        people1.setName("allen");
        people1.setAge(12);
        people1.setAddress("guanghzou");
        people1.setBirth(new Date());
        people1.setHobbyList(Collections.singletonList(new Hobby("basketball", 100, 0)));

        People people2 = new People();
        people2.setName("allen");
        people2.setAge(12);
        people2.setAddress("guanghzou");
        people2.setBirth(new Date());
        people2.setHobbyList(Collections.singletonList(new Hobby("basketball", 100, 0)));

        List<People> list = new ArrayList<>();
        list.add(people1);
        list.add(people2);

        PeopleDTO peopleDTO = peopleConverter.domain2dto(people);
        List<PeopleDTO> peopleDTOS = peopleConverter.domain2dto(list);
        System.out.println(peopleDTO);
        System.out.println(peopleDTOS);
    }


    @ApiOperation(value = "修改状态")
    @GetMapping("/updatestatus")
    public String updateStatus(@RequestParam(value = "id") Long id, @RequestParam(value = "status") String status, @RequestParam(value = "force") Boolean force) {
        return "hello";
    }

    @ApiOperation(value = "参数测试")
    @GetMapping("/test")
    public String test(@RequestParam String input) {
        try {
            final SensorsAnalytics sa = new SensorsAnalytics(new SensorsAnalytics.ConcurrentLoggingConsumer("您的日志文件路径"));

            // 用户的 Distinct ID
            String distinctId = "ABCDEF123456789";

            // 记录用户登录事件
            sa.track(distinctId, true, "UserLogin");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }

        return input;
    }

    @ApiOperation(value = "修改状态")
    @GetMapping("/test/thread/safe")
    public String safe() {
        String copy = name;
        System.out.println(Thread.currentThread().getName() + name);
        return "hello";
    }

    @ApiOperation(value = "setname")
    @GetMapping("/test/thread/setname")
    public String setNames(String name) {
        setName(name);
//        System.out.println(Thread.currentThread().getName() + copy);
        return "hello";
    }

    @ApiOperation(value = "测试入参实体类")
    @PutMapping("/test/body")
    public String setNames(@RequestBody User user) {
        return "hello";
    }

    public void setName(String name) {
        this.name = name;
    }

    static class User {
        private String xml;

        public String getXml() {
            return xml;
        }

        public void setXml(String xml) {
            this.xml = xml;
        }
    }
}
