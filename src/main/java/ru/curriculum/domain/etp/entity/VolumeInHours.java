package ru.curriculum.domain.etp.entity;


import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(fluent = true)
public class VolumeInHours {

    @Column(name = "total_hours")
    private Double total;

    @Column(name = "hours_per_one_listener")
    private Double perOneListener;

    @Column(name = "full_time_hours_per_one_listener")
    private Double fullTimePerOneListener;

    @Column(name = "distance_hours_per_one_listener")
    private Double distancePerOneListener;

    @Column(name = "total_hours_study_work")
    private Double totalStudyWork;

    @Column(name = "full_time_hours_study_work")
    private Double fullTimeStudyWork;

    @Column(name = "distance_hours_study_work")
    private Double distanceStudyWork;

    @Column(name = "payment_hours_study_work")
    private Double paymentStudyWork;

    @Column(name = "full_time_payment_hours_study_work")
    private Double fullTimePaymentForStudyWork;

    @Column(name = "distance_payment_hours_study_work")
    private Double distancePaymentForStudyWork;

    @Column(name = "ema_payment_hours_study_work")
    private Double emaPaymentForStudyWork;

    @Column(name = "oma_payment_hours_study_work")
    private Double omaPaymentForStudyWork;

    @Column(name = "in_load_hours")
    private Double inLoad;

    @Column(name = "full_time_in_load_hours")
    private Double fullTimeInLoad;

    @Column(name = "distance_in_load_hours")
    private Double distanceInLoad;

    @Column(name = "ema_in_load_hours")
    private Double emaInLoad;

    @Column(name = "oma_in_load_hours")
    private Double omaInLoad;
}
