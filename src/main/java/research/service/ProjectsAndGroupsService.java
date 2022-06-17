package research.service;

import org.modelmapper.TypeToken;
import org.springframework.transaction.annotation.Transactional;
import research.criteria.ResearchGroupCriteria;
import research.dtos.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import research.exceptions.ProjectNotFoundException;
import research.exceptions.ResearchGroupNotFoundException;
import research.model.Project;
import research.model.ResearchGroup;
import research.repository.ResearchGroupsRepository;

import java.lang.reflect.Type;
import java.util.*;

@Transactional
@Service
public class ProjectsAndGroupsService {
    private ModelMapper modelMapper;
    private research.repository.ProjectsRepository projectsRepository;
    private ResearchGroupsRepository researchGroupsRepository;

    private Type projectDtoListType=new TypeToken<List<ProjectDto>>(){}.getType();
    private Type researchGroupDtoListType=new TypeToken<List<ResearchGroupDto>>(){}.getType();


    public ProjectsAndGroupsService(ModelMapper modelMapper, research.repository.ProjectsRepository projectsRepository, ResearchGroupsRepository researchGroupsRepository) {
        this.modelMapper = modelMapper;
        this.projectsRepository = projectsRepository;
        this.researchGroupsRepository = researchGroupsRepository;
    }

    public ProjectDto createProject(CreateProjectCommand createProjectCommand) {
        Project project=new Project();
        project.setName(createProjectCommand.getName());
        project.setStartDate(createProjectCommand.getStartDate());
        project.setBudget(createProjectCommand.getBudget());
        projectsRepository.save(project);
        return modelMapper.map(project,ProjectDto.class);
    }

    public ResearchGroupDto createResearchGroup(CreateResearchGroupCommand createResearchGroupCommand) {
        ResearchGroup researchGroup=new ResearchGroup();
        researchGroup.setName(createResearchGroupCommand.getName());
        researchGroup.setFounded(createResearchGroupCommand.getFounded());
        researchGroup.setCountOfResearchers(createResearchGroupCommand.getCountOfResearchers());
        researchGroup.setLocation(createResearchGroupCommand.getLocation());
        researchGroup.setBudget(createResearchGroupCommand.getBudget());
        researchGroupsRepository.save(researchGroup);
        return modelMapper.map(researchGroup,ResearchGroupDto.class);
    }

    private Project findProjectById(long id) {
        Optional<Project> optionalProject= projectsRepository.findById(id);
        if(optionalProject.isEmpty()){
//            throw  new IllegalArgumentException("No car found with id: "+id);
            throw  new ProjectNotFoundException(id);
        }
        return optionalProject.get();
    }

    private ResearchGroup findResearchGroupById(long id) {
        Optional<ResearchGroup> optionalResearchGroup= researchGroupsRepository.findById(id);
        if(optionalResearchGroup.isEmpty()){
//            throw  new IllegalArgumentException("No car found with id: "+id);
            throw  new ResearchGroupNotFoundException(id);
        }
        return optionalResearchGroup.get();
    }



    public ProjectDto addPostedGroupToProject(long id, CreateResearchGroupCommand createResearchGroupCommand) {
        ResearchGroup researchGroup=modelMapper.map(createResearchGroupCommand,ResearchGroup.class);
        Project project=findProjectById(id);
        project.addGroup(researchGroup);
        researchGroupsRepository.save(researchGroup);
//        projectsRepository.save(project);

        return modelMapper.map(project,ProjectDto.class);
    }

    public ProjectDto addGroupToProject(long projectId, long groupId) {
        ResearchGroup researchGroup=findResearchGroupById(groupId);
        Project project=findProjectById(projectId);
        project.addGroup(researchGroup);
//        projectsRepository.save(project);

        return modelMapper.map(project,ProjectDto.class);
    }

