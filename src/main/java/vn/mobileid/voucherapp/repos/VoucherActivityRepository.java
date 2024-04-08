package vn.mobileid.voucherapp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.mobileid.voucherapp.domain.Voucher;
import vn.mobileid.voucherapp.domain.VoucherActivity;


public interface VoucherActivityRepository extends JpaRepository<VoucherActivity, Long> {

    VoucherActivity findFirstByVoucher(Voucher voucher);

}
