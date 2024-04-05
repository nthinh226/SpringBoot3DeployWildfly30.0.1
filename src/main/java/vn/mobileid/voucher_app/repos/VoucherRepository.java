package vn.mobileid.voucher_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.mobileid.voucher_app.domain.Voucher;


public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    boolean existsByVoucherCodeIgnoreCase(String voucherCode);

}
