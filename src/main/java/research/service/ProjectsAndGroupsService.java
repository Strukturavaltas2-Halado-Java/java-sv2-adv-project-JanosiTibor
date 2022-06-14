package research.service;

import research.criteria.CarCriteria;
import research.dtos.CreateProjectCommand;
import research.dtos.ProjectDto;
import research.exceptions.CarNotFoundException;
import research.exceptions.CarSellerNotFoundException;
import research.exceptions.IllegalKmStateException;
import research.model.OrderBy;
import research.model.OrderType;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import research.model.Project;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectsAndGroupsService {
    private ModelMapper modelMapper;
    private research.repository.ProjectsRepository projectsRepository;
    private research.repository.ResearchGroupRepository researchGroupRepository;

    public ProjectsAndGroupsService(ModelMapper modelMapper, research.repository.ProjectsRepository projectsRepository, research.repository.ResearchGroupRepository researchGroupRepository) {
        this.modelMapper = modelMapper;
        this.projectsRepository = projectsRepository;
        this.researchGroupRepository = researchGroupRepository;
    }

    public ProjectDto createProject(CreateProjectCommand createProjectCommand) {
        Project project=new Project();
        project.setName(createProjectCommand.getName());
        project.setStartDate(createProjectCommand.getStartDate());
        project.setBudget(createProjectCommand.getBudget());
        projectsRepository.save(project);
        return modelMapper.map(project,ProjectDto.class);
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
