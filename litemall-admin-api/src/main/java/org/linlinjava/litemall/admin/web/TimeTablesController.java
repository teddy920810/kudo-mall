package org.linlinjava.litemall.admin.web;

import biweekly.Biweekly;
import biweekly.ICalendar;
import biweekly.component.VAlarm;
import biweekly.component.VEvent;
import biweekly.parameter.Related;
import biweekly.property.Summary;
import biweekly.property.Trigger;
import biweekly.util.Duration;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.binarywang.utils.qrcode.QrcodeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.dto.ExcelModel;
import org.linlinjava.litemall.core.storage.StorageService;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.LitemallNav;
import org.linlinjava.litemall.db.domain.LitemallStorage;
import org.linlinjava.litemall.db.service.LitemallNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/timetables")
@Validated
public class TimeTablesController {
    private final Log logger = LogFactory.getLog(TimeTablesController.class);

    @Autowired
    private StorageService storageService;
    @Autowired
    private LitemallNavService navService;

    @PostMapping("/excel")
    public String create(@RequestParam("file") MultipartFile file) throws JsonProcessingException {

        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        if (!".xls".equals(suffix) && !".xlsx".equals(suffix)) {
            return "fail";
        }
        List<ExcelModel> excelModelList = verfiyExcel(file);
        List<TaskItem> listAll = new ArrayList<>();
        for (int i = 0; i < excelModelList.size(); i++) {
            ExcelModel excelModel = excelModelList.get(i);
            try {
                List<TaskItem> list = parseContent(excelModel.getContent(),excelModel.getClassName(),excelModel.getClassRoom(),excelModel.getCourseName(),30);
                listAll.addAll(list);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        ICalendar ical = new ICalendar();
        for (int i = 0; i < listAll.size(); i++) {
            TaskItem taskItem = listAll.get(i);
            VEvent event = new VEvent();
            Summary summary = event.setSummary(taskItem.getCourse());
            summary.setLanguage("zh-CN");
            event.setDateStart(taskItem.getStartTime());
            event.setDateEnd(taskItem.getEndTime());
            event.setLocation(taskItem.getClassRoom());
            event.setDescription(taskItem.getClassName());

            //audio alarm
            Duration duration = Duration.builder().prior(true).minutes(taskItem.getNotifyMinutes()).build();
            Trigger trigger = new Trigger(duration, Related.START);
            VAlarm alarm = VAlarm.display(trigger, "Coures Notify");

            event.addAlarm(alarm);
            ical.addEvent(event);
        }
        String icalString = Biweekly.write(ical).go();

        LitemallStorage litemallStorage = storageService.store(new ByteArrayInputStream(icalString.getBytes()), "timetalebs.ics");

        ObjectMapper objectMapper = new ObjectMapper();
        LitemallNav nav = navService.findByPid(objectMapper.writeValueAsString(listAll).hashCode());
        if (nav == null){//add
            nav = new LitemallNav();
            nav.setName(litemallStorage.getKey());
            nav.setPid(objectMapper.writeValueAsString(listAll).hashCode());
            nav.setLevel(objectMapper.writeValueAsString(listAll));
            navService.add(nav);
        }else {//update
            storageService.delete(nav.getName());
            nav.setName(litemallStorage.getKey());
            nav.setLevel(objectMapper.writeValueAsString(listAll));
            navService.updateById(nav);
        }

        byte[] bytes = QrcodeUtils.createQrcode(litemallStorage.getUrl(), 800, null);

        BASE64Encoder encoder = new BASE64Encoder();
        String imageBase64 = "data:image/jpg;base64,"+ encoder.encode(bytes);

        //String qr_url = storageService.store(new ByteArrayInputStream(bytes),0,"image/jpeg", "qr_code.jpg").getUrl();
        Map<String, Object> map = new HashMap<>();
        map.put("cede", 0);
        map.put("data",imageBase64);
        map.put("msg","success");
        return imageBase64;
    }


    @PostMapping("/ics")
    public String test(@RequestBody List<TaskItem> taskItemList) throws JsonProcessingException {
        ICalendar ical = new ICalendar();
        for (int i = 0; i < taskItemList.size(); i++) {
            TaskItem taskItem = taskItemList.get(i);
            VEvent event = new VEvent();
            Summary summary = event.setSummary(taskItem.getCourse());
            summary.setLanguage("zh-CN");
            event.setDateStart(taskItem.getStartTime());
            event.setDateEnd(taskItem.getEndTime());
            event.setLocation(taskItem.getClassRoom());
            event.setDescription(taskItem.getClassName());

            //audio alarm
            Duration duration = Duration.builder().prior(true).minutes(taskItem.getNotifyMinutes()).build();
            Trigger trigger = new Trigger(duration, Related.START);
            VAlarm alarm = VAlarm.display(trigger, "Coures Notify");

            event.addAlarm(alarm);
            ical.addEvent(event);
        }
        String icalString = Biweekly.write(ical).go();

        LitemallStorage litemallStorage = storageService.store(new ByteArrayInputStream(icalString.getBytes()), "timetalebs.ics");

        ObjectMapper objectMapper = new ObjectMapper();
        LitemallNav nav = navService.findByPid(objectMapper.writeValueAsString(taskItemList).hashCode());
        if (nav == null){//add
            nav = new LitemallNav();
            nav.setName(litemallStorage.getKey());
            nav.setPid(objectMapper.writeValueAsString(taskItemList).hashCode());
            nav.setLevel(objectMapper.writeValueAsString(taskItemList));
            navService.add(nav);
        }else {//update
            storageService.delete(nav.getName());
            nav.setName(litemallStorage.getKey());
            nav.setLevel(objectMapper.writeValueAsString(taskItemList));
            navService.updateById(nav);
        }

        byte[] bytes = QrcodeUtils.createQrcode(litemallStorage.getUrl(), 800, null);

        BASE64Encoder encoder = new BASE64Encoder();
        String imageBase64 = "data:image/jpg;base64,"+ encoder.encode(bytes);

        //String qr_url = storageService.store(new ByteArrayInputStream(bytes),0,"image/jpeg", "qr_code.jpg").getUrl();
        Map<String, Object> map = new HashMap<>();
        map.put("cede", 0);
        map.put("data",imageBase64);
        map.put("msg","success");
        return imageBase64;
    }

    private static List<TaskItem> parseContent(String s,String className,String classRoom,String courseName,int delay) throws ParseException {

        List<TaskItem> taskItemList = new ArrayList<>();

        int first = s.indexOf("（");
        if(first==-1){
            first = s.indexOf("(");
        }
        int last = s.lastIndexOf("）");
        if (last==-1){
            last = s.lastIndexOf(")");
        }
        String s1 = s.substring(first+1,last);

        String reg_charset = "[\\u4e00-\\u9fa5]*$";
        Pattern p = Pattern.compile(reg_charset);
        Matcher m = p.matcher(s1);
        StringBuffer time = new StringBuffer();

        int f2=0;
        while (m.find()) {
            if(!"".equals(m.group())){
                time.append(m.group());
                f2 = m.start();
            }
        }

        String startTime = "";
        String endTime="";
        switch (time.toString()) {
            case "晚上":
                startTime = "19:00:00";
                endTime = "22:00:00";
                break;
            case "上下午":
                startTime = "9:00:00";
                endTime = "17:00:00";
                break;
            case "下午":
                startTime = "14:00:00";
                endTime = "17:00:00";
                break;
            case "上午":
                startTime = "9:00:00";
                endTime = "12:00:00";
        }

        Calendar date = Calendar.getInstance();
        if(f2!=0) {
            String s3 = s1.substring(0,f2);
            String[] s4 = s3.split("/");
            for (int i = 0; i < s4.length; i++) {
                TaskItem taskItem = new TaskItem(className,courseName,date.get(Calendar.YEAR)+"-"+s4[i].replace(".","-")+" "+endTime,date.get(Calendar.YEAR)+"-"+s4[i].replace(".","-")+" "+startTime,delay,classRoom);
                taskItemList.add(taskItem);
            }

        }
        return taskItemList;
    }

    public static void main(String[] args) throws ParseException {
        String s = "（9.9/9.11/9.16/9.18/9.23/9.25/10.14/10.16晚上）";
        List<TaskItem> list = parseContent(s,"2019班级","教室","会计",30);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i));
        }
    }


    private List<ExcelModel> verfiyExcel(MultipartFile file) {

        ImportParams importParams = new ImportParams();
        try {
            ExcelImportResult<ExcelModel> result = ExcelImportUtil.importExcelMore(file.getInputStream(), ExcelModel.class,
                    importParams);
            // 当结果中通过校验的数据(result.getList())为空时
            // 直接返回“上传Excel表格格式有误<br>或者<br> 上传Excel数据为空”(CodeMsg.Excel_FORMAT_ERROR)
            if (result.getList().size() == 0 || result.getList().get(0) == null) {
                return null;
            }
            return result.getList();
        } catch (IOException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }




}

