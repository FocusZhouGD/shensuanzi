package com.example.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {

    @Autowired
    private ApplicationContext applicationContext;



    @GetMapping("/pay")
    public PayResult pay(@RequestParam("amount") int amount,@RequestParam("paymentType") String paymentType ){
        Order order = new Order();
        order.setAmount(amount);
        order.setPaymentType(paymentType);

        //根据不同类型获取不同类型的策略
        //这里可以使用枚举以及map映射不同的类也是可以的，放的位置不同使用的场景就可以不一样
        IPayment payment = applicationContext.getBean(paymentType, IPayment.class);
        PayResult payResult = payment.pay(order);
        return payResult;
    }
}


/**
 * @Component
 * public class ExampleUtil {
 *
 *     // 通过@Autowired注解注入ApplicationContext
 *     private final ApplicationContext applicationContext;
 *
 *     // 构造函数注入
 *     public ExampleUtil(ApplicationContext applicationContext) {
 *         this.applicationContext = applicationContext;
 *     }
 *
 *     // 提供一个线程安全的方法来获取名为exampleService的bean
 *     public synchronized Object getExampleService() {
 *         try {
 *             // 通过getBean方法获取bean，这里明确指定了bean的名称
 *             return applicationContext.getBean("exampleService");
 *         } catch (Exception e) {
 *             // 合理处理可能抛出的异常，例如打印日志或返回null
 *             // 实际应用中，应根据具体需求来定制异常处理策略
 *             System.err.println("Failed to get exampleService bean: " + e.getMessage());
 *             return null;
 *         }
 *     }
 * }
 *
 * 合理划分职责：通过创建一个工具类ExampleUtil，将获取特定bean的职责集中在此类中，降低了类之间的耦合度。
 * 使用接口而非直接使用ApplicationContext：虽然示例中没有展示接口的使用，但通过提供一个具体的方法getExampleService，使得调用者无需直接与ApplicationContext交互，进一步降低了对ApplicationContext的直接依赖。
 * 异常处理：通过捕获并处理getBean方法可能抛出的所有异常，确保了获取bean操作的异常安全性。
 * 线程安全性：通过在getExampleService方法上添加synchronized关键字，确保了在多线程环境下访问ApplicationContext的线程安全性。
 * 性能考虑：尽管本示例中性能不是主要关注点，但通过提供一个专门的方法来获取bean，可以在需要的时候缓存bean的实例，从而提高性能。
 * 代码注释和文档：虽然上述代码中没有包含详细的注释，但在实际应用中，应提供充分的文档和注释来说明ExampleUtil类和getExampleService方法的使用方法和设计意图。
 */