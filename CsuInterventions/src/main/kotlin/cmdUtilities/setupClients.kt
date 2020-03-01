//import edu.colostate.csedu.cmdUtilities.SheetsReader
//
//import edu.colostate.csedu.DB
//import edu.colostate.csedu.db.entity.Assessment
//import edu.colostate.csedu.db.entity.MasteryOutcome
//
///**
// *
// *
// *
// * @author Albert Lionelle <br>
// *         lionelle@colostate.edu <br>
// *         Computer Science Department <br>
// *         Colorado State University
// * @version 1.0
// */
//
//
//
///*   (Code to add folks to the database. removed for safety reasons)
//val list = reader.getStudents(reader.getCanvasMap())
//   for(student in list) {
//       println("Adding:  ${student.email}")
//       DB.addStudent(args.getOrNull(0) ?: "cs150Fall19", student)
//   }
//
// */
//
//
//fun main(args: Array<String>) {
//    val reader = SheetsReader()
//
//    val courseId = args.getOrNull(0) ?: "cs150Fall19"
//    val outcomes = reader.getOutcomes()
//    val students = DB.getAllStudents(courseId)
//    for(student in students) {
//        if(outcomes.contains(student.canvasId)) {
//            var assessment = MasteryOutcome(name = "Unit 1 Outcomes", id = "unit-1-outcomes",
//                                            topics = outcomes[student.canvasId] as Map<String, Map<String, Double>>)
//            DB.addAssessment(student, assessment)
//        }
//    }
//
//
//}