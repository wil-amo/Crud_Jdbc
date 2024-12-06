import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger;
        Scanner scanner = new Scanner(System.in);
        EmployeeDAO employeeDAO = new EmployeeDAO();

        // Initialize db
        EmployeeDAO.startDb();

        String resetBank = "DELETE FROM jdbc_crud.employees;";
        employeeDAO.randomQuery(resetBank);

        // Loop
        while (true) {
            //Menu
            System.out.println("Menu:");
            System.out.println("1. Add employee");
            System.out.println("2. List employee");
            System.out.println("3. Update employee");
            System.out.println("4. Delete employee");
            System.out.println("5. Exit");
            System.out.print("Selection an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();  //

            switch (option) {
                case 1:
                    // Add emp
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Departament: ");
                    String department = scanner.nextLine();
                    Employee newEmployee = new Employee(0, name, email, department);
                    employeeDAO.insertEmployee(newEmployee);
                    System.out.println("Funcionário adicionado com sucesso.");
                    break;

                case 2:
                    // List employee
                    if (employeeDAO.getAllEmployees().isEmpty()){
                        System.out.println("No employees registered.");
                    }else {
                        System.out.println("List of employees:");
                        System.out.println(employeeDAO.getAllEmployees());}
                    break;

                case 3:
                    // Update employee
                    System.out.print("Enter the employee ID to update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New Name: ");
                    String newName = scanner.nextLine();
                    System.out.print("New Email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("New Department: ");
                    String newDepartment = scanner.nextLine();
                    employeeDAO.upEmployeeById(updateId, newName, newEmail, newDepartment);
                    System.out.println("Employee successfully updated.");
                    break;

                case 4:
                    // Delete employee
                    System.out.print("Enter the ID of the employee to be deleted: ");
                    int deleteId = scanner.nextInt();
                    employeeDAO.deleteEmployee(deleteId);
                    System.out.println("Employee successfully deleted.");
                    break;

                case 5:
                    // Exit
                    System.out.println("Exit...");
                    scanner.close();
                    return;


                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
