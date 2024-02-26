package com.dao;

import com.entity.CategoriesEntity;
import com.entity.TaskEntity;
import com.util.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDao {

    private static final TaskDao TASK_DAO = new TaskDao();
    private static final String SELECT_SQL = """
            SELECT  task_id, 
                    task_name,
                    due_date, 
                    category_id
            from to_do_repository.to_do_app.tasks
            """;


    public List<TaskEntity> readAll () {
        try (Connection connection = ConnectionManager.get();
            var statement = connection.prepareStatement(SELECT_SQL)) {

            ResultSet resultSet = statement.executeQuery();
            List <TaskEntity> list = new ArrayList<>();
            while (resultSet.next()) {
                list.add(
                        new TaskEntity(resultSet.getInt("task_id"),
                                        resultSet.getString("task_name"),
                                        resultSet.getDate("due_date").toLocalDate(),
                                        CategoriesDao.save(resultSet.getInt("category_id")).orElse(null))
                );
            }
            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TaskDao getTaskDao() {
        return TASK_DAO;
    }
}
