package vn.mobileid.voucherapp.model;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.springframework.web.servlet.HandlerMapping;
import vn.mobileid.voucherapp.service.VoucherService;


/**
 * Validate that the voucherCode value isn't taken yet.
 */
@Target({ FIELD, METHOD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = VoucherVoucherCodeUnique.VoucherVoucherCodeUniqueValidator.class
)
public @interface VoucherVoucherCodeUnique {

    String message() default "{Exists.voucher.voucherCode}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class VoucherVoucherCodeUniqueValidator implements ConstraintValidator<VoucherVoucherCodeUnique, String> {

        private final VoucherService voucherService;
        private final HttpServletRequest request;

        public VoucherVoucherCodeUniqueValidator(final VoucherService voucherService,
                final HttpServletRequest request) {
            this.voucherService = voucherService;
            this.request = request;
        }

        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext cvContext) {
            if (value == null) {
                // no value present
                return true;
            }
            @SuppressWarnings("unchecked") final Map<String, String> pathVariables =
                    ((Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE));
            final String currentId = pathVariables.get("id");
            if (currentId != null && value.equalsIgnoreCase(voucherService.get(Long.parseLong(currentId)).getVoucherCode())) {
                // value hasn't changed
                return true;
            }
            return !voucherService.voucherCodeExists(value);
        }

    }

}
