package vn.mobileid.voucher_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class VoucherAppApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(VoucherAppApplication.class, args);
    }

}
