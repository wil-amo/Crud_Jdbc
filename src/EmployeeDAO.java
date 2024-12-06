import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class EmployeeDAO {
    private static final Logger logger = Logger.getLogger(EmployeeDAO.class.getName());


    public void randomQuery(String query) {
        try (Connection connection = db_connection.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    static {
        try {
            FileHandler fileHandler = new FileHandler("C:\\Users\\willi\\Java_creations\\CRUD_JBDC\\Java_CRUD.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // log (INFO, WARNING, SEVERE, etc.)
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "FileHandler could not be configured", e);
        }
    }


    public static void startDb() {
        logger.info("Starting a database connection...");

        try (Connection connection = db_connection.getConnection();
             Statement statement = connection.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS employees ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "email VARCHAR(100) NOT NULL UNIQUE, "
                    + "department VARCHAR(100) NOT NULL)";
            statement.executeUpdate(createTableSQL);

            logger.info("Conection successfully...");

        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("Error connecting to the database...");

        }
    }

    public void insertEmployee(Employee emp) {
        logger.info("Calls insertEmployee method");
        String insertSql = "INSERT INTO employees (name, email, department) VALUES (?, ?, ?)";

        try (Connection con = db_connection.getConnection();
             PreparedStatement ps = con.prepareStatement(insertSql)) {
            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDepartment());

            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("Erro: The email '" + emp.getEmail() + "' is already registered for the name '" + emp.getName() + "' and department '" + emp.getDepartment() + "'. Try another email.");
            logger.info("Error when executing insertEmployee because the email is already registered in the database  ");
        } catch (SQLException ex) {
            System.out.println("Error de SQL: " + ex.getMessage());
            logger.info("Error executing insertEmployee, SQ syntax error");
        } catch (Exception ex) {
            System.out.println("Error inespected: " + ex.getMessage());
            logger.info("Erro inespected.");
        }
    }

    public List<Employee> getAllEmployees() {
        logger.info("Calls getAllEmployees method");
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = db_connection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * from employees")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String department = resultSet.getString("department");

                employees.add(new Employee(id, name, email, department));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("Found error in getAllEmployees method");
            throw new RuntimeException(e);
        }
        return employees;
    }



    public void upEmployeeById(int id, String newName, String newEmail, String newDepartment) {
        logger.info("Calls método method");
        String sql = "UPDATE employees SET name = ?, email = ?, department = ? WHERE id = ?";
        try (Connection connection = db_connection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setString(2, newEmail);
            statement.setString(3, newDepartment);
            statement.setInt(4, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No employee found with ID: " + id);
                logger.info("upEmployeeById  não encontrado");
            } else {
                System.out.println("Employee with ID " + id + " updated successfully.");
                logger.info("upEmployeeById  correctly");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void upEmployeeByName(String name, String newName, String newEmail, String newDepartment) {
        String sql = "UPDATE employees SET name = ?, email = ?, department = ? WHERE name = ?";
        try (Connection connection = db_connection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setString(2, newEmail);
            statement.setString(3, newDepartment);
            statement.setString(4, name);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No employee found with name: " + name);
            } else {
                System.out.println("Employee with name '" + name + "' updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void upEmployeeByEmail(String email, String newName, String newEmail, String newDepartment) {
        String sql = "UPDATE employees SET name = ?, email = ?, department = ? WHERE email = ?";
        try (Connection connection = db_connection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newName);
            statement.setString(2, newEmail);
            statement.setString(3, newDepartment);
            statement.setString(4, email);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                System.out.println("No employee found with email: " + email);
            } else {
                System.out.println("Employee with email '" + email + "' updated successfully.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int id) {
        try (Connection connection = db_connection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM employees WHERE id=?")) {

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printEmployee() {
        List<Employee> employees = getAllEmployees();

        System.out.println("+----+--------------+---------------------------+---------------+");
        System.out.println("| ID | Name         | Email                     | Department       |");
        System.out.println("+----+--------------+---------------------------+---------------+");

        for (Employee employee : employees) {
            System.out.printf("| %2d | %-12s | %25s | %-13s |\n",
                    employee.getId(), employee.getName(), employee.getEmail(), employee.getDepartment());
        }

        System.out.println("+----+--------------+---------------------------+---------------+" + "\n");
    }
}