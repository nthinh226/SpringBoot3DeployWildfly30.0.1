package vn.mobileid.voucher_app.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VoucherDTO {

    private Long id;

    @NotNull
    private VoucherStatus voucherStatus;

    @NotNull
    @Size(max = 255)
    @VoucherVoucherCodeUnique
    private String voucherCode;

    @NotNull
    private Long initialValue;

    @NotNull
    private Long currentValue;

}
