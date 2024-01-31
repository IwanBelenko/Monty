package org.example;

import java.util.HashMap;
import java.util.Random;

public class MontyHallGame {

    private Random random = new Random();

    // Метод для проведения одной игры
    public boolean playGame(boolean switchDoor) {
        // Инициализация дверей (false - козел, true - автомобиль)
        boolean[] doors = {false, false, false};
        // Случайный выбор двери с автомобилем
        doors[random.nextInt(doors.length)] = true;

        // Выбор игрока
        int chosenDoor = random.nextInt(doors.length);

        // Ведущий открывает дверь с козлом
        int shownDoor;
        do {
            shownDoor = random.nextInt(doors.length);
        } while (doors[shownDoor] || shownDoor == chosenDoor);

        // Если игрок выбирает поменять свой первоначальный выбор
        if (switchDoor) {
            chosenDoor = 3 - chosenDoor - shownDoor;
        }

        // Возвращает результат: выиграл ли игрок автомобиль
        return doors[chosenDoor];
    }

    public static void main(String[] args) {
        MontyHallGame game = new MontyHallGame();

        // Статистика побед и поражений для каждой стратегии
        int winWithSwitch = 0;
        int winWithoutSwitch = 0;

        // Хеш-карта для хранения результатов
        HashMap<Integer, Boolean> resultMap = new HashMap<>();

        // Играем 1000 раз
        for (int i = 0; i < 1000; i++) {
            // Стратегия с переключением
            boolean winWithSwitchResult = game.playGame(true);
            resultMap.put(i, winWithSwitchResult);
            if (winWithSwitchResult) {
                winWithSwitch++;
            }

            // Стратегия без переключения
            boolean winWithoutSwitchResult = game.playGame(false);
            // Для упрощения логики игра без переключения не записывается в resultMap
            if (winWithoutSwitchResult) {
                winWithoutSwitch++;
            }
        }

        resultMap.forEach((key, value) -> System.out.println("Game " + key + ": " + (value ? "Win" : "Lose")));
    }
}
