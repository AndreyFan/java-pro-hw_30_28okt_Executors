package de.telran.hw_30_28okt_Executors;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainTask1_Scheduled {
    //  1 уровень сложности: 1. У вас есть задача проверять какой то сайт, с целью
    //  проверки опубликования
    //на нем новой информации, но если мы будем слишком часто проверять этот сайт,
    //то нас могут "забанить" за спам. Чтобы избежать этого, вы должны слать свои запросы
    //не чаще чем один раз в минуту.
    //Ваша задача должна работать паралельно основной функциональности и не занимать ресурсы
    //главного потока.
    //Подумайте, какой механизм Java вы могли бы использовать для автоматизации данной задачи.
    //Как реализацию работы с сайтом, просто выведите сообщение о том, что соединение
    //установлено и выведите текущее время.
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new Task1_Shedule(), 0, 60, TimeUnit.SECONDS);
    }
}

class Task1_Shedule implements Runnable {
    @Override
    public void run() {
        ZonedDateTime currentTime = ZonedDateTime.now();

        Locale locale = new Locale("uk");
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd-MM-yy", locale);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm", locale);

        System.out.println("Соединение установлено . Текущая дата: "+ currentTime.format(formatter1)+ " Текущее время: " +currentTime.format(formatter2));
    }
}
