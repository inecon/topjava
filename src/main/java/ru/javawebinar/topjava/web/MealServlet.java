package ru.javawebinar.topjava.web;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Data
@Slf4j
@EqualsAndHashCode
public class MealServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals list");

        List <MealTo> mealsList = new MealsUtil().getHardcodedMealsListWithExcess();
        request.setAttribute("mealsList", mealsList );

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }


}
