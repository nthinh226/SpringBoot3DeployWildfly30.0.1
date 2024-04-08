package vn.mobileid.voucherapp.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.mobileid.voucherapp.domain.Voucher;
import vn.mobileid.voucherapp.domain.VoucherActivity;
import vn.mobileid.voucherapp.model.VoucherDTO;
import vn.mobileid.voucherapp.repos.VoucherActivityRepository;
import vn.mobileid.voucherapp.repos.VoucherRepository;
import vn.mobileid.voucherapp.util.NotFoundException;
import vn.mobileid.voucherapp.util.ReferencedWarning;


@Service
public class VoucherService {

    private final VoucherRepository voucherRepository;
    private final VoucherActivityRepository voucherActivityRepository;

    public VoucherService(final VoucherRepository voucherRepository,
            final VoucherActivityRepository voucherActivityRepository) {
        this.voucherRepository = voucherRepository;
        this.voucherActivityRepository = voucherActivityRepository;
    }

    public List<VoucherDTO> findAll() {
        final List<Voucher> vouchers = voucherRepository.findAll(Sort.by("id"));
        return vouchers.stream()
                .map(voucher -> mapToDTO(voucher, new VoucherDTO()))
                .toList();
    }

    public VoucherDTO get(final Long id) {
        return voucherRepository.findById(id)
                .map(voucher -> mapToDTO(voucher, new VoucherDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final VoucherDTO voucherDTO) {
        final Voucher voucher = new Voucher();
        mapToEntity(voucherDTO, voucher);
        return voucherRepository.save(voucher).getId();
    }

    public void update(final Long id, final VoucherDTO voucherDTO) {
        final Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(voucherDTO, voucher);
        voucherRepository.save(voucher);
    }

    public void delete(final Long id) {
        voucherRepository.deleteById(id);
    }

    private VoucherDTO mapToDTO(final Voucher voucher, final VoucherDTO voucherDTO) {
        voucherDTO.setId(voucher.getId());
        voucherDTO.setVoucherStatus(voucher.getVoucherStatus());
        voucherDTO.setVoucherCode(voucher.getVoucherCode());
        voucherDTO.setInitialValue(voucher.getInitialValue());
        voucherDTO.setCurrentValue(voucher.getCurrentValue());
        return voucherDTO;
    }

    private Voucher mapToEntity(final VoucherDTO voucherDTO, final Voucher voucher) {
        voucher.setVoucherStatus(voucherDTO.getVoucherStatus());
        voucher.setVoucherCode(voucherDTO.getVoucherCode());
        voucher.setInitialValue(voucherDTO.getInitialValue());
        voucher.setCurrentValue(voucherDTO.getCurrentValue());
        return voucher;
    }

    public boolean voucherCodeExists(final String voucherCode) {
        return voucherRepository.existsByVoucherCodeIgnoreCase(voucherCode);
    }

    public ReferencedWarning getReferencedWarning(final Long id) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Voucher voucher = voucherRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        final VoucherActivity voucherVoucherActivity = voucherActivityRepository.findFirstByVoucher(voucher);
        if (voucherVoucherActivity != null) {
            referencedWarning.setKey("voucher.voucherActivity.voucher.referenced");
            referencedWarning.addParam(voucherVoucherActivity.getId());
            return referencedWarning;
        }
        return null;
    }

}
