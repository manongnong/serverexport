package com.dswdemo.serverexport.app1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
@RestController
public class SpringBootServerExportApp1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootServerExportApp1Application.class, args);
    }


    @GetMapping("/echo")
    public String echo() {
        return "echo";
    }

    /**
     * 导出数据例子
     */
    @GetMapping("/exportData")
    public List<AppInfo> exportData() {
        List<AppInfo> appInfos = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            AppInfo appInfo = new AppInfo();
            appInfo.setName("name"+i);
            appInfo.setDescription("des"+i);
            appInfos.add(appInfo);
        }
        return appInfos;
    }

    public class AppInfo {

        private String name;
        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}
