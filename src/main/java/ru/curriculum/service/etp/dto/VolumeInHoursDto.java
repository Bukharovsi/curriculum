package ru.curriculum.service.etp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.VolumeInHours;

@Getter
@Setter
@NoArgsConstructor
public class VolumeInHoursDto {

    private Double total;

    private Double perOneListener;

    private Double fullTimePerOneListener;

    private Double distancePerOneListener;

    private Double totalStudyWork;

    private Double fullTimeStudyWork;

    private Double distanceStudyWork;

    private Double paymentStudyWork;

    private Double fullTimePaymentForStudyWork;

    private Double distancePaymentForStudyWork;

    private Double emaPaymentForStudyWork;

    private Double omaPaymentForStudyWork;

    private Double inLoad;

    private Double fullTimeInLoad;

    private Double distanceInLoad;

    private Double emaInLoad;

    private Double omaInLoad;

    public VolumeInHoursDto(VolumeInHours volumeInHours) {
        this.total = volumeInHours.total();
        this.perOneListener = volumeInHours.perOneListener();
        this.fullTimePerOneListener = volumeInHours.fullTimePerOneListener();
        this.distancePerOneListener = volumeInHours.distancePerOneListener();
        this.totalStudyWork = volumeInHours.totalStudyWork();
        this.fullTimeStudyWork = volumeInHours.fullTimeStudyWork();
        this.distanceStudyWork = volumeInHours.distanceStudyWork();
        this.paymentStudyWork = volumeInHours.paymentStudyWork();
        this.fullTimePaymentForStudyWork = volumeInHours.fullTimePaymentForStudyWork();
        this.distancePaymentForStudyWork = volumeInHours.distancePaymentForStudyWork();
        this.emaPaymentForStudyWork = volumeInHours.emaPaymentForStudyWork();
        this.omaPaymentForStudyWork = volumeInHours.omaPaymentForStudyWork();
        this.inLoad = volumeInHours.inLoad();
        this.fullTimeInLoad = volumeInHours.fullTimeInLoad();
        this.distanceInLoad = volumeInHours.distanceInLoad();
        this.emaInLoad = volumeInHours.emaInLoad();
        this.omaInLoad = volumeInHours.omaInLoad();
    }
}
