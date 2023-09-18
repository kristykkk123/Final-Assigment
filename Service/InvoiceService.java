package Service;
import java.time.LocalDateTime;
import Repository.OpeningHoursRepository;
import pojo.InvoicePojo;
import pojo.OrderPojo;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


    public class InvoiceService {
    private final OpeningHoursRepository openingHoursRepository; // injecting the repository

    public InvoiceService(OpeningHoursRepository openingHoursRepository) {
        this.openingHoursRepository = openingHoursRepository; // initialise the repository
    }
    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    public InvoicePojo generateInvoiceForOrder(OrderPojo order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        double totalCost = order.getTotalOrderCost();

        LocalDateTime completionTime = order.getPickupDateTime();
        LocalDateTime adjustedPickupTime = adjustPickupTimeBasedOnStoreHours(completionTime);
        String formattedPickupTime = formatDateTime(adjustedPickupTime);
        System.out.println("Formatted Pickup Time: " + formattedPickupTime);  // This will print the formatted date


        return new InvoicePojo(order.getOrderNumber(), order.getCustomerDetails(), order, totalCost);
    }

    private LocalDateTime adjustPickupTimeBasedOnStoreHours(LocalDateTime completionTime) {
        String dayOfWeek = completionTime.getDayOfWeek().toString().toUpperCase();
        System.out.println("Fetching for day: " + dayOfWeek);
        LocalTime storeOpenTime = openingHoursRepository.getOpeningTimeForDay(dayOfWeek);
        LocalTime storeCloseTime = openingHoursRepository.getClosingTimeForDay(dayOfWeek);

        if (storeOpenTime == null || storeCloseTime == null) {
            // Add proper error handling here
            throw new RuntimeException("Store hours not found for " + dayOfWeek);
        }

        if (completionTime.toLocalTime().isAfter(storeCloseTime)) {
            return completionTime.plusDays(1).withHour(storeOpenTime.getHour()).withMinute(storeOpenTime.getMinute()).withSecond(0).withNano(0);
        } else if (completionTime.toLocalTime().isBefore(storeOpenTime)) {
            return completionTime.withHour(storeOpenTime.getHour()).withMinute(storeOpenTime.getMinute()).withSecond(0).withNano(0);
        } else {
            return completionTime;
        }
    }
    }

















