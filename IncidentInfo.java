public class IncidentInfo {
    String incidentId;
    String incidentType;
    int severityLevel;
    String reportDate;
    String estimatedResolutionTime;
    double impactCost;

    public IncidentInfo(String id, String type, int severity, String date, String time, double cost) {
        this.incidentId = id;
        this.incidentType = type;
        this.severityLevel = severity;
        this.reportDate = date;
        this.estimatedResolutionTime = time;
        this.impactCost = cost;
    }

    public void display() {
        System.out.println("   > ID: " + incidentId +
                " | Type: " + incidentType +
                " | Severity: " + severityLevel +
                " | Date: " + reportDate +
                " | Time: " + estimatedResolutionTime +
                " | Cost: RM " + impactCost);
    }
}