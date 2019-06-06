package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        List<UserMealWithExceed> userMealWithExceedList = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

        //for checking, DELETE before release
        userMealWithExceedList.forEach(System.out::println);
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime,
                                                                   LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> filteredWithExceeded = new ArrayList<>();
        Map<LocalDate, Integer> sumOfCaloriesPerDay = new HashMap<>();


        //computing sum of calories per day and save it in Map <DAY, sumOfCaloriesPerDay>
        for (UserMeal meal : mealList) {
            sumOfCaloriesPerDay.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), (oldQuantityOfCalories, newQuantityOfCalories)
                    -> oldQuantityOfCalories + newQuantityOfCalories);
        }

        for (UserMeal meal : mealList) {
            boolean exceed;
            if (meal.getDateTime().toLocalTime().isAfter(startTime) && meal.getDateTime().toLocalTime().isBefore(endTime)) {
                if (sumOfCaloriesPerDay.get(meal.getDateTime().toLocalDate()) <= caloriesPerDay) {
                    exceed = false;
                } else {
                    exceed = true;
                }
                filteredWithExceeded.add(new UserMealWithExceed(meal.getDateTime(), meal.getDescription(), meal.getCalories(),
                        exceed));
            }
        }

        return filteredWithExceeded;
    }
}
