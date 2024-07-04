package workout.gym;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

public class WorkoutRepo {

    private final JdbcClient jdbcClient;


    public WorkoutRepo(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Workout> findAll(){
        return jdbcClient.sql("select * from workout")
                .query(Workout.class)
                .list();
    }

    public Optional<Workout> FindById(Integer id){
        return jdbcClient.sql("SELECT * FROM workout where id = :id")
                .param("id", id)
                .query(Workout.class)
                .optional();
    }

    public void create(Workout workout){
        var updated =  jdbcClient.sql("INSERT INTO workout (ID , NAME , DESCRIPTION , EQUIPMENT, TARGETMUSCLE , DIFFICULTY , TYPEE) values (?, ?, ?, ?, ?, ?, ?)")
                .params(List.of(workout.id(),workout.ActualWeight(), workout.TargetWeight(), workout.Height(), workout.targetMuscle(), workout.description()))
                .update();
        Assert.state(updated == 1, "Failed to create run " + workout.id());
    }

    public void update(Workout workout, Integer id){
        var updated = jdbcClient.sql("update workout set name = ?, description = ?, equipment = ?, targetMuscle = ?, difficulty = ?, typee = ? where id = ?")
                .params(List.of(workout.ActualWeight(), workout.TargetWeight(), workout.Height(), workout.targetMuscle(), workout.description(), id))
                .update();
        Assert.state(updated == 1, "Failed to update run " + workout.ActualWeight());
    }

    public void delete(Integer id){
        var updated = jdbcClient.sql("delete from workout where id = :id")
                .param("id", id)
                .update();
        Assert.state(updated == 1, "Failed to delete run " + id);
    }

    public int count (){
        return jdbcClient.sql("select * from workout").query().listOfRows().size();

    }

    public void saveAll(List<Workout> workouts){
        workouts.forEach(this::create);
    }

    public List<Workout> FilterByEquipment(String equipment){
        return jdbcClient.sql("select * from workout where equipment = ?")
                .param("equipment", equipment)
                .query(Workout.class)
                .list();
    }
}
