package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Optional;

public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {
        Optional<Integer> max = new BufferedReader(new FileReader("data.txt")).lines().//определяем считывание со строки
                map(s -> s.replaceAll("[^0-9,]", "")).map(s -> {//удаляем все лишние символы кроме цифр и запятых
                    String[] split = s.split(",");//разделяем строку на массивы по запятой
                return split;//возвращаем массив
                }).
                peek(parts-> System.out.println("Отрезок с координатами: x=" + parts[0] + " y=" + parts[1])).//вывод всех отрезков и их координат
                map(parts->{//для каждого из массивов
                    int x = Integer.parseInt(parts[0]);//получаем значение x
                    int y = Integer.parseInt(parts[1]);//получаем значение y
                    int maxi = Math.max(x, y);//находим максимальное значение, из которого вычитать минимальное
                    int min = Math.min(x,y);//находим минимальное значение
                    return maxi-min;//вычитаем длину массива
                }).
                max(Integer::compare);//вызываем метод сравнения для значений
        System.out.println(max.orElseGet(()->0));//выводим значение Optional, если оно отсутствует, будет выведено 0, используя Supplier
    }
}
