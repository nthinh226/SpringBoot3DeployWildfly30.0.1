package vn.mobileid.voucher_app.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vn.mobileid.voucher_app.domain.Voucher;
import vn.mobileid.voucher_app.domain.VoucherActivity;
import vn.mobileid.voucher_app.model.VoucherActivityDTO;
import vn.mobileid.voucher_app.repos.VoucherActivityRepository;
import vn.mobileid.voucher_app.repos.VoucherRepository;
import vn.mobileid.voucher_app.util.NotFoundException;


@Service
public class VoucherActivityService {

    private final VoucherActivityRepository voucherActivityRepository;
    private final VoucherRepository voucherRepository;

    public VoucherActivityService(final VoucherActivityRepository voucherActivityRepository,
            final VoucherRepository voucherRepository) {
        this.voucherActivityRepository = voucherActivityRepository;
        this.voucherRepository = voucherRepository;
    }

    public List<VoucherActivityDTO> findAll() {
        final List<VoucherActivity> voucherActivities = voucherActivityRepository.findAll(Sort.by("id"));
        return voucherActivities.stream()
                .map(voucherActivity -> mapToDTO(voucherActivity, new VoucherActivityDTO()))
                .toList();
    }

    public VoucherActivityDTO get(final Long id) {
        return voucherActivityRepository.findById(id)
                .map(voucherActivity -> mapToDTO(voucherActivity, new VoucherActivityDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final VoucherActivityDTO voucherActivityDTO) {
        final VoucherActivity voucherActivity = new VoucherActivity();
        mapToEntity(voucherActivityDTO, voucherActivity);
        return voucherActivityRepository.save(voucherActivity).getId();
    }

    public void update(final Long id, final VoucherActivityDTO voucherActivityDTO) {
        final VoucherActivity voucherActivity = voucherActivityRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(voucherActivityDTO, voucherActivity);
        voucherActivityRepository.save(voucherActivity);
    }

    public void delete(final Long id) {
        voucherActivityRepository.deleteById(id);
    }

    private VoucherActivityDTO mapToDTO(final VoucherActivity voucherActivity,
            final VoucherActivityDTO voucherActivityDTO) {
        voucherActivityDTO.setId(voucherActivity.getId());
        voucherActivityDTO.setSource(voucherActivity.getSource());
        voucherActivityDTO.setChangeValue(voucherActivity.getChangeValue());
        voucherActivityDTO.setVoucher(voucherActivity.getVoucher() == null ? null : voucherActivity.getVoucher().getId());
        return voucherActivityDTO;
    }

    private VoucherActivity mapToEntity(final VoucherActivityDTO voucherActivityDTO,
            final VoucherActivity voucherActivity) {
        voucherActivity.setSource(voucherActivityDTO.getSource());
        voucherActivity.setChangeValue(voucherActivityDTO.getChangeValue());
        final Voucher voucher = voucherActivityDTO.getVoucher() == null ? null : voucherRepository.findById(voucherActivityDTO.getVoucher())
                .orElseThrow(() -> new NotFoundException("voucher not found"));
        voucherActivity.setVoucher(voucher);
        return voucherActivity;
    }

}
