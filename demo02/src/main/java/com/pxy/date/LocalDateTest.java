package com.pxy.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;

/**
 * @author puxy
 * @version 1.0
 * @description 日期类学习包括Date 和 jdk8 中的各种类
 * @date 2021/4/29 10:06
 */
public class LocalDateTest {

    /**
     * jdk8中引入的新日期API明确了日期时间的概念,如:瞬时(instant)、长短(duration)、日期、时间、时区和周期
     */


    public static void main(String[] args) {
//        formatDate();
            LocalDate localDate = LocalDate.of(2017, 07, 8);
            TemporalAdjuster temporalAdjuster = t -> t.plus(Period.ofDays(14));
            LocalDate result = localDate.with(temporalAdjuster);

            String fourteenDaysAfterDate = "2017-07-22";
        System.out.println(result.toString());
    }

    /**
     * 使用预定义的日期格式化工具去解析或者格式化日期
     */
    private static void formatDate() {
        String dateStr = "20210401";
        LocalDate parse = LocalDate.parse(dateStr, DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(parse);
    }

    /**
     * 获取当前的时间戳
     */
    private static void instant() {
        Instant now = Instant.now();
        System.out.println(now);
    }

    /**
     * 包含时差信息的日期和时间
     */
    private static void zoneOffset() {
        LocalDateTime dateTime = LocalDateTime.of(2021, Month.APRIL, 01, 00, 00);
        //5小时时差
        ZoneOffset zoneOffset = ZoneOffset.of("-05:00");
        OffsetDateTime offsetDateTime = OffsetDateTime.of(dateTime, zoneOffset);
        System.out.println(offsetDateTime);
    }

    /**
     * 计算两个日期之间的天数和月数
     */
    private static void calculateDate() {
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(2023, Month.APRIL, 01);
        //后面的 减去 前面的
        Period between = Period.between(now, date);
        int months = between.getMonths();
        int days = between.getDays();
        //单位只能是 YEARS MONTHS DAYS
        long years = between.get(ChronoUnit.YEARS);
        System.out.printf("相隔:%d 年,%d 月,%d 天", years, months, days);
    }

    /**
     * 检查闰年
     */
    private static void checkLeapYear() {
        LocalDate now = LocalDate.now();
        boolean leapYear = now.isLeapYear();
        System.out.println("是否是闰年:" + leapYear);
    }

    /**
     * 体现出固定日期
     */
    private static void testYearMonth() {
        YearMonth currYearMonth = YearMonth.now();
        //一个月有多少天
        int length = currYearMonth.lengthOfMonth();
        System.out.println(currYearMonth + "---" + length);
        YearMonth yearMonth = YearMonth.of(2021, Month.FEBRUARY);
        //一个月有多少天
        int length1 = yearMonth.lengthOfMonth();
        System.out.println(yearMonth + "---" + length1);
    }

    /**
     * 获取特定时区下的时间
     */
    private static void zone() {
        ZoneId zoneId = ZoneId.of("America/New_York");
        LocalDateTime now = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(now, zoneId);
        System.out.println("现在日期和时间在指定时区的时间:" + zonedDateTime);
    }

    /**
     * 日期比较:判断日期早于还是晚于另一个日期
     */
    private static void compare() {
        LocalDate now = LocalDate.now();
        LocalDate date = LocalDate.of(2021, 4, 28);
        boolean after = now.isAfter(date);
        System.out.println(after);
        boolean before = now.isBefore(date);
        System.out.println(before);
        boolean equal = now.isEqual(date);
        System.out.println(equal);
    }

    /**
     * Clock时钟类
     */
    private static void clock() {
        //根据系统时间返回当前时间并设置为UTC
        Clock utc = Clock.systemUTC();
        //获取毫秒值
        long millis = utc.millis();

        //根据系统时钟区域返回当前时间
        Clock systemDefault = Clock.systemDefaultZone();
        //获取毫秒值
        long millis1 = systemDefault.millis();
        System.out.println(millis + "--" + millis1);
    }

    /**
     * 日期计算
     */
    private static void dateCalculate() {
        //在现有时间基础上增加小时
        LocalTime now = LocalTime.now();
        LocalTime plusHours = now.plusHours(3);
        System.out.println("3小时后的时间:" + plusHours);
        //计算一个星期之后的日期
        LocalDate today = LocalDate.now();
        LocalDate lastWeekDate = today.plus(1, ChronoUnit.WEEKS);
        //计算一个星期前的日期,可以传如 负数 ,或者使用 minus 方法,其底层也是通过 plus 传入负数实现
        LocalDate beforeWeekDate1 = today.plusWeeks(-1);
        LocalDate beforeWeekDate2 = today.minusWeeks(1);
        System.out.println("一周后的日期:" + lastWeekDate);
        System.out.println("一周前的日期:" + beforeWeekDate1);
        System.out.println("一周前的日期:" + beforeWeekDate2);
        //计算一年前或者一年后的日期
        LocalDate lastYearDate = today.plus(1, ChronoUnit.YEARS);
        LocalDate beforeYearDate = today.plus(-1, ChronoUnit.YEARS);
        System.out.println("一年后的日期:" + lastYearDate);
        System.out.println("一年前的日期:" + beforeYearDate);
    }

    /**
     * 获取当前时间
     */
    private static void getCurrTime() {
        //获取当前时间 LocalTime
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
    }

    /**
     * 获取当前日期
     */
    private static void getCurrentDate() {
        //获取当前日期
        LocalDate now = LocalDate.now();
        System.out.println(now);

        Date date = new Date();
        System.out.println(date);
    }

    /**
     * 检查周期性事件如节日、生日等
     */
    private static void cycleDate() {
        LocalDate now = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(1992, 12, 25);
        MonthDay birthDay = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());

        MonthDay currMonthDay = MonthDay.from(now);
        if (currMonthDay.equals(birthDay)) {
            System.out.println("happy birthDay to you");
        }
    }

