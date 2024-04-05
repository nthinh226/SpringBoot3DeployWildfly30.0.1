package vn.mobileid.voucher_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.mobileid.voucher_app.domain.Voucher;
import vn.mobileid.voucher_app.domain.VoucherActivity;


public interface VoucherActivityRepository extends JpaRepository<VoucherActivity, Long> {

    VoucherActivity findFirstByVoucher(Voucher voucher);

}