    public void deleteResearchGroup(long id) {
        ResearchGroup researchGroup=findResearchGroupById(id);
        findProjectsByParticipatingGroup(researchGroup).forEach( project -> project.removeGroup(researchGroup));
        researchGroupsRepository.deleteById(id);
    }
    public List<Project> findProjectsByParticipatingGroup(ResearchGroup researchGroup){
        List<Project> filteredProjectList=projectsRepository.findProjectsByParticipatingGroup(researchGroup);
        return filteredProjectList;
    }
    public List<Project> findProjectDtosByParticipatingGroup(ResearchGroup researchGroup){
        return modelMapper.map(findProjectsByParticipatingGroup(researchGroup),projectDtoListType);
    }

    public void deleteProject(long id) {
        Project project=findProjectById(id);
        projectsRepository.delete(project);
    }

    public ProjectDto getProjectById(long id) {
        Project project=findProjectById(id);
        return modelMapper.map(project,ProjectDto.class);
    }

    public ResearchGroupDto getResearchGroupById(long id) {
        ResearchGroup researchGroup=findResearchGroupById(id);
        return modelMapper.map(researchGroup,ResearchGroupDto.class);
    }

    public ProjectDto updateProject(long id, UpdateProjectCommand updateProjectCommand) {
        Project project=findProjectById(id);
        if(updateProjectCommand.getName()!=null){
            project.setName(updateProjectCommand.getName());
        }
        if(updateProjectCommand.getStartDate()!=null){
            project.setStartDate(updateProjectCommand.getStartDate());
        }
        if(updateProjectCommand.getBudget()!=null){
            project.setBudget(updateProjectCommand.getBudget());
        }
        return modelMapper.map(project,ProjectDto.class);
    }

    public ResearchGroupDto updateResearchGroupById(long id, UpdateResearchGroupCommand updateResearchGroupCommand) {
        ResearchGroup researchGroup=findResearchGroupById(id);
        if(updateResearchGroupCommand.getName()!=null){
            researchGroup.setName(updateResearchGroupCommand.getName());
        }
        if(updateResearchGroupCommand.getFounded()!=null){
            researchGroup.setFounded(updateResearchGroupCommand.getFounded());
        }
        if(updateResearchGroupCommand.getCountOfResearchers()!=null){
            researchGroup.setCountOfResearchers(updateResearchGroupCommand.getCountOfResearchers());
        }
        if(updateResearchGroupCommand.getLocation()!=null){
            researchGroup.setLocation(updateResearchGroupCommand.getLocation());
        }
        if(updateResearchGroupCommand.getBudget()!=null){
            researchGroup.setBudget(updateResearchGroupCommand.getBudget());
        }
        return modelMapper.map(researchGroup,ResearchGroupDto.class);
    }

