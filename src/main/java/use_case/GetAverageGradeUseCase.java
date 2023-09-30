package use_case;
import api.GradeDB;
import entity.Grade;
import entity.Team;

public final class GetAverageGradeUseCase {
    private final GradeDB gradeDB;

    public GetAverageGradeUseCase(GradeDB gradeDB) {
        this.gradeDB = gradeDB;
    }

    public float getAverageGrade(String course) {
        float avg = 0;
        int numStudents = gradeDB.getMyTeam().getMembers().length;
        for (String student: gradeDB.getMyTeam().getMembers()) {
            avg += gradeDB.getGrade(student, course).getGrade();
        }
        return avg / numStudents;
    }
}