class TaskItem {

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String className;

    private String course;

    private Date endTime;

    private Date startTime;

    private int notifyMinutes;

    private String classRoom;

    public TaskItem() {
    }

    public TaskItem(String className, String course, String endTime, String startTime, int notifyMinutes, String classRoom) throws ParseException {
        this.className = className;
        this.course = course;
        this.endTime = format.parse(endTime);
        this.startTime = format.parse(startTime);
        this.notifyMinutes = notifyMinutes;
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

    public int getNotifyMinutes() {
        return notifyMinutes;
    }

    public void setNotifyMinutes(int notifyMinutes) {
        this.notifyMinutes = notifyMinutes;
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                "className='" + className + '\'' +
                ", course='" + course + '\'' +
                ", endTime=" + endTime +
                ", startTime=" + startTime +
                ", notifyMinutes=" + notifyMinutes +
                ", classRoom='" + classRoom + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskItem taskItem = (TaskItem) o;
        return notifyMinutes == taskItem.notifyMinutes &&
                className.equals(taskItem.className) &&
                course.equals(taskItem.course) &&
                endTime.equals(taskItem.endTime) &&
                startTime.equals(taskItem.startTime) &&
                classRoom.equals(taskItem.classRoom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, course, endTime, startTime, notifyMinutes, classRoom);
    }
}
