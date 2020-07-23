package com.github.roman1306.registry.controller.html;

import com.github.roman1306.registry.entity.User;
import com.github.roman1306.registry.presentation.DoctorView;
import com.github.roman1306.registry.presentation.SlotView;
import com.github.roman1306.registry.service.spi.ContentProvider;
import com.github.roman1306.registry.service.spi.PatientRecordService;
import io.micrometer.core.annotation.Timed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.groupingBy;

/**
 * Контроллер, отдающий страницу с записями, как для врача, так и для пациента.
 */
@Controller
@RequestMapping("/")
public class RecordController {

    @NonNull
    private final PatientRecordService patientRecordService;

    @NonNull
    private final ContentProvider contentProvider;

    public RecordController(
            @NonNull PatientRecordService patientRecordService,
            @NonNull ContentProvider contentProvider
    ) {
        this.patientRecordService = patientRecordService;
        this.contentProvider = contentProvider;
    }

    /**
     * Отдает главную страницу с записями.
     *
     * @param user     пользователь (врач или пациент)
     * @param pageable запрос страницы
     * @return модель и представление
     */
    @GetMapping
    @Timed("records.request")
    ModelAndView myRecords(@AuthenticationPrincipal User user, Pageable pageable) {
        final var subdir = user.isPatient() ? "patient" : "doctor";
        final var modelAndView = new ModelAndView(String.join("/", subdir, "my-record"));
        final Page<?> records = this.contentProvider.records(user, pageable);
        modelAndView.addObject("records", records);
        modelAndView.addObject("specialities", this.contentProvider.specialities());
        modelAndView.addObject("departments", this.contentProvider.availableDepartments());

        return modelAndView;
    }

    /**
     * Отдает фрагмент html страницы с временными слотами. Используется для модального
     * окна записи пациента на прием.
     * <p>
     * Доступно только для пользователей с ролью 'Пациент'.
     *
     * @param specialityId идентификатор специальности врача
     * @param departmentId идентификатор департамента
     * @return модель и представление
     */
    @PreAuthorize("hasRole('PATIENT')")
    @GetMapping("/slots/{specialityId}/{departmentId}")
    ModelAndView slots(@PathVariable UUID specialityId, @PathVariable UUID departmentId) {
        final var modelAndView = new ModelAndView("patient/slots");
        final List<SlotView> slots = this.patientRecordService.getAvailableSlots(specialityId, departmentId);

        final Map<LocalDate, Map<DoctorView, List<SlotView>>> slotsByDateAndDoctor = slots
                .stream()
                .collect(groupingBy(slot -> slot.getTime().toLocalDate(),
                        groupingBy(SlotView::getDoctor)));
        modelAndView.addObject("slots", slotsByDateAndDoctor);

        return modelAndView;
    }

}
