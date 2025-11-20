package car;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CarRentalServices {

    private List<Car> cars;

    private List<Customer> customers;
    private List<BookedCarInformation> bookedCarInformation;

    public CarRentalServices () {

        cars = new ArrayList<Car>();
        customers = new ArrayList<>();
        bookedCarInformation = new ArrayList<>();


    }

    public void bookedCar(Car car , Customer customer , int days) {
        if (car.getNoOfAvailableCar() > 0) {
            car.setNoOfAvailableCar(car.getNoOfAvailableCar() -1);
            bookedCarInformation.add(new BookedCarInformation(car, customer, days));

        } else {
            System.out.println(" Car is not available for rent.");
        }

    }
    public void returnCar(Car car, BookedCarInformation bookedCarInfo) {
        car.setNoOfAvailableCar(car.getNoOfAvailableCar() + 1);
        bookedCarInformation.remove(bookedCarInfo);

    }
    public void addCars (Car car) {
        cars.add(car);
    }
    public void addCustomer(Customer cust) {
        customers.add(cust);
    }
    public void options () {
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println(" ===== Welcom to our Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Available Car");
            System.out.println("4. Exit");
            System.out.println("Enter your choice");

            int choice = sc.nextInt();

            sc.nextLine();

            if(choice == 1) {

                System.out.println(" ==For Renting a Car please provide below details");
                System.out.println(" Enter your name ");

                String custName = sc.nextLine();

                System.out.println("Enter the Car Id you want to rent");
                String carId = sc.nextLine();

                System.out.println("Enter the numbers of days for rental");
                int days = sc.nextInt();
                Customer customer = new Customer("CUSTOMER- "+ (customers.size()+ 1), custName);


                addCustomer(customer);

                Optional<Car> optionalCar = cars.stream()
                        .filter(c -> c.getCarId().equalsIgnoreCase(carId) && c.getNoOfAvailableCar() > 0)
                        .findAny();

                if (optionalCar.isEmpty()) {

                    System.out.println(" Car is not available . Please try to book another Car");

                    options();

                    return;
                }
                Car selectedCar = optionalCar.get();
                System.out.println(" === Bill Receipt === ");
                System.out.println("Customer ID : " + customer.getId());
                System.out.println("Customer Name : " + customer.getName());
                System.out.println("Car Brand :" + selectedCar.getBrand()+ "Model: " + selectedCar.getModel());
                System.out.println("Rental Days :" + days);
                System.out.println("Total Price : " + selectedCar.calculatePrice(days));

                System.out.println("Confirm rental (Y/N): ");
                String confirmation = sc.next();

                if(confirmation.equalsIgnoreCase("Y")) {

                    //Booked a Car
                    bookedCar(selectedCar, customer , days);

                    System.out.println("Car booking is done successfully.");
                } else {

                    System.out.println("Car booking is canceled .");
                }


            } else if (choice ==2) {
                System.out.println(" === Return a Car ===");
                System.out.println("Enter the car ID you want to return: ");
                String carId = sc.nextLine();

                Optional<Car> optionalCar = cars.stream()
                        .filter(c-> c.getCarId().equals(carId)).findAny();

                if (optionalCar.isEmpty()) {

                    System.out.println("Please provide valid car details.");

                    options();
                    return;
                }

                Car carToReturn = optionalCar.get();

                BookedCarInformation bookedCarInfo = bookedCarInformation.stream()
                        .filter(b-> b.getCar() == carToReturn).findFirst().orElse(null);
                if(bookedCarInformation == null) {
                    System.out.println("Car information not available. please provide valid details");
                    options();
                    return;
                }

                Customer cust = bookedCarInfo.getCustomer();
                returnCar(carToReturn, bookedCarInfo);
                System.out.println(" Car return successfully by " +cust.getName());
            }
            else if (choice ==3) {
                System.out.println(" == Available Cars ==");

                cars.stream()
                        .filter(c-> c.getNoOfAvailableCar() > 0)
                        .forEach(car ->System.out.println(car.getCarId() + "-" + car.getBrand() +
                                "" + car.getModel() + "" + car.getNoOfAvailableCar()));
            } else if (choice ==4) {
                System.out.println("Thank you for choosing us.");
                break;
            } else {
                System.out.println(" Please provide valid options.");
            }

        }
    }}




