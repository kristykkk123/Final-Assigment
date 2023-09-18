package pojo;

public class InvoicePojo {

private String invoiceNumber; //
private CustomerPojo customerInfo; // Details of the customer information associated with this invoice
private OrderPojo orderDetails; // Details of the order information associated with this invoice
private double totalInvoiceCost; // should match with totalOrderCost from OrderPojo


    public InvoicePojo(String invoiceNumber, CustomerPojo customerInfo, OrderPojo orderDetails, double totalInvoiceCost) {
        this.invoiceNumber = invoiceNumber;
        this.customerInfo = customerInfo;
        this.orderDetails = orderDetails;
        this.totalInvoiceCost = totalInvoiceCost;
    }


    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public CustomerPojo getCustomerInfo() {
        return this.customerInfo;
    }

    public void setCustomerInfo(CustomerPojo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public OrderPojo getOrderDetails() {
        return this.orderDetails;
    }

    public void setOrderDetails(OrderPojo orderDetails) {
        this.orderDetails = orderDetails;
    }

    public double getTotalInvoiceCost() {
        return this.totalInvoiceCost;
    }

    public void setTotalInvoiceCost(double totalInvoiceCost) {
        this.totalInvoiceCost = totalInvoiceCost;
    }
    
}

































