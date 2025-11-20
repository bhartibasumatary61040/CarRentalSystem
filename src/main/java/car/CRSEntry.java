package car;

public class CRSEntry {

    public static void main(String[] args) {

        Car Toyotacar = new Car ();
        Toyotacar.setCarId("CAE-1");
        Toyotacar.setBrand("Toyota");
        Toyotacar.setModel("Fortuner");
        Toyotacar.setNoOfAvailableCar(1);
        Toyotacar.setPricePerDay(3000);


        Car Tatacar = new Car ();
        Tatacar.setCarId("CAE-2");
        Tatacar.setBrand("Tata");
        Tatacar.setModel("Harier");
        Tatacar.setNoOfAvailableCar(2);
        Tatacar.setPricePerDay(25000);

        Car Mahindracar = new Car ();
        Mahindracar.setCarId("CAE-3");
        Mahindracar.setBrand("Mahindra");
        Mahindracar.setModel("XUV 700");
        Mahindracar.setNoOfAvailableCar(3);
        Mahindracar.setPricePerDay(7000);

        car.CarRentalServices carRentalService = new CarRentalServices();
        carRentalService.addCars(Toyotacar);
        carRentalService.addCars(Tatacar);
        carRentalService.addCars(Mahindracar);



    }

}
