package edu.colostate.csedu.cmdUtilities
import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import edu.colostate.csedu.DB
import edu.colostate.csedu.db.entity.StudentOutcome

const val pathToOutcomeCsvFile = "src/main/outcomes.csv";
val studentsReceivingPersonalizedEmails = DB.campaigns.get("5inCETCdO6A4FOVpGzGr"); //personalized email campaign
val listOfOutcomes = getMutableListOfOutcomes();
val mapIdToOutcome = getOutcomesAsMap();

private fun getMutableListOfOutcomes(): MutableList<List<String>> {
    val outcomes = mutableListOf<List<String>>();
    csvReader().open(pathToOutcomeCsvFile) {
        readAllAsSequence().forEach { row ->
            outcomes.add(row);
        }
    }
    return outcomes
}

private fun getOutcomesAsMap(): Map<String, List<String>> {
    val map = mutableMapOf<String, List<String>>();
    for(outcome in listOfOutcomes){
        map[outcome[1]] = outcome
    }
    return map;
}




fun main(){
    if (studentsReceivingPersonalizedEmails != null) {
        for(studentID in studentsReceivingPersonalizedEmails.studentIds){
            //setStudentOutcome(studentID);
        }
    }
}

private fun setStudentOutcome(studentID: String) {
    val student = allStudents[studentID];
    val studentOutcomes = mapIdToOutcome[student?.canvasId];
    if(studentOutcomes != null && student != null){
        student.outcomeMapping = getOutcomeMap(studentOutcomes);
        DB.students.set(student.id, student);
    }
}

//this function needs to be altered to get data by header, not by index
//this function also should add to the students history rather than over-ride the students history
private fun getOutcomeMap(outcomes: List<String>): MutableMap<String, StudentOutcome> {

    return mutableMapOf(
            "NS.CS.ALL.2020.JAVA.RECURSION" to createStudentOutcome(2,outcomes),
            "NS.CS.ALL.2020.JAVA.COMMON" to createStudentOutcome(4,outcomes),
            "NS.CS.ALL.2020.JAVA.METHODS" to createStudentOutcome(6,outcomes),
            "NS.CS.ALL.2020.JAVA.REPETITION" to createStudentOutcome(8,outcomes),
            "NS.CS.ALL.2020.JAVA.FUNDAMENTALS" to createStudentOutcome(10,outcomes),
            "NS.CS.ALL.2020.JAVA.INPUT_OUTPUT" to createStudentOutcome(12,outcomes),
            "NS.CS.ALL.2020.JAVA.BRANCHING" to createStudentOutcome(16,outcomes),
            "NS.CS.ALL.2020.JAVA.ARRAYS" to createStudentOutcome(18,outcomes)
            );
}

private fun createStudentOutcome(scoreIndex: Int, outcomes: List<String>): StudentOutcome {
    var s = StudentOutcome(0.0,outcomes[scoreIndex+1].toDouble());
    if(outcomes[scoreIndex].isNotEmpty()){
        s.score = outcomes[scoreIndex].toDouble();
    }
    return s
}
