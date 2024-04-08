package vn.mobileid.voucherapp.rest;

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
import vn.mobileid.voucherapp.model.VoucherActivityDTO;
import vn.mobileid.voucherapp.service.VoucherActivityService;


@RestController
@RequestMapping(value = "/api/voucherActivities", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoucherActivityResource {

    private final VoucherActivityService voucherActivityService;

    public VoucherActivityResource(final VoucherActivityService voucherActivityService) {
        this.voucherActivityService = voucherActivityService;
    }

    @GetMapping
    public ResponseEntity<List<VoucherActivityDTO>> getAllVoucherActivities() {
        return ResponseEntity.ok(voucherActivityService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherActivityDTO> getVoucherActivity(
            @PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(voucherActivityService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createVoucherActivity(
            @RequestBody @Valid final VoucherActivityDTO voucherActivityDTO) {
        final Long createdId = voucherActivityService.create(voucherActivityDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateVoucherActivity(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final VoucherActivityDTO voucherActivityDTO) {
        voucherActivityService.update(id, voucherActivityDTO);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteVoucherActivity(@PathVariable(name = "id") final Long id) {
        voucherActivityService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