    /**
     * 处理特定日期,当日期不合法时候会报错
     */
    private static void getALocalDate() {
        //处理特定日期,当日期不合法时候会报错
        LocalDate of = LocalDate.of(2021, 4, 29);
        System.out.println(of);
        //判断两个日期是否相等
        LocalDate now = LocalDate.now();
        System.out.println(now.isEqual(of));
        System.out.println(now.equals(of));
    }

    /**
     * 获取年、月、日
     */
    private static void getY_M_D() {
        // 传统方式获取年、月、日
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        int cYear = calendar.get(Calendar.YEAR);
        int cMonth = calendar.get(Calendar.MONTH);
        int cDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int cDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        int cDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int cWeekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
        int cHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.printf("年:%d  月:%d  日:%d  一年中的第几天:%d %n", cYear, cMonth, cDayOfMonth, cDayOfYear);
        System.out.printf("星期:%d  一年的第几周:%d  小时:%d %n", cDayOfWeek, cWeekOfYear, cHourOfDay);

        // LocalDate获取年、月、日信息
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int monthValue = today.getMonthValue();
        int dayOfMonth = today.getDayOfMonth();
        int dayOfYear = today.getDayOfYear();
        DayOfWeek dayOfWeek = today.getDayOfWeek();
        int dayOfWeekValue = dayOfWeek.getValue();
        //localDate获取一年中的第几周
        WeekFields weekFields = WeekFields.ISO;
        int weekOfYear = today.get(weekFields.weekOfYear());
        //localDate中不包含时间,要获取时、分、秒需要使用localTime
        LocalTime time = LocalTime.now();
        int hourOfDay = time.getHour();
        System.out.printf("年:%d  月:%d  日:%d  一年中的第几天:%d %n", year, monthValue, dayOfMonth, dayOfYear);
        System.out.printf("星期:%d  一年的第几周:%d  小时:%d %n", dayOfWeekValue, weekOfYear, hourOfDay);
    }
}
