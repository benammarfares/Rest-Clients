package workout.gym;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workout")
public class WorkoutController {
    private final WorkoutRepo workoutRepository;

    public WorkoutController(WorkoutRepo workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @RequestMapping("/AllCardio")
    List<Workout> findAll(){
        return workoutRepository.findAll();
    }

    @RequestMapping("/{id}")
    Workout findById(@PathVariable Integer id){
        return workoutRepository.FindById(id).orElseThrow();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping("")
    void addCardio(@Valid @RequestBody Workout workout){
        workoutRepository.create(workout);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("/{id}")
    void updateCardio(@Valid @RequestBody Workout workout, @PathVariable Integer id){
        workoutRepository.update(workout,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping("/{id}")
    void deleteCardio(@PathVariable Integer id){
        workoutRepository.delete(id);
    }
}
