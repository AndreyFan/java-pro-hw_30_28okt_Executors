package de.telran.hw_30_28okt_Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MainTask2_Ex {
    // 2*. В одном из предыдущий ДЗ у вас была задача:
    //У вас в магазине распродажа. К вам набежало 10 000 клиентов и вы пытаетесь корректно всех обслужить с
    //учетом того, что одновременно у вас внутри магазина может находиться не более 10 покупателей(согласно
    //карантинным нормам) и время обслуживания одного покупателя занимает 30 секунд.
    //Сымитируйте данный процесс работы и подсчитайте за какой период времени вы сможете обслужить данный
    //объем покупателей?
    //Отдельный покупатель - отдельный поток.
    //Можно было бы реализовать данную задачу не используя семафор?
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10); //10- количество потоков в пуле

        for (int i = 0; i < 30; i++) { // 30 покупателей ( 10 000 слишком много)
            executorService.execute(new ConsumersInShop());
            Thread.sleep(500); // для наглядности
        }

        executorService.shutdown();
    }
}

class ConsumersInShop implements Runnable {

    static int i = 0;

    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + " зашел в магазин ");  // номера потоков будут в пределах 1,,10 ( так как пул ограничен 10-тью потоками)
            Thread.sleep(5000);  // пауза для наглядности, что в магазине находится одновременно не более  10 человек
            i++;  // внутренний счетчик для подсчета времени , необходимого на обслуживание ( 0,5 мин на каждого)
            System.out.println(Thread.currentThread().getName() + " выходит из магазина . С начала обслуживания прошло " + i * 0.5 + " минут ");
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}

