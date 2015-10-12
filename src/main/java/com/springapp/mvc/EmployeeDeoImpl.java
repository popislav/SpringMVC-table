package com.springapp.mvc;

import com.springapp.db.Departments;
import com.springapp.db.Employee;
import com.springapp.db.SQLiteJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrvoje on 03.10.15..
 */
public class EmployeeDeoImpl implements EmployeeDeo {
    @Override
    public void insert(Employee employee){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedQuerry;
        ArrayList<Integer> ll = new ArrayList<Integer>();
        try {
            connection = SQLiteJDBC.getConnection();
            preparedQuerry = connection.prepareStatement("SELECT  id from employee");
            ResultSet resultSet = preparedQuerry.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                ll.add(id);
            }

            preparedStatement = connection.prepareStatement("INSERT INTO employee (id, name, surname, experience, department) VALUES (?, ?, ?, ?, ?)");
            if(ll.contains(employee.getId())){
                System.out.println("This id reserved, please try unused one");
                return;
//                System.exit(0);
            }
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getName());
            preparedStatement.setString(3, employee.getSurname());
            preparedStatement.setInt(4, employee.getExperience());
            preparedStatement.setString(5, employee.getDepartment());
            preparedStatement.executeUpdate();
            System.out.println("INSERT INTO employee (name,surname,experience,department) VALUES (?, ?, ?, ?)");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Employee selectById(int id) {
        Employee employee = new Employee();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = SQLiteJDBC.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setExperience(resultSet.getInt("experience"));
                employee.setDepartment(resultSet.getString("department"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return employee;
    }

    @Override
    public List<Departments> departments(){
        List<Departments> departments = new ArrayList<Departments>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            connection = SQLiteJDBC.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT department, COUNT(*) FROM employee GROUP BY department ORDER BY department");

            while (resultSet.next()) {
                Departments department = new Departments();
                department.setDepartment(resultSet.getString("department"));
                department.setEmployeeCount(resultSet.getInt("COUNT(*)"));
                departments.add(department);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
           }
        }
        return departments;
    }

    @Override
    public List<Employee> selectAll() {
        List<Employee> employees = new ArrayList<Employee>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = SQLiteJDBC.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM employee ORDER BY id");

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setSurname(resultSet.getString("surname"));
                employee.setExperience(resultSet.getInt("experience"));
                employee.setDepartment(resultSet.getString("department"));

                employees.add(employee);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return employees;
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = SQLiteJDBC.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("DELETE FROM employee WHERE id = ?");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Employee employee, int id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = SQLiteJDBC.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE employee SET name = ?, surname = ?, experience = ?, department = ?  WHERE id = ?");

            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getSurname());
            preparedStatement.setInt(3, employee.getExperience());
            preparedStatement.setString(4, employee.getDepartment());
            preparedStatement.setInt(5, id);
//            preparedStatement.setInt(5, employee.getId());
            preparedStatement.executeUpdate();
            System.out.println("UPDATE employee SET name = ?, name = ? ... WHERE id = ?");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
