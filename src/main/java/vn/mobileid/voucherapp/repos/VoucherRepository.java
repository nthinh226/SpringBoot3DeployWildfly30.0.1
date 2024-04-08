package vn.mobileid.voucherapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.mobileid.voucherapp.domain.Voucher;


public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    boolean existsByVoucherCodeIgnoreCase(String voucherCode);

}
