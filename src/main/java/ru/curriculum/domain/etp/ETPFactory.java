package ru.curriculum.domain.etp;

//@Component
//public class ETPFactory {
//    @Autowired
//    private EMASectionInfoRepository emaSectionInfoRepository;
//    @Autowired
//    private OMASectionInfoRepository omaSectionInfoRepository;
//    @Autowired
//    private PlanFactory planFactory;
//
//    public ETP create(ETP_DTO etpDTO) {
//        ETP etp = new ETP(
//                etpDTO.getId(),
//                etpDTO.getTitle(),
//                etpDTO.getTarget(),
//                etpDTO.getDistanceLearningBeginDate(),
//                etpDTO.getDistanceLearningEndDate(),
//                etpDTO.getFullTimeLearningBeginDate(),
//                etpDTO.getFullTimeLearningEndDate(),
//                createEAModules(etpDTO.getEaModules()),
//                createEMASections(etpDTO.getEmaModules()),
//                createOMASections(etpDTO.getOmaModules()));
//
//        return etp;
//    }
//
//    private Set<EMAModule> createEMASections(List<EMAModuleDTO> emaModuleDTOS) {
//        Set<EMAModule> sections = new HashSet<>();
//        emaModuleDTOS.forEach(sectionDTO -> {
//            EMAModule section = new EMAModule(
//                    sectionDTO.getId(),
//                    emaSectionInfoRepository.findOne(sectionDTO.getInfo().getId()),
//                    planFactory.create(sectionDTO.getPlan()));
//            sections.add(section);
//        });
//
//        return sections;
//    }
//
//    private Set<OMAModule> createOMASections(List<OMAModuleDTO> omaSectionDTOs) {
//        Set<OMAModule> sections = new HashSet<>();
//        omaSectionDTOs.forEach(sectionDTO -> {
//            OMAModule section = new OMAModule(
//                    sectionDTO.getId(),
//                    omaSectionInfoRepository.findOne(sectionDTO.getInfo().getId()),
//                    planFactory.create(sectionDTO.getPlan()));
//            sections.add(section);
//        });
//
//        return sections;
//    }
//
//    private Set<EAModule> createEAModules(List<EAModuleDTO> eaModuleDTOs) {
//        Set<EAModule> modules = new HashSet<>();
//        eaModuleDTOs.forEach(moduleDTO -> {
//            Set<EASection> sectionDTOs = new HashSet<>();
//            moduleDTO.getSections().forEach(sectionDTO-> {
//                    EASection section = new EASection(
//                            sectionDTO.getId(),
//                            sectionDTO.getName(),
//                            planFactory.create(sectionDTO.getPlan()));
//                    sectionDTOs.add(section);
//            });
//            EAModule module = new EAModule(moduleDTO.getId(), moduleDTO.getName(), sectionDTOs);
//            modules.add(module);
//        });
//
//        return modules;
//    }
//}
