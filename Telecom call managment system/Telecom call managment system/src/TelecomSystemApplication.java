import com.telecom.Implservice.CustomerNotificationService;
import com.telecom.Implservice.CustomerServiceImpl;
import com.telecom.factory.PlanFactory;
import com.telecom.model.CallLog;
import com.telecom.model.Customer;
import com.telecom.singleton.BillingEngine;


public class TelecomSystemApplication {
    public static void main(String[] args) throws InterruptedException {
        CustomerServiceImpl customerService = new CustomerServiceImpl();

        // Register customers
        Customer user1 = new Customer("1", "Keerthi", "111-222-3333");
        user1.setPlan(PlanFactory.getPlan("prepaid"));
        Customer user2 = new Customer("2", "Sowmi", "444-555-6666");
        user2.setPlan(PlanFactory.getPlan("postpaid"));

        customerService.addCustomer(user1);
        customerService.addCustomer(user2);
        
        

        // Start a call (simulate with thread)
        CallLog callLog = new CallLog(user1, user2);
        Thread callThread = new Thread(new CallSimulator(callLog));
        callThread.start();

        callThread.join(); // wait for call to finish

        // Register observer
        BillingEngine billingEngine = BillingEngine.getInstance();
        billingEngine.addObserver(new CustomerNotificationService());

        // Generate bill and notify
        billingEngine.generateAndNotifyBill(user1);
        billingEngine.generateAndNotifyBill(user2);
    }
}
