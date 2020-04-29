import com.github.doyaaaaaken.kotlincsv.dsl.csvReader

class GetActiveStudents(private val filename: String) {

    fun getActiveStudentIDs(): MutableList<String> {
        var studentIds = mutableListOf<String>();
        try {
            studentIds = readFile();
            return studentIds;
        }catch (e: Exception){
            val error = e.message;
            println("Error with reading file: --> [$error]");
        }
        return studentIds;
    }

    private fun readFile(): MutableList<String>{
        var ids = mutableListOf<String>();
        csvReader().open(filename) {
            readAllAsSequence().forEach { row ->
                ids.add(row[2]);
            }
        }
        return ids.subList(3,ids.size);
    }
}