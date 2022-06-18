package research.service;

import research.exceptions.ProjectAlreadyExistsException;
import research.exceptions.ProjectNotValidException;
import research.exceptions.researchGroupNotValidException;
import research.model.Location;
import research.model.Project;
import research.model.ResearchGroup;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

public class Validation {
    public Project validProject(Project projectToValidate){
        boolean isValid=checkDate(projectToValidate.getStartDate(),Project.FIRST_DATE.minusDays(1),Project.LAST_DATE.plusDays(1))
                && checkNotBlankString(projectToValidate.getName())
                && checkNotNegativeInteger(projectToValidate.getBudget());

        if (!isValid) {
            throw new ProjectNotValidException(projectToValidate);
        }
        return projectToValidate;
    }

    private boolean checkNotBlankString(String s){
        return s != null && !s.isBlank();
    }

    private boolean checkDate(LocalDate actual, LocalDate start, LocalDate end){
        return actual.isAfter(start) && actual.isBefore(end);
    }

    private boolean checkNotNegativeInteger(int i){
        return i>=0;
    }

    public ResearchGroup validResearchGroup(ResearchGroup researchGroupToValidate) {
        boolean isValid=checkNotBlankString(researchGroupToValidate.getName())
                && checkDate(researchGroupToValidate.getFounded(),Project.FIRST_DATE.minusDays(1),LocalDate.now().plusDays(1))
                && checkNotNegativeInteger(researchGroupToValidate.getCountOfResearchers())
                && checkNotNegativeInteger(researchGroupToValidate.getBudget())
                && checkValidLocation(researchGroupToValidate.getLocation());


//        private String name;
//
//        private LocalDate founded;
//        private int countOfResearchers;
//        @Enumerated(EnumType.STRING)
//        private Location location;
//        private int budget;
        if (!isValid) {
            throw new researchGroupNotValidException(researchGroupToValidate);
        }
        return researchGroupToValidate;
    }

    private boolean checkValidLocation(Location location) {
        for (Location validLocation : Location.values()) {
                if (validLocation==location) {
                    return true;
                }
            }
            return false;
    }
}
