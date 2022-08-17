package com.example.workoutme

object Constants {

    //Create a function for default exercise Arraylist using ExerciseModel which can hold every exercise with their respective entity
    fun defaultExercise(): ArrayList<ExerciseModel> {
        //Create a variable for ArrayList object to hold ExerciseModel as list
        val exerciseList = ArrayList<ExerciseModel>()
        //Create a variable for jumping Jacks exercise useing Exercise model
        val jumpingJack = ExerciseModel(
            1,
            "Jumping Jack",
            R.drawable.ic_jumping_jacks,
            false,
            false
        )
        //THen add this to the ArrayList variable
        exerciseList.add(jumpingJack)

        //Create a variable for abdominalCrunch exercise useing Exercise model
        val abdominalCrunch = ExerciseModel(
            2,
            "Abdominal Crunch",
            R.drawable.ic_abdominal_crunch,
            false,
            false
        )
        //THen add this to the ArrayList variable
        exerciseList.add(abdominalCrunch)

        //Create a variable for Plank exercise useing Exercise model
        val plank = ExerciseModel(
            3,
            "Plank",
            R.drawable.ic_plank,
            false,
            false
        )
        //THen add this to the ArrayList variable
        exerciseList.add(plank)

        //Create a variable for Side Plank exercise useing Exercise model
        val sidePlank = ExerciseModel(
            4,
            "Side Plank",
            R.drawable.ic_side_plank,
            false,
            false
        )
        //THen add this to the ArrayList variable
        exerciseList.add(sidePlank)

        //Create a variable for Plank exercise useing Exercise model
        val lunge = ExerciseModel(
            5,
            "Lunge",
            R.drawable.ic_lunge,
            false,
            false
        )
        //THen add this to the ArrayList variable
        exerciseList.add(lunge)

        //Create a variable for highKneesRun exercise useing Exercise model
        val highKneesRun = ExerciseModel(
            6,
            "High Knees Running in Place",
            R.drawable.ic_high_knees_running_in_place,
            false,
            false
        )
        //THen add this to the ArrayList variable
        exerciseList.add(highKneesRun)

        //Create a variable for Plank exercise useing Exercise model
        val pushUp = ExerciseModel(
            7,
            "Push Up",
            R.drawable.ic_push_up,
            false,
            false
        )
        //THen add this to the ArrayList variable
        exerciseList.add(pushUp)

        //Create a variable for Plank exercise useing Exercise model
        val pushUpNrotation = ExerciseModel(
            8,
            "Push Up & Rotation",
            R.drawable.ic_push_up_and_rotation,
            false,
            false
        )
        //THen add this to the ArrayList variable
        exerciseList.add(pushUpNrotation)

        //Create a variable for Plank exercise useing Exercise model
        val squat = ExerciseModel(
            9,
            "Squat",
            R.drawable.ic_squat,
            false,
            false
        )
        //THen add this to the ArrayList variable
        exerciseList.add(squat)

        //Create a variable for Plank exercise useing Exercise model
        val stepUpOntoChair = ExerciseModel(
            10,
            "Step Up Onto Chair",
            R.drawable.ic_step_up_onto_chair,
            false,
            false
        )
        //THen add this to the ArrayList variable
        exerciseList.add(stepUpOntoChair)

        //Create a variable for Plank exercise useing Exercise model
        val tricepDip = ExerciseModel(
            11,
            "Tricep Dip On Chair",
            R.drawable.ic_triceps_dip_on_chair,
            false,
            false
        )
        //THen add this to the ArrayList variable
        exerciseList.add(tricepDip)

        val wallSit = ExerciseModel(
            12,
            "Wall Sit",
            R.drawable.ic_wall_sit,
            false,
            false
        )
        //THen add this to the ArrayList variable
        exerciseList.add(wallSit)

        //return the ArrayList
        return exerciseList
    }

}