package org.linlinjava.litemall.admin.web;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VEvent;
import biweekly.property.Summary;
import com.github.binarywang.utils.qrcode.QrcodeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.storage.StorageService;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallStorage;
import org.linlinjava.litemall.db.service.LitemallStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/timetables")
@Validated
public class TimeTablesController {
    private final Log logger = LogFactory.getLog(TimeTablesController.class);

    @Autowired
    private StorageService storageService;

    @PostMapping("/create")
    public Object create(@RequestParam("file") MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        LitemallStorage litemallStorage = storageService.store(file.getInputStream(), file.getSize(), file.getContentType(), originalFilename);
        return ResponseUtil.ok(litemallStorage);
    }


    @PostMapping("/ics")
    public Object test(@RequestBody List<TaskItem> taskItemList){
        ICalendar ical = new ICalendar();
        for (int i = 0; i < taskItemList.size(); i++) {
            TaskItem taskItem = taskItemList.get(i);
            VEvent event = new VEvent();
            Summary summary = event.setSummary(taskItem.getClassName());
            summary.setLanguage("zh-CN");
            event.setDateStart(taskItem.getStartTime());
            event.setDateEnd(taskItem.getEndTime());
            event.setLocation(taskItem.getClassRoom());
            event.setDescription(taskItem.getCourse());
            ical.addEvent(event);
        }

        String icalString = Biweekly.write(ical).go();

        String url = storageService.store(new ByteArrayInputStream(icalString.getBytes()), "timetalebs.ics");


        byte[] bytes = QrcodeUtils.createQrcode(url, 800, null);

        String qr_url = storageService.store(new ByteArrayInputStream(bytes),0,"image/jpeg", "qr_code.jpg").getUrl();

        return ResponseUtil.ok(qr_url);
    }


}

class TaskItem {

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String className;

    private String course;

    private Date endTime;

    private Date startTime;

    private String classRoom;

    public TaskItem() {
    }

    public TaskItem(String className, String course, Date endTime, Date startTime, String classRoom) {
        this.className = className;
        this.course = course;
        this.endTime = endTime;
        this.startTime = startTime;
        this.classRoom = classRoom;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) throws ParseException {
        this.endTime = format.parse(endTime);
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) throws ParseException {
        this.startTime = format.parse(startTime);
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}
