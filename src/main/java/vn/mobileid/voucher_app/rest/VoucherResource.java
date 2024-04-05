package vn.mobileid.voucher_app.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.mobileid.voucher_app.model.VoucherDTO;
import vn.mobileid.voucher_app.service.VoucherService;
import vn.mobileid.voucher_app.util.ReferencedException;
import vn.mobileid.voucher_app.util.ReferencedWarning;


@RestController
@RequestMapping(value = "/api/vouchers", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoucherResource {

    private final VoucherService voucherService;

    public VoucherResource(final VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    public ResponseEntity<List<VoucherDTO>> getAllVouchers() {
        return ResponseEntity.ok(voucherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherDTO> getVoucher(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(voucherService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createVoucher(@RequestBody @Valid final VoucherDTO voucherDTO) {
        final Long createdId = voucherService.create(voucherDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateVoucher(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final VoucherDTO voucherDTO) {
        voucherService.update(id, voucherDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteVoucher(@PathVariable(name = "id") final Long id) {
        final ReferencedWarning referencedWarning = voucherService.getReferencedWarning(id);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        voucherService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
