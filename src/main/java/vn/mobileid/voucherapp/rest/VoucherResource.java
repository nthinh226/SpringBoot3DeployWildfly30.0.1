package vn.mobileid.voucherapp.rest;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.mobileid.voucherapp.model.VoucherDTO;
import vn.mobileid.voucherapp.service.VoucherService;
import vn.mobileid.voucherapp.util.ReferencedException;
import vn.mobileid.voucherapp.util.ReferencedWarning;

import java.util.List;


@RestController
@RequestMapping(value = "/api/vouchers", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoucherResource {

    private final VoucherService voucherService;

    public VoucherResource(final VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping
    public ResponseEntity<List<VoucherDTO>> getAllVouchers() {
        Logger logger = LogManager.getLogger(VoucherResource.class);
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

    @GetMapping("/access-token-test")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<String> accessTokenTest() {
        return ResponseEntity.ok("{\"access_token\":\"eyJ4NXQiOiJNV0l5TkRJNVlqRTJaV1kxT0RNd01XSTNOR1ptTVRZeU5UTTJOVFZoWlRnMU5UTTNaVE5oTldKbVpERTFPVEE0TldFMVlUaGxNak5sTldFellqSXlZUSIsImtpZCI6Ik1XSXlOREk1WWpFMlpXWTFPRE13TVdJM05HWm1NVFl5TlRNMk5UVmhaVGcxTlRNM1pUTmhOV0ptWkRFMU9UQTROV0UxWVRobE1qTmxOV0V6WWpJeVlRX1JTMjU2IiwidHlwIjoiYXQrand0IiwiYWxnIjoiUlMyNTYifQ.eyJzdWIiOiJhYWE4MTIyOS0wNGVjLTRiMmQtOWYxNi04ZWMzNDI3YjZjMTYiLCJhdXQiOiJBUFBMSUNBVElPTiIsImF1ZCI6ImZLS3B3ZEJUTFhVOWtfc0NGSUtrblFvRmY5MGEiLCJuYmYiOjE3MTc0MDQ0OTIsImF6cCI6ImZLS3B3ZEJUTFhVOWtfc0NGSUtrblFvRmY5MGEiLCJzY29wZSI6ImRlZmF1bHQiLCJpc3MiOiJodHRwczpcL1wvd3NvMmFtLm1vYmlsZS1pZC52bjo0NDNcL29hdXRoMlwvdG9rZW4iLCJleHAiOjE3MTc0MDgwOTIsImlhdCI6MTcxNzQwNDQ5MiwianRpIjoiOWZlN2QzYWYtNzU0NS00YmYzLWI1ZDUtYWU5MTJmMTRkNDM1IiwiY2xpZW50X2lkIjoiZktLcHdkQlRMWFU5a19zQ0ZJS2tuUW9GZjkwYSJ9.eCwQYsU_fiFklJ46nrL0RDtCHn9KLdSFPR6AJ4Xpnta6pgmckLHeX-CpJETnhvtX-coWXSRpmkWSzbmw-YNdpEScGOeaKSQIAbcqggcAyPbT7KfAQEWkTxGWu5FYYDV8r5ggrp2z2zu2GuPzVqAy3JDqAc1yInOtBdu6rVCj1NWXkBYH39q-VoK2SyzZRB3LF4JsJ9l3xU2PPogJWqsS-TXWgxJwwvW-y1symEbHUOJjkM-nnEdTh3yF8c2uoMRyICdq8L8LShaiF-7AxrOGWDSNt6fwb6SjHQP-kw9cohtPmRVLFd-APU3LthyslPB9jgeuLzIzmksZnaUe2uDhCA\",\"scope\":\"default\",\"token_type\":\"Bearer\",\"expires_in\":3600}");
    }

}