    public List<ResearchGroupDto> getResearchGroups(ResearchGroupCriteria researchGroupCriteria) {
        List<ResearchGroup> result = researchGroupsRepository.findAllByCriteria(researchGroupCriteria.getNameLike(),researchGroupCriteria.getMinCountOfResearchers(),researchGroupCriteria.getMinBudget());
        return modelMapper.map(result,researchGroupDtoListType);
    }


//    public List<CarDTO> getCars(CarCriteria carCriteria) {
//        List<Car> filteredCarList=new ArrayList<>();
//        if(!carCriteria.getBrand().equals("")){
//            filteredCarList= projectsRepository.findAllByBrandIgnoreCaseAndAgeIsLessThanEqual(carCriteria.getBrand(),carCriteria.getMaxAge());
//        }
//        else{
//            filteredCarList= projectsRepository.findAllByAgeIsLessThanEqual(carCriteria.getMaxAge());
//        }
//
//        return sortedCarStream(filteredCarList,carCriteria).stream()
//                .map(m->modelMapper.map(m,CarDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    private List<Car> sortedCarStream(List<Car> filteredCarList, CarCriteria carCriteria) {
//        List<Car> orderedCarList;
//        if(carCriteria.getOrderBy()== OrderBy.age) {
//            orderedCarList=filteredCarList.stream()
//                    .sorted(Comparator.comparing(Car::getAge))
//                    .collect(Collectors.toList());
//        }
//        else if(carCriteria.getOrderBy()==OrderBy.km) {
//            orderedCarList=filteredCarList.stream()
//                    .sorted(Comparator.comparing(car->car.getKilometerStateList().get(car.getKilometerStateList().size()-1).getKmCounter()))
//                    .collect(Collectors.toList());
//        }
//        else{
//            orderedCarList=filteredCarList.stream()
//                    .sorted(Comparator.comparing(Car::getId))
//                    .collect(Collectors.toList());
//        }
//        if(carCriteria.getOrderType()== OrderType.desc){
//            Collections.reverse(orderedCarList);
//        }
//        return orderedCarList;
//    }
//
//
//
//    public List<String> getBrands() {
//        return projectsRepository.getAllBrands().stream()
//                .sorted()
//                .collect(Collectors.toList());
//    }
//
//    public CarDTO addKilometerStatesToCar(long id, AddKilometerStatesCommand addKilometerStatesCommand) {
//        Car car=findCarById(id);
//        if(car.getKilometerStateList().get(car.getKilometerStateList().size()-1).getKmCounter()>addKilometerStatesCommand.getKmCounter()){
////            throw new IllegalStateException("Not valid km count: "+addKilometerStatesCommand.getKmCounter());
//            throw new IllegalKmStateException(addKilometerStatesCommand.getKmCounter());
//        }
//        car.addKilometerCount(addKilometerStatesCommand.getKmCounter());
//        projectsRepository.save(car);
//        return modelMapper.map(car,CarDTO.class);
//    }
//
//    private Car findCarById(long id) {
//        Optional<Car> car= projectsRepository.findById(id);
//        if(car.isEmpty()){
////            throw  new IllegalArgumentException("No car found with id: "+id);
//            throw  new CarNotFoundException(id);
//        }
//        return car.get();
//    }
//
//    public CarDTO getCar(long id) {
//        Car car=findCarById(id);
//        return modelMapper.map(car,CarDTO.class);
//    }
//
//    public void deleteCar(long id) {
//        Car car=findCarById(id);
//        projectsRepository.delete(car);
//    }
//    public void deleteAllCar() {
//        projectsRepository.deleteAll();
//    }
//
//    public CarSellerDTO createCarSeller(CreateCarSellerCommand createCarSellerCommand) {
//        CarSeller carSeller=new CarSeller(createCarSellerCommand.getName());
//        researchGroupRepository.save(carSeller);
//        return modelMapper.map(carSeller,CarSellerDTO.class);
//    }
//    private CarSeller findCarSellerById(long id) {
//        Optional<CarSeller> carSeller= researchGroupRepository.findById(id);
//        if(carSeller.isEmpty()){
////            throw  new IllegalArgumentException("No car found with id: "+id);
//            throw  new CarSellerNotFoundException(id);
//        }
//        return carSeller.get();
//    }
//
////    @Transactional
//    public CarSellerDTO addCarToSeller(long id, CreateCarCommand createCarCommand) {
//        CarSeller carSeller=findCarSellerById(id);
//
//        Car car = new Car();
//        car.setBrand(createCarCommand.getBrand());
//        car.setType(createCarCommand.getType());
//        car.setAge(createCarCommand.getAge());
//        car.setCondition(createCarCommand.getCondition());
//        car.addKilometerCount(createCarCommand.getKmCounter());
//        car.setSeller(carSeller);
//        projectsRepository.save(car);
//
//        return modelMapper.map(carSeller,CarSellerDTO.class);
//    }
//    public void deleteCarSeller(long id) {
//        CarSeller carSeller=findCarSellerById(id);
//        researchGroupRepository.delete(carSeller);
//    }

}
