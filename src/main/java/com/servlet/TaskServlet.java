package com.servlet;

import com.service.FlightService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/tasks")
public class TaskServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (PrintWriter writer = resp.getWriter()) {
            writer.write("<h1>Список задач:</h1>");
            writer.write("<ul>");
            flightService.findAll()
                    .forEach(
                            flightDto -> {
                                writer.write(
                                        """
                                                <li>
                                                    <a href="tasks?flightId=%d">%s</a>
                                                </li>
                                            """.formatted(flightDto.getTaskId(),flightDto.getTaskName())
                                );
                            }
                    );

            writer.write("</ul>");

        }

    }
}
