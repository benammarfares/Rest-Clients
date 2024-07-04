package workout.gym;

import jakarta.validation.constraints.NotEmpty;

public record Workout(
        Integer id ,
        @NotEmpty

        Float ActualWeight,
        @NotEmpty

        Float TargetWeight,
        @NotEmpty

        Float Height,
        @NotEmpty
        String targetMuscle,
        @NotEmpty
        String description


) {
}
